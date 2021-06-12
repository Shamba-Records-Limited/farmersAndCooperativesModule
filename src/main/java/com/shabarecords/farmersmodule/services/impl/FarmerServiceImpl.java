package com.shabarecords.farmersmodule.services.impl;

import com.shabarecords.farmersmodule.models.Farmers;
import com.shabarecords.farmersmodule.models.Region;
import com.shabarecords.farmersmodule.repository.FarmerRepository;
import com.shabarecords.farmersmodule.repository.RegionRepository;
import com.shabarecords.farmersmodule.services.FarmService;
import com.shabarecords.farmersmodule.services.FarmerService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.PhoneNumberUtil;
import com.shabarecords.farmersmodule.utils.UtilGenerator;
import com.shabarecords.farmersmodule.utils.databind.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;
    private final RegionRepository regionRepository;

    @Autowired
    private FarmService farmService;

    @Override
    public ResponseEntity<APIResponse> addFarmer(AddFarmerRequest request) {

        try {

            // Format PhoneNo
            String phoneNo = PhoneNumberUtil.getFormattedPhoneNumber(request.getPrimaryPhone());
            request.setPrimaryPhone(phoneNo);

            // Check for Duplicate | PhoneNo
            if (farmerRepository.existsByPrimaryPhone(request.getPrimaryPhone())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Farmer with primaryPhone %s already exists",
                                request.getPrimaryPhone())));
            }

            // Check for Duplicate | code
            if (StringUtils.hasText(request.getCode())) {

                if (farmerRepository.existsByCode(request.getCode())) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                            APIResponse.ofError(String.format("Farmer with growerCode %s already exists",
                                    request.getCode())));
                }

            } else {
                request.setCode(UtilGenerator.getCode("F", "0123456789"));
            }

            // Check for Duplicate | nationalId
            if (farmerRepository.existsByNationalId(request.getNationalId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        APIResponse.ofError(String.format("Farmer with nationalId %s already exists",
                                request.getNationalId())));
            }

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


            Farmers farmers = farmerRepository.save(Farmers.of(request, region));

            // add Farms
            request.getFarms().forEach(
                    farmRequest -> {
                        farmService.addFarm(farmers, farmRequest);
                    }
            );


            return ResponseEntity.ok(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }


    @Override
    public ResponseEntity<APIResponse> updateFarmer(Long farmerId, UpdateFarmerRequest updateRequest) {
//        try {
//
//            Optional<Farmers> farmers = farmerRepository.findById(farmerId);
//
//            if (!farmers.isPresent()) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                        APIResponse.ofError(String.format("Could not find Farmer record with id %s ",
//                                farmerId)));
//            }
//
//            Farmers farmer = farmers.get();
//
//
//            // Format PhoneNo
//            String phoneNo = PhoneNumberUtil.getFormattedPhoneNumber(userRequest.getPhoneNo());
//            userRequest.setPhoneNo(phoneNo);
//
//            // Check for Duplicate | PhoneNo
//            if (!user.getPhoneNo().equals(userRequest.getPhoneNo()))
//                if (usersRepository.existsByPhoneNo(userRequest.getPhoneNo())) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                            APIResponse.ofError(String.format("User with phoneNo %s already exists",
//                                    userRequest.getPhoneNo())));
//                }
//
//            // Check for Duplicate | Email
//            if (StringUtils.hasText(userRequest.getEmail()) && !user.getEmail().equals(userRequest.getEmail()))
//                if (usersRepository.existsByEmail(userRequest.getEmail())) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                            APIResponse.ofError(String.format("User with email %s already exists",
//                                    userRequest.getEmail())));
//                }
//
//
//            // Check for Duplicate | IDNumber
//            if (!user.getIdNumber().equals(userRequest.getIdNumber()))
//                if (usersRepository.existsByIdNumber(userRequest.getIdNumber())) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                            APIResponse.ofError(String.format("User with IDNumber %s already exists",
//                                    userRequest.getIdNumber())));
//                }
//
//            // check Branch Exist
//            Branches branches = null;
//            if (StringUtils.hasText(userRequest.getBranchCode())) {
//                Optional<Branches> optionalBranches = branchesRepository.findById(userRequest.getBranchCode());
//                if (!optionalBranches.isPresent()) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                            APIResponse.ofError(String.format("Branch with branchCode %s could not be found",
//                                    userRequest.getBranchCode())));
//
//                }
//
//                branches = optionalBranches.get();
//            }
//
//            // check department Exist
//            Departments departments = null;
//            if (StringUtils.hasText(userRequest.getDeptCode())) {
//                Optional<Departments> optionalDepartments = departmentsRepository.findById(userRequest.getDeptCode());
//                if (!optionalDepartments.isPresent()) {
//                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
//                            APIResponse.ofError(String.format("Department with deptCode %s could not be found",
//                                    userRequest.getDeptCode())));
//
//                }
//
//                departments = optionalDepartments.get();
//            }
//
//            // save
//            Users usr = usersRepository.save(Users.ofUpdate(user, userRequest, departments, branches));
//
//            // update userGroup
//            updateUserGroups(usr.getUserId(), userRequest.getUserGroups().stream().map(UserGroup::getGroupCode).collect(Collectors.toSet()));
//
//            return ResponseEntity.ok(APIResponse.ofSuccess());
//        } catch (Exception e) {
//            log.error("Error : {}", e.getMessage());
//
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());
//
//        }

        return ResponseEntity.ok(APIResponse.ofSuccess());
    }


    @Override
    public ResponseEntity getFarmer(String growerCode) {
        try {
            Optional<Farmer> farmer = farmerRepository.findByCode(growerCode);
            if (farmer.isPresent())
                return ResponseEntity.ok(farmer.get());


            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }

    }

    @Override
    public ResponseEntity<Page<Farmer>> getFarmers(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.DESC, "dateCreated"));
        return ResponseEntity.ok(farmerRepository.findBy(pageable));
    }

    @Override
    public ResponseEntity<APIResponse> addFarm(Long farmerId, FarmRequest farmRequest) {
        ResponseEntity<APIResponse> response = null;

        // Grower
        Optional<Farmers> optionalFarmers = farmerRepository.findById(farmerId);

        if (!optionalFarmers.isPresent()) {
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    APIResponse.ofError(String.format("Farmer with farmerId %s already exists",
                            farmerId)));
        } else {
            response = farmService.addFarm(optionalFarmers.get(), farmRequest);
        }


        return response;
    }

    @Override
    public ResponseEntity<List<FarmData>> getGrowerFarms(String growerCode) {
        return farmService.getGrowerFarms(growerCode);
    }
}
