package com.example.individual_spring.stores.data;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.individual_spring.stores.Master;
import org.springframework.data.repository.query.Param;

public interface MasterRepository extends CrudRepository<Master, Long> {
    @Modifying
    @Query("UPDATE MASTER m set m.FULL_NAME = :fullName, m.POST = :post, m.PHONE = :phone where m.MASTER_ID = :id")
    void updateMaster(@Param(value = "id") Long id, @Param(value = "fullName") String fullName,
                      @Param(value = "post") String post, @Param(value = "phone") String phone);
}
