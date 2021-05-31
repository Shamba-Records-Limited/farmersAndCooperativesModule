package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.regions.County;
import com.shabarecords.farmersmodule.repository.regions.CountyRepository;
import com.shabarecords.farmersmodule.services.CountyService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
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
 class CountyServiceImpl implements CountyService {

    private final CountyRepository repository;

    @SneakyThrows
    @Override
    public County saveOrUpdateCounty(County county) {

        Assert.notNull(county, "County cannot be null");
        Assert.notNull(county.getCountyCode(), "Please provide county code");
        Assert.notNull(county.getName(), "County name cannot be null");

        if(repository.existsByCountyCode(county.getCountyCode()))
            throw new Exception("County with county "+county.getCountyCode()+" already exists");

        if (repository.existsByName(county.getName().trim()))
            throw new Exception(county.getName()+" already added");

        return repository.save(county);
    }

    @Override
    public County getCounty(String countyCode) {

        Optional<County> county= repository.findById(countyCode);

        return county.orElse(null);
    }

    @Override
    public List<County> addCounties(List<County> counties) {
        return null;
    }

    @Override
    public List<County> getCounties( ) {
        return repository.findAll();
    }
}
