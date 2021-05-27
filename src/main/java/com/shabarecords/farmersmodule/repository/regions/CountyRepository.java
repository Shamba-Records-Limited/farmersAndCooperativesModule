package com.shabarecords.farmersmodule.repository.regions;

import com.shabarecords.farmersmodule.models.regions.County;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface CountyRepository extends JpaRepository<County, String> {

    boolean existsByCountyCode(String countyCode);

    boolean existsByName(String name);
}
