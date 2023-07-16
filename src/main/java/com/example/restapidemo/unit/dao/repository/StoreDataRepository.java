package com.example.restapidemo.unit.dao.repository;

import com.example.restapidemo.unit.dao.entity.StoreDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreDataRepository extends JpaRepository<StoreDataEntity, String> {

}
