package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.regions.County;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface CountyService {

    County saveOrUpdateCounty(County county);

    County getCounty(String countyCode);

    List<County> addCounties(List<County> counties);

    List<County> getCounties();
}
