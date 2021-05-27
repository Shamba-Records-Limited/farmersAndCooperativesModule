package com.shabarecords.farmersmodule.services.farmer.farm;

import com.shabarecords.farmersmodule.models.farm.Farm;
import com.shabarecords.farmersmodule.repository.farmer.FarmerRepository;
import com.shabarecords.farmersmodule.repository.farmer.farm.FarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */

@Service
@RequiredArgsConstructor
 class FarmServiceImpl implements FarmService {

    private final FarmRepository repository;
    private final FarmerRepository farmerRepository;

    @Override
    public Farm addFarm(Farm farm) {
        Assert.notNull(farm, "Farm cannot be null");
        Assert.notNull(farm.getFarmer(), "Farmer cannot be null");

        Assert.notNull(farm.getFarmer().getGrowerId(), "Farmer growerId cannot be null");
        Assert.notNull(farm.getVillage(), "Village farm is located in has to be provided");



        return repository.save(farm);
    }

    @Override
    public Farm getFarm(Integer id) {
        Optional<Farm> farm = repository.findById(id);
        return farm.orElse(null);
    }

    @Override
    public List<Farm> getFarmersFarms(String growerId) {
        return repository.findAllByFarmer_GrowerId(growerId);
    }

    @Override
    public List<Farm> getFarmsInAVillage(Integer villageId, PageRequest request) {
        return  repository.findAllByVillage_Id(villageId, request);
    }

    @Override
    public List<Farm> getFarmsInASubCounty(String subCountyCode, PageRequest request) {
        return repository.findAllByVillage_SubCounty_SubCountyCode(subCountyCode, request);
    }

    @Override
    public List<Farm> getFarmsInACounty(String countyCode, PageRequest request) {
        return repository.findAllByVillage_SubCounty_County_CountyCode(countyCode, request);
    }
}
