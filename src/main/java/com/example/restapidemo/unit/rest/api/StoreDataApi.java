package com.example.restapidemo.unit.rest.api;

import com.example.restapidemo.unit.dao.dto.StoreDataDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

public interface StoreDataApi {
    @Operation(
            operationId = "getStoredataRequest",
            summary = "storedata controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "502", description = "Server is busy")
            }
    )
    @GetMapping(
            value = "/storedata/all",
            produces = { "application/json" }
    )
    @ResponseBody
    List<StoreDataDto> getAllStoreData();
}
