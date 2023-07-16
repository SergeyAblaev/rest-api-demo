package com.example.restapidemo.unit.service;

import com.example.restapidemo.unit.dao.dto.StoreDataDto;
import com.example.restapidemo.unit.dao.entity.StoreDataEntity;
import com.example.restapidemo.unit.dao.repository.StoreDataRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreDataService {

    @Autowired
    StoreDataRepository storeDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<StoreDataDto> getAll(){
        List<StoreDataEntity> all = storeDataRepository.findAll();
        return all.stream().map(this::convertToDto).toList();
    }

    private StoreDataDto convertToDto(StoreDataEntity post) {
        return modelMapper.map(post, StoreDataDto.class);
    }

}
