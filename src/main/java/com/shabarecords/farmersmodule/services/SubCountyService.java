package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.regions.SubCounty;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface SubCountyService {

    SubCounty addSubCounty(SubCounty subCounty);

    List<SubCounty> addSubCounties(List<SubCounty>  subCounties);

    SubCounty getSubCounty(String subCountyCode);

    List<SubCounty> getSubCounties(PageRequest request);

    List<SubCounty> getSubCounties(String countyCode);
}
