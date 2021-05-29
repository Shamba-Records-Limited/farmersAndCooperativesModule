package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.farm.Farm;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface FarmService {

    Farm addFarm(Farm farm);

    Farm getFarm(Integer id);

    List<Farm> getFarmersFarms(String growerId);

    List<Farm> getFarmsInAVillage(Integer growerId, PageRequest  request);

    List<Farm> getFarmsInASubCounty(String growerId, PageRequest  request);

    List<Farm> getFarmsInACounty(String growerId, PageRequest  request);



}
