package com.example.individual_spring.stores.data;

import com.example.individual_spring.stores.Master;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.individual_spring.stores.Service;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
//    @Query("SELECT * FROM SERVICE WHERE SERVICE.MASTER_ID = :masterId")
    @Modifying
    @Query("INSERT INTO SERVICE (TITLE, PRICE, DURATION, MASTER_ID) VALUES ( :title, :price, :duration, :masterId )")
    void createService(@Param("title") String title,
                       @Param("price") float price,
                       @Param("duration") float duration,
                       @Param("masterId") Long masterId);
}
