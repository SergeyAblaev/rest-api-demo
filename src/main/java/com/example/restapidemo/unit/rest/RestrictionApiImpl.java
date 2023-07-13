package com.example.restapidemo.unit.rest;

import com.example.restapidemo.unit.rest.api.RestrictionApi;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestrictionApiImpl implements RestrictionApi {

    @Override
    public ResponseEntity<String> getlifebit(HttpServletRequest request) {

        return ResponseEntity.ok().build();
    }
}
