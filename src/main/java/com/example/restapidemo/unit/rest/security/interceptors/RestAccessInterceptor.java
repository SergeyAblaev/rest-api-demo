package com.example.restapidemo.unit.rest.security.interceptors;

import com.example.restapidemo.unit.service.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

@AllArgsConstructor
public class RestAccessInterceptor implements HandlerInterceptor {

    private static final String TOO_MACH_REQUESTS = "Too mach requests!";

    RequestService requestService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String remoteAddr = request.getRemoteAddr();
        if (requestService.saveAndCheckAllowedRequest(remoteAddr)) {
            return true;
        } else {
            response.setStatus(HttpStatus.BAD_GATEWAY.value());
            response.setHeader("Bad-Gateway", TOO_MACH_REQUESTS);
            return false;
        }
    }


}