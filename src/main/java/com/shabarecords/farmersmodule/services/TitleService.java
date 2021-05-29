package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.title.Title;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface TitleService {

    Title addTitle(Title title);

    List<Title> getTitles(PageRequest request);

    Title getTitle(Integer titleCode);
}
