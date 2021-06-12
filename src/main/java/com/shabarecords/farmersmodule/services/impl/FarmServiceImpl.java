package com.shabarecords.farmersmodule.services.impl;

import com.shabarecords.farmersmodule.models.Farm;
import com.shabarecords.farmersmodule.models.Farmers;
import com.shabarecords.farmersmodule.models.Region;
import com.shabarecords.farmersmodule.repository.FarmRepository;
import com.shabarecords.farmersmodule.repository.FarmerRepository;
import com.shabarecords.farmersmodule.repository.RegionRepository;
import com.shabarecords.farmersmodule.services.FarmService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.FarmData;
import com.shabarecords.farmersmodule.utils.databind.FarmRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@Scope("singleton")
@RequiredArgsConstructor
@Slf4j
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final RegionRepository regionRepository;



    @Override
    public ResponseEntity<APIResponse> addFarm(Farmers farmers, FarmRequest request) {

        try {



            // check Region Exist
            Region region = null;
            if (StringUtils.hasText(request.getRegionCode())) {
                Optional<Region> optionalRegion = regionRepository.findById(request.getRegionCode());

                if (!optionalRegion.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            APIResponse.ofError(String.format("Region with code %s could not be found",
                                    request.getRegionCode())));

                }

                region = optionalRegion.get();
            }


            farmRepository.save(Farm.of(request, region,farmers));

            return ResponseEntity.ok(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity<List<FarmData>> getGrowerFarms(String growerCode) {
        return ResponseEntity.ok(farmRepository.findByFarmer_Code(growerCode));
    }
}
