package com.shabarecords.farmersmodule.services.impl;

import com.shabarecords.farmersmodule.models.Cooperatives;
import com.shabarecords.farmersmodule.repository.CooperativeRepository;
import com.shabarecords.farmersmodule.services.CoopMemberShipService;
import com.shabarecords.farmersmodule.services.CooperativeService;
import com.shabarecords.farmersmodule.services.FarmService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.PhoneNumberUtil;
import com.shabarecords.farmersmodule.utils.UtilGenerator;
import com.shabarecords.farmersmodule.utils.databind.*;
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
public class CooperativeServiceImpl implements CooperativeService {


    private final CooperativeRepository cooperativeRepository;
    private final FarmService farmService;
    private final CoopMemberShipService coopMemberShipService;


    @Override
    public ResponseEntity<APIResponse> addCooperative(AddCooperativeRequest request) {

        try {

            // Format PhoneNo
            String phoneNo = PhoneNumberUtil.getFormattedPhoneNumber(request.getPhoneNo());
            request.setPhoneNo(phoneNo);

            // Check for Duplicate | PhoneNo
            if (cooperativeRepository.existsByPhoneNo(request.getPhoneNo())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Cooperative with phoneNo %s already exists",
                                request.getPhoneNo())));
            }

            // Check for Duplicate | code
            if (StringUtils.hasText(request.getCode())) {

                if (cooperativeRepository.existsByCode(request.getCode())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            APIResponse.ofError(String.format("Cooperative with coopCode %s already exists",
                                    request.getCode())));
                }

            } else {
                request.setCode(UtilGenerator.getCode("C", "0123456789"));
            }

            // Check for Duplicate | email
            if (cooperativeRepository.existsByEmail(request.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Cooperative with email %s already exists",
                                request.getEmail())));
            }

            // Check for Duplicate | registrationNo
            if (cooperativeRepository.existsByRegistrationNo(request.getRegistrationNo())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Cooperative with registrationNo %s already exists",
                                request.getEmail())));
            }


            Cooperatives cooperative = cooperativeRepository.save(Cooperatives.of(request));

            // add Farms
            request.getFarms().forEach(
                    farmRequest -> {
                        farmService.addFarm(null, cooperative, farmRequest);
                    }
            );


            return ResponseEntity.ok(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity<APIResponse> updateCooperative(Long cooperativeId, UpdateCooperativeRequest updateCooperativeRequest) {
        try {

            Optional<Cooperatives> optionalCooperatives = cooperativeRepository.findById(cooperativeId);

            if (!optionalCooperatives.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Could not find Cooperative record with id %s ",
                                cooperativeId)));
            }

            Cooperatives cooperative = optionalCooperatives.get();


            // Format PhoneNo
            if (StringUtils.hasText(updateCooperativeRequest.getPhoneNo())) {
                String phoneNo = PhoneNumberUtil.getFormattedPhoneNumber(updateCooperativeRequest.getPhoneNo());
                updateCooperativeRequest.setPhoneNo(phoneNo);

                // Check for Duplicate | PhoneNo
                if (!cooperative.getPhoneNo().equals(updateCooperativeRequest.getPhoneNo()))
                    if (cooperativeRepository.existsByPhoneNo(updateCooperativeRequest.getPhoneNo())) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                APIResponse.ofError(String.format("Another Cooperative with phoneNo %s already exists",
                                        updateCooperativeRequest.getPhoneNo())));
                    }
            }

            // Check for Duplicate | registrationNo
            if (StringUtils.hasText(updateCooperativeRequest.getRegistrationNo()) &&
                    !cooperative.getRegistrationNo().equals(updateCooperativeRequest.getRegistrationNo())) {
                if (cooperativeRepository.existsByRegistrationNo(updateCooperativeRequest.getRegistrationNo())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            APIResponse.ofError(String.format("Another Cooperative with registrationNo %s already exists",
                                    updateCooperativeRequest.getRegistrationNo())));
                }

            }

            // Check for Duplicate | email
            if (StringUtils.hasText(updateCooperativeRequest.getEmail()) &&
                    !cooperative.getEmail().equals(updateCooperativeRequest.getEmail())) {
                if (cooperativeRepository.existsByEmail(updateCooperativeRequest.getEmail())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            APIResponse.ofError(String.format("Another Cooperative with email %s already exists",
                                    updateCooperativeRequest.getEmail())));
                }

            }


            // Update
            cooperativeRepository.save(Cooperatives.ofUpdate(cooperative, updateCooperativeRequest));

            return ResponseEntity.ok(APIResponse.ofSuccess());
        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }

    }


    @Override
    public ResponseEntity<APIResponse> addCoopFarmer(Long cooperativeId, AddCooperativeFarmer addCooperativeFarmer) {

        Optional<Cooperatives> cooperatives = cooperativeRepository.findById(cooperativeId);
        if (!cooperatives.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    APIResponse.ofError(String.format("Cooperative with cooperativeId %d could not be found",
                            cooperativeId)));
        }
        return coopMemberShipService.addFarmer(cooperatives.get(), addCooperativeFarmer);
    }

    @Override
    public ResponseEntity<List<CoopFarmers>> getCoopFarmers(Long cooperativeId) {
        return ResponseEntity.ok(coopMemberShipService.getCoopFarmers(cooperativeId));
    }

    @Override
    public ResponseEntity<APIResponse> addFarm(Long cooperativeId, FarmRequest farmRequest) {
        // Grower
        Optional<Cooperatives> cooperatives = cooperativeRepository.findById(cooperativeId);

        if (!cooperatives.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    APIResponse.ofError(String.format("Cooperative with cooperativeId %s does not exist",
                            cooperativeId)));
        }
        return farmService.addFarm(null, cooperatives.get(), farmRequest);
    }

    @Override
    public ResponseEntity<List<FarmData>> getCooperativeFarms(String coopCode) {
        return farmService.getCooperativeFarms(coopCode);
    }


    @Override
    public ResponseEntity<Cooperative> getCooperative(String code) {

        return ResponseEntity.ok(cooperativeRepository.getCooperative(code));
    }

    @Override
    public ResponseEntity<List<Cooperative>> getAllCoperatives() {

        return ResponseEntity.ok(cooperativeRepository.getCooperatives());

    }


}
