package com.shabarecords.farmersmodule.controller.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import com.shabarecords.farmersmodule.services.cooperative.CoopService;
import com.shabarecords.farmersmodule.services.cooperative.contact.CoopContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@RestController
@RequestMapping("/cooperative")
@RequiredArgsConstructor
public class CooperativeController {

    private final CoopContactService contactService;
    private final CoopService coopService;

    @PostMapping
    public ResponseEntity<Cooperative> viewCooperative(@RequestBody Cooperative cooperative) {

        return new ResponseEntity<>(coopService.addOrUpdateCooperative(cooperative), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Cooperative> updateCooperative(@RequestBody Cooperative cooperative) {

        return new ResponseEntity<>(coopService.addOrUpdateCooperative(cooperative), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Cooperative>> viewAllCooperatives(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(coopService.viewAllCooperatives(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Cooperative> getCooperative(@PathVariable String code){
        return new ResponseEntity<>(coopService.getCooperative(code), HttpStatus.OK);
    }

    @PostMapping("/{code}/contact")
    public ResponseEntity<CoopContact> addContact(
            @RequestBody CoopContact contact,
            @PathVariable String code) {


        contact.setCooperative(coopService.getCooperative(code));

        contact = contactService.addOrUpdateCooperative(contact);

        return new ResponseEntity<>(contact, HttpStatus.OK);

    }

    @PostMapping("/{code}/contacts/add-all")
    public ResponseEntity<List<CoopContact>> addAllContact(
            @RequestBody List<CoopContact> contacts,
            @PathVariable String code) {

        Cooperative  cooperative = coopService.getCooperative(code);
        contacts.forEach(contact -> contact.setCooperative(cooperative));
        contactService.addContacts(contacts);

        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{code}/contacts")
    public ResponseEntity<List<CoopContact>> viewCoopContacts(@PathVariable String code){

        return new ResponseEntity<>(contactService.getCoopContact(code), HttpStatus.OK);
    }


}
