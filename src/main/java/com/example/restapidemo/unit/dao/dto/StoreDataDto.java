package com.example.restapidemo.unit.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDataDto {

    private Long id;
    private String status;
    private LocalDateTime createTimestamp;
    private LocalDateTime lastChangeTimestamp;

}
