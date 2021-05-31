package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

public interface CoopService {

    Cooperative addOrUpdateCooperative(Cooperative cooperative);

    Cooperative getCooperative(String code);

    List<Cooperative> viewAllCooperatives(PageRequest request);
}
