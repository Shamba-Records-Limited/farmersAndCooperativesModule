package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.Cooperatives;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.AddCooperativeFarmer;
import com.shabarecords.farmersmodule.utils.databind.AddFarmerRequest;
import com.shabarecords.farmersmodule.utils.databind.CoopFarmers;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CoopMemberShipService {
    ResponseEntity<APIResponse> addFarmer(Cooperatives cooperative,AddCooperativeFarmer request);
    List<CoopFarmers> getCoopFarmers(Long cooperativeId);
}
