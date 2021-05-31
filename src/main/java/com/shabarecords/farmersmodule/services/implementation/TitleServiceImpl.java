package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.title.Title;
import com.shabarecords.farmersmodule.repository.title.TitleRepository;
import com.shabarecords.farmersmodule.services.TitleService;
import lombok.RequiredArgsConstructor;
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
 class TitleServiceImpl implements TitleService {

    private final TitleRepository repository;

    @Override
    public Title addTitle(Title title) {
        return repository.save(title);
    }

    @Override
    public List<Title> getTitles(PageRequest request) {
        return repository.findAll(request).getContent();
    }

    @Override
    public Title getTitle(Integer titleCode) {
        Optional<Title> title= repository.findById(titleCode);
        return title.orElse(null);
    }




}
