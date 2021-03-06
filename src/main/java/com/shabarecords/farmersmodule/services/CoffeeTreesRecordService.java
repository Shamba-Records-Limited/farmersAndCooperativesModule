package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/25/21, Tue
 */
public interface CoffeeTreesRecordService {

    CoffeeTreesRecord addCoffeeTrees(CoffeeTreesRecord trees);
    List<CoffeeTreesRecord> getCoffeeTreesHistory(Integer farm);

    ResponseEntity getFarmerCoffeeTrees(String growerId);

}
