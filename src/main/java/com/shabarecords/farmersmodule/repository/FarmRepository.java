package com.shabarecords.farmersmodule.repository;

import com.shabarecords.farmersmodule.models.Farm;
import com.shabarecords.farmersmodule.utils.databind.FarmData;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
@Repository
public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<FarmData> findByFarmer_Code(String growerCode);




}
