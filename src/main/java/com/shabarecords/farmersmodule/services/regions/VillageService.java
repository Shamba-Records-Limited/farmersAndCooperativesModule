package com.shabarecords.farmersmodule.services.regions;

import com.shabarecords.farmersmodule.models.regions.Village;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface VillageService {

    Village addVillage(Village village);

    Village findVillage(Integer villageId);

    List<Village> findVillagesInSubCounty(String subCountyCode, PageRequest request);


}
