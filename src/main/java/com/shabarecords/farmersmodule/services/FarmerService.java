package com.shabarecords.farmersmodule.services;


import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */
public interface FarmerService {


    ResponseEntity<APIResponse> addFarmer(AddFarmerRequest request);

    ResponseEntity<APIResponse> updateFarmer(Long growerCode, UpdateFarmerRequest updateRequest);

    ResponseEntity<Farmer> getFarmer(String growerCode);

    ResponseEntity<Page<Farmer>> getFarmers(Pageable pageable);

    ResponseEntity<APIResponse> addFarm(Long farmerId, FarmRequest farmRequest);

    ResponseEntity<List<FarmData>> getGrowerFarms(String growerCode);
}
