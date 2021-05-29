package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;
import com.shabarecords.farmersmodule.repository.cooperative.CoopFarmersRepository;
import com.shabarecords.farmersmodule.repository.cooperative.CooperativeRepository;
import com.shabarecords.farmersmodule.repository.farmer.CoffeeTreeRepository;
import com.shabarecords.farmersmodule.services.CoffeeTreesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/27/21, Thu
 */

@Service
@RequiredArgsConstructor
class CoffeeTreesServiceImpl implements CoffeeTreesService {
    private final CoffeeTreeRepository repository;
    private final CooperativeRepository cooperativeRepository;
    private final CoopFarmersRepository coopFarmersRepository;

    @Override
    public CoffeeTreesRecord addCoffeeTrees(CoffeeTreesRecord trees) {
        CoffeeTreesRecord trees1 = repository.findFirstByCurrentRecord(true);

        if (trees1 != null){
            trees1.setCurrentRecord(false);
            repository.save(trees1);


            // Update cooperative
            Assert.notNull(trees.getFarm(), "The Farm cannot be null");
            Assert.notNull(trees.getFarm().getFarmer(), "Farmer details has to be provided");
            Assert.notNull(trees.getFarm().getFarmer().getGrowerId(), "Farmer growerID has to be provided");


            Cooperative cooperative = coopFarmersRepository.findFirstByFarmer_GrowerIdAndActive(
                    trees.getFarm()
                            .getFarmer()
                            .getGrowerId(), true).getCooperative();

            if (cooperative != null) {
                cooperative.setNumberOfTrees(-trees1.getNumberOfTrees()+trees.getNumberOfTrees());
                cooperativeRepository.save(cooperative);

            }
        }



        return repository.save(trees);
    }

    @Override
    public List<CoffeeTreesRecord> getCoffeeTreesHistory(Integer farm) {
        return repository.findAllByFarm_Id(farm);
    }

    @Override
    public List<CoffeeTreesRecord> getFarmerCoffeeTrees() {
        return null;
    }
}
