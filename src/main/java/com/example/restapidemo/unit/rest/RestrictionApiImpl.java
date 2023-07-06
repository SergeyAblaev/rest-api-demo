package com.example.restapidemo.unit.rest;

import com.example.restapidemo.unit.rest.api.RestrictionApi;
import com.example.restapidemo.unit.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RestrictionApiImpl implements RestrictionApi {

    public static final String TOO_MACH_REQUESTS = "Too mach requests!";
    RequestService requestService;


    @Override
    public ResponseEntity<String> getlifebit(HttpServletRequest request) {

        String remoteAddr = request.getRemoteAddr();
        ResponseEntity<String> response;
        if (requestService.saveAndCheckAllowedRequest(remoteAddr)) {
            response = ResponseEntity.ok().build();
        } else {
            response = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(TOO_MACH_REQUESTS);
        }
        return response;
    }
}
