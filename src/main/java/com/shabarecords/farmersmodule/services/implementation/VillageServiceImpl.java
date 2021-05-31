package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.regions.SubCounty;
import com.shabarecords.farmersmodule.models.regions.Village;
import com.shabarecords.farmersmodule.repository.regions.SubCountyRepository;
import com.shabarecords.farmersmodule.repository.regions.VillageRepository;
import com.shabarecords.farmersmodule.services.VillageService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */

@Service
@RequiredArgsConstructor
 class VillageServiceImpl implements VillageService {

    private final VillageRepository repository;
    private final SubCountyRepository subCountyRepository;

    @SneakyThrows
    @Override
    public Village addVillage(Village village) {
        Optional<SubCounty> subCounty = subCountyRepository.findById(village.getSubCounty().getSubCountyCode());
        subCounty.orElseThrow(()->new Exception("Provided subCounty code is not valid"));

        return repository.save(village);
    }

    @Override
    public Village findVillage(Integer villageId) {
        Optional<Village> village = repository.findById(villageId);

        return village.orElse(null);
    }

    @Override
    public List<Village> findVillagesInSubCounty(String subCountyCode, PageRequest request) {
        return repository.findAllBySubCounty_SubCountyCode(subCountyCode, request);
    }
}
