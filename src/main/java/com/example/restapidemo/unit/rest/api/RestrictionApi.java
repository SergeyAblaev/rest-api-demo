package com.example.restapidemo.unit.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface RestrictionApi {
    /**
     * GET /lifeBit : Проверка сервиса
     */
    @Operation(
            operationId = "getRequest",
            summary = "lifeBit controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "502", description = "Server is busy")
            }
    )
    @GetMapping(
            value = "/lifebit",
            produces = { "application/json" }
    )
    ResponseEntity<String> getlifebit(HttpServletRequest request);
}
