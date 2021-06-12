package com.shabarecords.farmersmodule.services.impl;

import com.shabarecords.farmersmodule.models.Region;
import com.shabarecords.farmersmodule.repository.RegionRepository;
import com.shabarecords.farmersmodule.services.RegionService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.RegionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
@RequiredArgsConstructor
@Slf4j
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;


    @Override
    public ResponseEntity<APIResponse> addRegion(RegionRequest regionRequest) {
        try {

            // code
            if (regionRepository.existsById(regionRequest.getCode())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Region with code %s already exists",
                                regionRequest.getCode())));
            }


            // save
            regionRepository.save(Region.of(regionRequest));
            return ResponseEntity.ok(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }


    @Override
    public ResponseEntity getRegion(String code) {
        try {
            Optional<Region> region = regionRepository.findById(code);
            if (region.isPresent())
                return ResponseEntity.ok(region.get());


            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity<List<Region>> getRegions() {
        return ResponseEntity.ok(regionRepository.findAll());
    }
}
