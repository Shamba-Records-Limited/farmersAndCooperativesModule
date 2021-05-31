package com.shabarecords.farmersmodule.controller;

import com.shabarecords.farmersmodule.models.farm.Farm;
import com.shabarecords.farmersmodule.models.regions.SubCounty;
import com.shabarecords.farmersmodule.models.regions.Village;
import com.shabarecords.farmersmodule.services.FarmService;
import com.shabarecords.farmersmodule.services.SubCountyService;
import com.shabarecords.farmersmodule.services.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/26/21, Wed
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/villages")
public class VillageController {

    private final VillageService villageService;
    private final SubCountyService subCountyService;
    private final FarmService farmService;

    @PostMapping
    public ResponseEntity<Village>  addVillage(@RequestParam String name, @RequestParam String subCountyCode){
        SubCounty subCounty = subCountyService.getSubCounty(subCountyCode);

        Assert.notNull(subCounty, "The subCounty code provided is not valid");

        Village village = new Village();
        village.setSubCounty(subCounty);
        village.setName(name);

        return new ResponseEntity<>(villageService.addVillage(village), HttpStatus.OK);

    }

    @GetMapping("/{villageId}")
    public ResponseEntity<Village> viewVillage(@PathVariable Integer villageId){
        return new ResponseEntity<>(villageService.findVillage(villageId), HttpStatus.OK);
    }

    @GetMapping("/{villageId}/farms")
    public ResponseEntity<List<Farm>> viewVillageFarms(@PathVariable Integer villageId,
                                                 @RequestParam int page,
                                                 @RequestParam int size){

        return new ResponseEntity<>(farmService.getFarmsInAVillage(villageId, PageRequest.of(page, size)), HttpStatus.OK);
    }

}
