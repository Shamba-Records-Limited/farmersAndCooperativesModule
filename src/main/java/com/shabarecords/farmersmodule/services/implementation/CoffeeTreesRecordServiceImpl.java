package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;
import com.shabarecords.farmersmodule.repository.cooperative.CoopFarmersRepository;
import com.shabarecords.farmersmodule.repository.cooperative.CooperativeRepository;
import com.shabarecords.farmersmodule.repository.farmer.CoffeeTreeRepository;
import com.shabarecords.farmersmodule.services.CoffeeTreesRecordService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/27/21, Thu
 */

@Service
@RequiredArgsConstructor
@Slf4j
class CoffeeTreesRecordServiceImpl implements CoffeeTreesRecordService {
    private final CoffeeTreeRepository repository;
    private final CooperativeRepository cooperativeRepository;
    private final CoopFarmersRepository coopFarmersRepository;

    @Override
    public CoffeeTreesRecord addCoffeeTrees(CoffeeTreesRecord trees) {
        CoffeeTreesRecord trees1 = repository.findFirstByCurrentRecordAndFarm_Id(true, trees.getFarm().getId());

        if (trees1 != null){
            trees1.setCurrentRecord(false);
            repository.save(trees1);


            // Update cooperative
            Assert.notNull(trees.getFarm(), "The Farm cannot be null");
            Assert.notNull(trees.getFarm().getFarmer(), "Farmer details has to be provided");
            Assert.notNull(trees.getFarm().getFarmer().getGrowerId(), "Farmer growerID has to be provided");


        }



        return repository.save(trees);
    }

    @Override
    public List<CoffeeTreesRecord> getCoffeeTreesHistory(Integer farm) {
        return repository.findAllByFarm_Id(farm);
    }

    @Override
    public ResponseEntity getFarmerCoffeeTrees(String growerId) {
        try {

            List<CoffeeTreesRecord> treesRecords = repository.findAllByFarmFarmerGrowerId(growerId);

            if (treesRecords.size() > 0)
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(treesRecords);

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No records found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }
}
