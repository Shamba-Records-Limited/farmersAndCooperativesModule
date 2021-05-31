package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.CooperativeFarmers;
import com.shabarecords.farmersmodule.repository.cooperative.CoopFarmersRepository;
import com.shabarecords.farmersmodule.services.CoopFarmersService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Odinga David
 * @since : 5/29/21, Sat
 */

@Service
@RequiredArgsConstructor
@Slf4j
class CoopFarmersServiceImpl implements CoopFarmersService {

    private final CoopFarmersRepository repository;

    @Override
    public ResponseEntity<APIResponse> addCooperativeFarmer(CooperativeFarmers cooperative) {
        try {

            repository.save(cooperative);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity getCooperativeFarmers(String coopCode, PageRequest pageRequest) {
        try {
            List<CooperativeFarmers> farmers= repository.findAllByCooperativeCode(coopCode, pageRequest);

            if (farmers.size() > 0)
                return ResponseEntity.ok(farmers);

            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(APIResponse.ofInternalServerError());

        }
    }


    @Override
    public ResponseEntity getActiveCooperativeFarmers(String cooperativeCode, boolean active, PageRequest pageRequest) {

        try {
            List<CooperativeFarmers> farmers= repository.findAllByCooperative_CodeAndActive(cooperativeCode, active, pageRequest);

            if (farmers.size() > 0)
                return ResponseEntity.ok(farmers.stream().map(CooperativeFarmers::getFarmer).collect(Collectors.toList()));

            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity<APIResponse> deactivateFarmer(String code,  String growerCode) {
        try {

            if (repository.existsByFarmerGrowerIdAndCooperativeCodeAndActive(growerCode, code, true)) {

                CooperativeFarmers farmer1 = repository
                        .findFirstByFarmerGrowerIdAndCooperativeCodeAndActive(growerCode, code, true);
                farmer1.setActive(false);
                farmer1.setEffectiveTo(LocalDate.now());

                repository.save(farmer1);

                return ResponseEntity.ok(APIResponse.ofSuccess());

            }

            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(APIResponse.ofInternalServerError());

        }
    }
}
