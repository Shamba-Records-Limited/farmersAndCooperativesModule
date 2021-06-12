package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.Farmers;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.FarmData;
import com.shabarecords.farmersmodule.utils.databind.FarmRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FarmService {

    ResponseEntity<APIResponse> addFarm(Farmers farmers, FarmRequest farmRequest);
    public ResponseEntity<List<FarmData>> getGrowerFarms(String growerCode);

}
