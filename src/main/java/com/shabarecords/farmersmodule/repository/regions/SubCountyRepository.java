package com.shabarecords.farmersmodule.repository.regions;

import com.shabarecords.farmersmodule.models.regions.SubCounty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface SubCountyRepository extends JpaRepository<SubCounty, String> {

    boolean existsBySubCountyCodeOrName(String subCountyCode, String name);

    List<SubCounty> findAllByCounty_CountyCode(String county_countyCode);
}
