package com.shabarecords.farmersmodule.repository.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Transactional
public interface CoopContactRepository extends JpaRepository<CoopContact, Integer> {
    List<CoopContact> findByCooperative_Code(String cooperative_code);


    @Modifying(clearAutomatically = true)
    @Query("UPDATE CoopContact cc " +
            " SET   cc.priority = 'SECONDARY' " +
            " WHERE cc.priority = 'PRIMARY' " +
            " and  cc.cooperative.code = :coopCode" +
            " and  cc.type = :contactType" )
    void updateContact(@Param("coopCode") String coopCode, @Param("contactType") String type);
}
