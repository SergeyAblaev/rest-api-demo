package com.example.restapidemo.unit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.time.LocalDateTime.now;
import static java.time.ZoneId.systemDefault;

@Service
public class RequestService {
    private static final Map<String, LinkedList<Long>> STORE = new ConcurrentHashMap<>();
    @Value("${lifebit.minutesValue}")
    private long minutesValue;
    @Value("${lifebit.maxCountValue}")
    private long maxCountValue;


    public boolean saveAndCheckAllowedRequest(String remoteAddr) {
        LocalDateTime now = now();
        saveRequest(remoteAddr, now);
        return checkAllowedrequestResult(remoteAddr, now);
    }
//        RequestRepository.save()// or Map - где макс элементов = N штук (+перестройка при смене настройки)
        //  количество запросов с одного IP адреса на этот метод в размере N штук в X минут.

        //- Написать функционал, который будет ограничивать количество запросов с одного IP адреса на этот метод в размере N штук в X минут.
        // Если количество запросов больше, то должен возвращаться 502 код ошибки, до тех пор, пока количество обращений за заданный интервал не станет ниже N.
//    }

    private boolean checkAllowedrequestResult(String remoteAddr, LocalDateTime now) {
        LocalDateTime minusMinutes = now.minusMinutes(minutesValue);
        Long longMinutes = minusMinutes.atZone(systemDefault()).toEpochSecond();
        LinkedList<Long> list = STORE.get(remoteAddr);
        ListIterator<Long> listIterator = list.listIterator();
        int countValue = 0;
        while (listIterator.hasNext()){
            Long next = listIterator.next();
            if (next>longMinutes){
                countValue++;
            }
        }
        if (countValue > maxCountValue) {
            return false;
        }
            return true;
    }

    private void saveRequest(String remoteAddr, LocalDateTime dateTime) {
        LinkedList<Long> list = STORE.get(remoteAddr);
        if (list == null) {
            list = new LinkedList<>();
        }
        long epoch = dateTime.atZone(systemDefault()).toEpochSecond();
        list.add(epoch);
    STORE.put(remoteAddr, list);
    }

}
