package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.cooperative.CooperativeFarmers;
import com.shabarecords.farmersmodule.utils.APIResponse;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/29/21, Sat
 */
public interface CoopFarmersService {

    ResponseEntity<APIResponse> addCooperativeFarmer(CooperativeFarmers cooperative);

    ResponseEntity getCooperativeFarmers(String coopCode, PageRequest pageRequest);

    ResponseEntity getActiveCooperativeFarmers(String cooperativeCode, boolean active, PageRequest pageRequest);

    ResponseEntity<APIResponse> deactivateFarmer(Long id);
}
