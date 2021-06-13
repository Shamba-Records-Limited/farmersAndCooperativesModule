package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CooperativeService {

    ResponseEntity<List<CoopFarmers>> getCoopFarmers(Long cooperativeId);

    ResponseEntity<APIResponse> addCoopFarmer(Long cooperativeId, AddCooperativeFarmer addCooperativeFarmer);

    ResponseEntity<Cooperative> getCooperative(String code);

    ResponseEntity<List<Cooperative>> getAllCoperatives();

    ResponseEntity<APIResponse> updateCooperative(Long cooperativeId, UpdateCooperativeRequest updateCooperativeRequest);

    ResponseEntity<APIResponse> addCooperative(AddCooperativeRequest request);

    ResponseEntity<APIResponse> addFarm(Long cooperativeId, FarmRequest farmRequest);

    ResponseEntity<List<FarmData>> getCooperativeFarms(String coopCode);
}
