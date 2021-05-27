package com.shabarecords.farmersmodule.services.farmer;

import com.shabarecords.farmersmodule.models.farmer.Farmer;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */
public interface FarmerService {

    Farmer getFarmer(String farmer_id);

    List<Farmer> getAllFarmers(PageRequest request);

    Farmer updateFarmer(Farmer farmer);

    Farmer addFarmer(Farmer farmer);

}
