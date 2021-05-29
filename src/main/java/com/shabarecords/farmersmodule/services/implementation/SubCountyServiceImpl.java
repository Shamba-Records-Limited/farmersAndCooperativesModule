package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.regions.SubCounty;
import com.shabarecords.farmersmodule.repository.regions.SubCountyRepository;
import com.shabarecords.farmersmodule.services.SubCountyService;
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
 class SubCountyServiceImpl implements SubCountyService {

    private final SubCountyRepository repository;

    @SneakyThrows
    @Override
    public SubCounty addSubCounty(SubCounty subCounty) {
        if (repository.existsBySubCountyCodeOrName(subCounty.getSubCountyCode(), subCounty.getName()))
            throw new Exception("Subcounty with provided code or name already exists");

        return repository.save(subCounty);
    }

    @Override
    public List<SubCounty> addSubCounties(List<SubCounty> subCounties) {
        return null;
    }

    @Override
    public SubCounty getSubCounty(String subCountyCode) {

        Optional<SubCounty>  subCounty = repository.findById(subCountyCode);

        return subCounty.orElse(null);
    }

    @Override
    public List<SubCounty> getSubCounties(PageRequest request) {
        return repository.findAll(request).getContent();
    }

    @Override
    public List<SubCounty> getSubCounties(String countyCode) {
        return repository.findAllByCounty_CountyCode(countyCode);
    }
}
