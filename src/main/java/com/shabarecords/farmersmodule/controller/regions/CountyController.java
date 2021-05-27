package com.shabarecords.farmersmodule.controller.regions;

import com.shabarecords.farmersmodule.models.regions.County;
import com.shabarecords.farmersmodule.models.regions.SubCounty;
import com.shabarecords.farmersmodule.models.regions.Village;
import com.shabarecords.farmersmodule.services.regions.CountyService;
import com.shabarecords.farmersmodule.services.regions.SubCountyService;
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
@RequestMapping(path = "/counties")
public class CountyController {

    private final CountyService countyService;
    private final SubCountyService subCountyService;


    @PostMapping
    public ResponseEntity<County> addCounty(@RequestBody County county){
        return new ResponseEntity<>(countyService.saveOrUpdateCounty(county), HttpStatus.OK);
    }

    @GetMapping("/{countyCode}")
    public ResponseEntity<County> viewCounty(@PathVariable  String countyCode){
        return new ResponseEntity<>(countyService.getCounty(countyCode), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<County>> viewCounties(){
        return new ResponseEntity<>(countyService.getCounties(), HttpStatus.OK);
    }

    @GetMapping("/{countyCode}/sub-counties")
    public ResponseEntity<List<SubCounty>> viewSubCountiesInACounty(@PathVariable String countyCode){

        return new ResponseEntity<>(subCountyService.getSubCounties(countyCode), HttpStatus.OK);

    }
}
