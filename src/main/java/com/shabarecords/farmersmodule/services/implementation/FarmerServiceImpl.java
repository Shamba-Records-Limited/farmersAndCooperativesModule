package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.farmer.Farmer;
import com.shabarecords.farmersmodule.repository.farmer.FarmerRepository;
import com.shabarecords.farmersmodule.services.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@Service
@RequiredArgsConstructor
 class FarmerServiceImpl implements FarmerService {

    private final FarmerRepository farmerRepository;

    @Override
    public Farmer getFarmer(String farmer_id) {
        return farmerRepository.getOne(farmer_id);
    }

    @Override
    public List<Farmer> getAllFarmers(PageRequest request) {
        return farmerRepository.findAll();
    }

    @Override
    public Farmer updateFarmer(Farmer farmer) {
        Assert.notNull(farmer, "Farmer cannot be null");
        Assert.notNull(farmer.getGrowerId(), "Please provide grower ID");


        return addFarmer(farmer);
    }

    @Override
    public Farmer addFarmer(Farmer farmer) {

        Farmer farmer1 = new Farmer();
        farmerRepository.save(farmer1);

        return farmer;
    }
}
