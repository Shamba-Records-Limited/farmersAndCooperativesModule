package com.shabarecords.farmersmodule.repository;

import com.shabarecords.farmersmodule.models.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Repository
public interface RegionRepository extends JpaRepository<Region, String> {


}
