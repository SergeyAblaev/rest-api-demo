package com.example.restapidemo.unit.rest;

import com.example.restapidemo.unit.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class restrictionApiImplTest {
    private static final long MINUTES_VALUE =1;
    private static final long MAX_COUNT_VALUE =3;
    private final RequestService requestService = new RequestService();
    private final RestrictionApiImpl restrictionApiImpl = new RestrictionApiImpl(requestService);

    @Test
    void getlifebit() {
        ReflectionTestUtils.setField(requestService,"minutesValue", MINUTES_VALUE);
        ReflectionTestUtils.setField(requestService,"maxCountValue", MAX_COUNT_VALUE);
        String requestURI = "/lifebit";
        HttpServletRequest httpServletRequest = new MockHttpServletRequest(null, "GET", requestURI);

        ResponseEntity<String> getlifebit = getlifebitResponse(httpServletRequest, "192.168.1.2");
        assertEquals(HttpStatus.OK.value(), getlifebit.getStatusCode().value());

        for (int i=1; i<MAX_COUNT_VALUE; i++){
            getlifebitResponse(httpServletRequest, "192.168.1.1");
            getlifebitResponse(httpServletRequest, "192.168.1.2");
        }
        getlifebit = getlifebitResponse(httpServletRequest, "192.168.1.1");
        assertEquals(HttpStatus.OK.value(), getlifebit.getStatusCode().value());
        getlifebit = getlifebitResponse(httpServletRequest, "192.168.1.2");
        assertEquals(HttpStatus.BAD_GATEWAY.value(), getlifebit.getStatusCode().value());
    }

    private ResponseEntity<String> getlifebitResponse(HttpServletRequest httpServletRequest, String remoteAddr) {
        ((MockHttpServletRequest) httpServletRequest).setRemoteAddr(remoteAddr);
        return restrictionApiImpl.getlifebit(httpServletRequest);
    }
}