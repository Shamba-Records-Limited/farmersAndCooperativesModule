package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.Region;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.RegionRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegionService {
    ResponseEntity<APIResponse> addRegion(RegionRequest regionRequest);

    ResponseEntity<Region> getRegion(String code);

    ResponseEntity<List<Region>> getRegions();
}
