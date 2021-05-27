package com.shabarecords.farmersmodule.controller.regions;

import com.shabarecords.farmersmodule.models.regions.SubCounty;
import com.shabarecords.farmersmodule.models.regions.Village;
import com.shabarecords.farmersmodule.services.regions.SubCountyService;
import com.shabarecords.farmersmodule.services.regions.VillageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/26/21, Wed
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sub-counties")
public class SubCountyController {

    private final SubCountyService subCountyService;
    private final VillageService villageService;

    @PostMapping
    public ResponseEntity<SubCounty> addSubCounty(@RequestBody SubCounty subCounty){
       return new ResponseEntity<>(subCountyService.addSubCounty(subCounty), HttpStatus.OK);
    }

    @GetMapping("/{subCountyCode}")
    public ResponseEntity<SubCounty> viewSubCounty(@PathVariable String subCountyCode){
        return new ResponseEntity<>(subCountyService.getSubCounty(subCountyCode), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SubCounty>> viewSubCounties(@RequestParam int page,@RequestParam int size){
        return new ResponseEntity<>(subCountyService.getSubCounties(PageRequest.of(page, size)), HttpStatus.OK);
    }


    @GetMapping("/{subCountyCode}/villages")
    public ResponseEntity<List<Village>> viewVillagesInASubCounty(
            @PathVariable String subCountyCode,
            @RequestParam int page,
            @RequestParam int size){

        return new ResponseEntity<>(villageService.findVillagesInSubCounty(subCountyCode, PageRequest.of(page, size)), HttpStatus.OK);

    }


}
