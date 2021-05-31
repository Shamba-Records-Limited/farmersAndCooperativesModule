package com.shabarecords.farmersmodule.repository.farmer;

import com.shabarecords.farmersmodule.models.farm.Farm;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface FarmRepository extends JpaRepository<Farm, Integer> {

    List<Farm> findAllByFarmer_GrowerId(String farmer_growerId);

    List<Farm> findAllByVillage_Id(Integer village_id, PageRequest request);
    List<Farm> findAllByVillage_SubCounty_SubCountyCode(String village_subCounty_subCountyCode, PageRequest request);
    List<Farm> findAllByVillage_SubCounty_County_CountyCode(String village_subCounty_county_countyCode, PageRequest request);

}
