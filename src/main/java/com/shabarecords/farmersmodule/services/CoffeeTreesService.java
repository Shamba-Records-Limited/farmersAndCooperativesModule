package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/25/21, Tue
 */
public interface CoffeeTreesService {

    CoffeeTreesRecord addCoffeeTrees(CoffeeTreesRecord trees);
    List<CoffeeTreesRecord> getCoffeeTreesHistory(Integer farm);

    List<CoffeeTreesRecord> getFarmerCoffeeTrees();

}
