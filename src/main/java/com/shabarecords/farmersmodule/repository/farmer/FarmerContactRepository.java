package com.shabarecords.farmersmodule.repository.farmer;

import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */
public interface FarmerContactRepository extends JpaRepository<FarmerContact, BigDecimal> {

    List<FarmerContact> findAllByFarmer_GrowerId(String farmer_growerID);
}
