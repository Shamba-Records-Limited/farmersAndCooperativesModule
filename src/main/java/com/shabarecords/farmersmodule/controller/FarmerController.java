package com.shabarecords.farmersmodule.controller;

import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;
import com.shabarecords.farmersmodule.models.farm.Farm;
import com.shabarecords.farmersmodule.models.farmer.Farmer;
import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import com.shabarecords.farmersmodule.models.regions.Village;
import com.shabarecords.farmersmodule.services.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/farmers")
public class FarmerController {

    private final FarmService farmService;
    private final FarmerService farmerService;
    private final FarmerContactService contactService;
    private final CoffeeTreesRecordService recordService;
    private final VillageService villageService;

    @GetMapping("/{growerCode}")
    public ResponseEntity<Farmer> viewFarmer(@PathVariable String growerCode) {
        return new ResponseEntity<>(farmerService.getFarmer(growerCode), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Farmer>> viewAllFarmers(@RequestParam int page, @RequestParam int size) {

        List<Farmer> farmers = farmerService.getAllFarmers(PageRequest.of(page, size));

        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer farmer, @RequestParam Integer villageId) {
        farmer.setVillage(villageService.findVillage(villageId));
        return new ResponseEntity<>(farmerService.addFarmer(farmer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer) {

        return new ResponseEntity<>(farmerService.updateFarmer(farmer), HttpStatus.OK);
    }


    @PostMapping("/{growerCode}/contact")
    public ResponseEntity addFarmerContact(
            @RequestBody FarmerContact contact,
            @PathVariable String growerCode){

        contact.setFarmer(farmerService.getFarmer(growerCode));

        return contactService.addContact(contact);
    }


    @GetMapping("/{growerCode}/contacts")
    public ResponseEntity<List<FarmerContact>> getFarmerContacts(@PathVariable String growerCode){

        return new ResponseEntity<>(contactService.getFarmerContact(growerCode), HttpStatus.OK);
    }

    @PostMapping("/{growerCode}/farm")
    public ResponseEntity addFarm(
            @RequestBody @NotNull Farm farm,
            @PathVariable String growerCode){

        farm.setFarmer(farmerService.getFarmer(growerCode));

        return farmService.addFarm(farm);
    }


    @GetMapping("/farm/{id}")
    public ResponseEntity viewFarm(@PathVariable Integer id){
        return ResponseEntity.ok().body(farmService.getFarm(id));
    }

    @GetMapping("/{growerCode}/farm")
    public ResponseEntity<List<Farm>> viewFarms(@PathVariable String growerCode){

        return ResponseEntity.ok().body(farmService.getFarmersFarms(growerCode));
    }


    @GetMapping("/{growerCode}/coffee-trees")
    public ResponseEntity viewCofeeTrees(@PathVariable String growerCode){

        return recordService.getFarmerCoffeeTrees(growerCode);
    }



}
