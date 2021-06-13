package com.shabarecords.farmersmodule.services.impl;

import com.shabarecords.farmersmodule.models.*;
import com.shabarecords.farmersmodule.repository.CooperativeMemberShipRepository;
import com.shabarecords.farmersmodule.repository.FarmerRepository;
import com.shabarecords.farmersmodule.services.CoopMemberShipService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.databind.AddCooperativeFarmer;
import com.shabarecords.farmersmodule.utils.databind.CoopFarmers;
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
public class CoopMemberShipServiceImpl  implements CoopMemberShipService {

    private final FarmerRepository farmerRepository;
    private final CooperativeMemberShipRepository cooperativeMemberShipRepository;

    @Override
    public ResponseEntity<APIResponse> addFarmer(Cooperatives cooperative, AddCooperativeFarmer request) {
        try {

            if(cooperativeMemberShipRepository.existsByCooperativeAndCoopMemberShipNo(cooperative,request.getMemberShipNo())){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Farmer with memberShip No %s already exists",
                                request.getMemberShipNo())));

            }


            Optional<Farmers> farmersOptional = farmerRepository.findById(request.getFarmerId());
            if(!farmersOptional.isPresent()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Farmer with farmerId %d could not be found",
                                request.getFarmerId())));
            }



            cooperativeMemberShipRepository.save(CooperativeMemberShip.of(request.getMemberShipNo(), farmersOptional.get(),cooperative));

            return ResponseEntity.ok(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public List<CoopFarmers> getCoopFarmers(Long cooperativeId) {
        return cooperativeMemberShipRepository.findByCooperative_Id(cooperativeId);
    }
}
