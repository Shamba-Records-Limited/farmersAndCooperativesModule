package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import com.shabarecords.farmersmodule.repository.cooperative.CooperativeRepository;
import com.shabarecords.farmersmodule.services.CoopService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Service
@RequiredArgsConstructor
 class CoopServiceImpl implements CoopService {

    private final CooperativeRepository repository;

    @Override
    public Cooperative addOrUpdateCooperative(Cooperative cooperative) {
        return repository.save(cooperative);
    }

    @Override
    public Cooperative getCooperative(String code) {
        return repository.findById(code).orElse(null);
    }

    @Override
    public List<Cooperative> viewAllCooperatives(PageRequest request) {
        return repository.findAll(request).getContent();
    }
}
