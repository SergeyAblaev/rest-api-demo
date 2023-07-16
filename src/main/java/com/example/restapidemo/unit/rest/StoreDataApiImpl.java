package com.example.restapidemo.unit.rest;

import com.example.restapidemo.unit.dao.dto.StoreDataDto;
import com.example.restapidemo.unit.rest.api.StoreDataApi;
import com.example.restapidemo.unit.service.StoreDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreDataApiImpl implements StoreDataApi {

    @Autowired
    StoreDataService storeDataService;

    @Override
    public List<StoreDataDto> getAllStoreData() {
        return storeDataService.getAll();
    }
}
