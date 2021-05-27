package com.shabarecords.farmersmodule.repository.regions;

import com.shabarecords.farmersmodule.models.regions.Village;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface VillageRepository extends JpaRepository<Village, Integer> {

    List<Village> findAllBySubCounty_SubCountyCode(String subCounty_subCountyCode, PageRequest request);
}
