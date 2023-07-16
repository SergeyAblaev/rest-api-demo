package com.example.restapidemo.unit.rest;

import com.example.restapidemo.unit.rest.security.interceptors.RestAccessInterceptor;
import com.example.restapidemo.unit.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RestAccessInterceptorTest {
    public static final String REQUEST_URI = "/lifebit";
    private static final long MINUTES_VALUE = 1;
    private static final long MAX_COUNT_VALUE = 3000;
    public static final String REMOTE_ADDR1 = "192.168.1.1";
    public static final String REMOTE_ADDR2 = "192.168.1.2";
    private RequestService requestService = new RequestService();
    private RestAccessInterceptor restAccessInterceptor = new RestAccessInterceptor(requestService);
    private HttpServletRequest httpServletRequest1;
    private HttpServletRequest httpServletRequest2;
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    void initialize() {
        ReflectionTestUtils.setField(requestService, "minutesValue", MINUTES_VALUE);
        ReflectionTestUtils.setField(requestService, "maxCountValue", MAX_COUNT_VALUE);
        httpServletRequest1 = new MockHttpServletRequest(null, "GET", REQUEST_URI);
        httpServletRequest2 = new MockHttpServletRequest(null, "GET", REQUEST_URI);
        ((MockHttpServletRequest) httpServletRequest1).setRemoteAddr(REMOTE_ADDR1);
        ((MockHttpServletRequest) httpServletRequest2).setRemoteAddr(REMOTE_ADDR2);
        httpServletResponse = new MockHttpServletResponse();
    }

    @Test
    void multithreadingCheckAllowedRequestResult() throws InterruptedException {
        boolean allowedRequestResult = restAccessInterceptor.preHandle(httpServletRequest2, httpServletResponse, this);
        assertTrue(allowedRequestResult);

        ExecutorService e = Executors.newFixedThreadPool(50);
        for (int i = 1; i < MAX_COUNT_VALUE; i++) {
            e.submit(() -> {
                restAccessInterceptor.preHandle(httpServletRequest1, httpServletResponse, this);
                restAccessInterceptor.preHandle(httpServletRequest2, httpServletResponse, this);
            });
        }

        e.awaitTermination(1, TimeUnit.SECONDS);
        allowedRequestResult = restAccessInterceptor.preHandle(httpServletRequest1, httpServletResponse, this);
        assertTrue(allowedRequestResult);

        allowedRequestResult = restAccessInterceptor.preHandle(httpServletRequest2, httpServletResponse, this);
        assertFalse(allowedRequestResult);
    }
}