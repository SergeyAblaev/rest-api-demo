package com.example.restapidemo.unit.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "STORE_DATA")
public class StoreDataEntity {

    @Id
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "CREATE_TIMESTAMP")
    private LocalDateTime createTimestamp;

    @Column(name = "LAST_CHANGE_TIMESTAMP")
    private LocalDateTime lastChangeTimestamp;

}
