package com.shabarecords.farmersmodule.controller;

import com.shabarecords.farmersmodule.models.farmer.Farmer;
import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import com.shabarecords.farmersmodule.services.FarmerService;
import com.shabarecords.farmersmodule.services.FarmerContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/farmers")
public class FarmerController {

    private final FarmerService farmerService;
    private final FarmerContactService contactService;

    @GetMapping("/{farmer_id}")
    public ResponseEntity<Farmer> viewFarmer(@PathVariable String farmer_id) {
        return new ResponseEntity<>(farmerService.getFarmer(farmer_id), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<Farmer>> viewAllFarmer(@RequestParam int page, @RequestParam int size) {

        List<Farmer> farmers = farmerService.getAllFarmers(PageRequest.of(page, size));

        return new ResponseEntity<>(farmers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer farmer) {

        return new ResponseEntity<>(farmerService.addFarmer(farmer), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer) {

        return new ResponseEntity<>(farmerService.updateFarmer(farmer), HttpStatus.OK);
    }


    @PostMapping("/{farmer_id}/add-contact")
    public ResponseEntity<FarmerContact> addFarmerContact(
            @RequestBody FarmerContact contact,
            @PathVariable String farmer_id){

        contact.setFarmer(farmerService.getFarmer(farmer_id));

        contact = contactService.addContact(contact);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }


    @GetMapping("/{farmer_id}/contacts")
    public ResponseEntity<List<FarmerContact>> getFarmerContacts(@PathVariable String farmer_id){

        return new ResponseEntity<>(contactService.getFarmerContact(farmer_id), HttpStatus.OK);
    }



}
