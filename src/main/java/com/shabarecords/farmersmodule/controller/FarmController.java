package com.shabarecords.farmersmodule.controller;

import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;
import com.shabarecords.farmersmodule.models.farm.Farm;
import com.shabarecords.farmersmodule.services.CoffeeTreesRecordService;
import com.shabarecords.farmersmodule.services.FarmService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/27/21, Thu
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/farms")
public class FarmController {
    private final CoffeeTreesRecordService recordService;


    @PostMapping("/coffee-trees")
    public ResponseEntity<CoffeeTreesRecord> addCoffeeTrees(@RequestBody CoffeeTreesRecord treesRecord){
        return ResponseEntity.ok().body(recordService.addCoffeeTrees(treesRecord));
    }

    @PostMapping("{id}")
    public ResponseEntity<List<CoffeeTreesRecord>> viewCoffeeTrees( @PathVariable Integer id){
        return ResponseEntity.ok().body(recordService.getCoffeeTreesHistory(id));
    }
}
