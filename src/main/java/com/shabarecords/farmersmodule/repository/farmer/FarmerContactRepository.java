package com.shabarecords.farmersmodule.repository.farmer;

import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import com.shabarecords.farmersmodule.utils.enums.ContactPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@Transactional
public interface FarmerContactRepository extends JpaRepository<FarmerContact, Integer> {

    List<FarmerContact> findAllByFarmer_GrowerId(String farmer_growerID);

    FarmerContact findFirstByFarmerGrowerIdAndPriority(String farmer_growerId, ContactPriority priority);


    @Modifying(clearAutomatically = true)
    @Query("UPDATE FarmerContact fc " +
            "SET   fc.priority = 'SECONDARY' " +
            "WHERE fc.priority = 'PRIMATY' " +
            "and  fc.farmer.growerId = :growerId" )
    void updateContact(@Param("growerId") String growerId);
}
