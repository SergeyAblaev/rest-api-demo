package com.example.restapidemo.unit.rest;

import com.example.restapidemo.unit.rest.security.interceptors.RestAccessInterceptor;
import com.example.restapidemo.unit.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestAccessInterceptorTest {
    private static final long MINUTES_VALUE = 1;
    private static final long MAX_COUNT_VALUE = 3;
    public static final String REMOTE_ADDR1 = "192.168.1.1";
    public static final String REMOTE_ADDR2 = "192.168.1.2";
    private final RequestService requestService = new RequestService();
    private final RestAccessInterceptor restAccessInterceptor = new RestAccessInterceptor(requestService);

    @Test
    void checkAllowedRequestResult() {
        ReflectionTestUtils.setField(requestService, "minutesValue", MINUTES_VALUE);
        ReflectionTestUtils.setField(requestService, "maxCountValue", MAX_COUNT_VALUE);
        String requestURI = "/lifebit";
        HttpServletRequest httpServletRequest = new MockHttpServletRequest(null, "GET", requestURI);
        HttpServletResponse httpServletResponse = new MockHttpServletResponse();

        boolean allowedRequestResult = getCheckAllowedRequestResult(httpServletRequest, httpServletResponse, REMOTE_ADDR2);
        assertTrue(allowedRequestResult);

        for (int i = 1; i < MAX_COUNT_VALUE; i++) {
            getCheckAllowedRequestResult(httpServletRequest, httpServletResponse, REMOTE_ADDR1);
            getCheckAllowedRequestResult(httpServletRequest, httpServletResponse, REMOTE_ADDR2);
        }
        allowedRequestResult = getCheckAllowedRequestResult(httpServletRequest, httpServletResponse, REMOTE_ADDR1);
        assertTrue(allowedRequestResult);

        allowedRequestResult = getCheckAllowedRequestResult(httpServletRequest, httpServletResponse, REMOTE_ADDR2);
        assertFalse(allowedRequestResult);
    }

    private boolean getCheckAllowedRequestResult(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String remoteAddr) {
        ((MockHttpServletRequest) httpServletRequest).setRemoteAddr(remoteAddr);
        return restAccessInterceptor.preHandle(httpServletRequest, httpServletResponse, this);
    }
}