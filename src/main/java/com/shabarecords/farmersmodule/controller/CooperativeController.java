//package com.shabarecords.farmersmodule.controller;
//
//import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
//import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
//import com.shabarecords.farmersmodule.models.cooperative.CooperativeFarmers;
//import com.shabarecords.farmersmodule.models.farmer.Farmer;
//import com.shabarecords.farmersmodule.services.*;
//import com.shabarecords.farmersmodule.utils.APIResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author : Odinga David
// * @since : 5/22/21, Sat
// */
//
//@RestController
//@RequestMapping("/cooperative")
//@RequiredArgsConstructor
//public class CooperativeController {
//
//    private final CoopContactService contactService;
//    private final CoopService coopService;
//    private final CoopFarmersService coopFarmersService;
//    private final FarmerService farmerService;
//    private final ContactPersonService contactPersonService;
//
//    @PostMapping
//    public ResponseEntity<APIResponse> addCooperative(@RequestBody Cooperative cooperative) {
//
//        return new ResponseEntity<>(coopService.addOrUpdateCooperative(cooperative), HttpStatus.OK);
//    }
//
//    @PutMapping
//    public ResponseEntity<Cooperative> updateCooperative(@RequestBody Cooperative cooperative) {
//
//        return new ResponseEntity<>(coopService.addOrUpdateCooperative(cooperative), HttpStatus.OK);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<Cooperative>> viewAllCooperatives(@RequestParam int page, @RequestParam int size){
//        return new ResponseEntity<>(coopService.viewAllCooperatives(PageRequest.of(page, size)), HttpStatus.OK);
//    }
//
//    @GetMapping("/{code}")
//    public ResponseEntity<Cooperative> getCooperative(@PathVariable String code){
//        return new ResponseEntity<>(coopService.getCooperative(code), HttpStatus.OK);
//    }
//
//    @PostMapping("/{code}/contact")
//    public ResponseEntity addContact(
//            @RequestBody CoopContact contact,
//            @PathVariable String code) {
//
//
//        contact.setCooperative(coopService.getCooperative(code));
//
//        return contactService.addOrUpdateCooperative(contact);
//
//
//
//    }
//
//    @PostMapping("/{code}/contacts/add-all")
//    public ResponseEntity<List<CoopContact>> addAllContact(
//            @RequestBody List<CoopContact> contacts,
//            @PathVariable String code) {
//
//        Cooperative  cooperative = coopService.getCooperative(code);
//        contacts.forEach(contact -> contact.setCooperative(cooperative));
//        contactService.addContacts(contacts);
//
//        return new ResponseEntity<>(contacts, HttpStatus.OK);
//    }
//
//    @GetMapping("/{code}/contacts")
//    public ResponseEntity<List<CoopContact>> viewCoopContacts(@PathVariable String code){
//
//        return new ResponseEntity<>(contactService.getCoopContact(code), HttpStatus.OK);
//    }
//
//
//    @PostMapping("/{code}/farmers/{growerCode}")
//    public ResponseEntity<APIResponse> addCoopFarmer(@PathVariable String code,
//                                                     @PathVariable String growerCode){
//        CooperativeFarmers farmers = new CooperativeFarmers();
//        farmers.setCooperative(coopService.getCooperative(code));
//        farmers.setFarmer(farmerService.getFarmer(growerCode));
//
//        return coopFarmersService.addCooperativeFarmer(farmers);
//    }
//
//    @GetMapping("/{code}/farmers")
//    public ResponseEntity viewCoopFarmer(@PathVariable String code,
//                                         @RequestParam int page,
//                                         @RequestParam int size,
//                                         @RequestParam(required = false) String active){
//
//        PageRequest request = PageRequest.of(page,  size);
//
//        if  (active != null && (active.equalsIgnoreCase("yes") || active.equalsIgnoreCase("no")))
//          return coopFarmersService.getActiveCooperativeFarmers(code, active.equalsIgnoreCase("yes"), request);
//
//        return coopFarmersService.getCooperativeFarmers(code, request);
//    }
//
//
//    @DeleteMapping("/{code}/farmers/{growerCode}")
//    public ResponseEntity<APIResponse> viewActiveCoopFarmer(@PathVariable String code, @PathVariable String growerCode){
//
//        return  coopFarmersService.deactivateFarmer(code, growerCode);
//    }
//
//
//    @PostMapping("/{code}/contact-person")
//    public ResponseEntity<APIResponse> addContactPerson(@PathVariable String code,
//                                                     @RequestBody ContactPerson contactPerson){
//
//        Cooperative cooperative = coopService.getCooperative(code);
//        if (cooperative == null)
//            return ResponseEntity
//                    .status(HttpStatus.EXPECTATION_FAILED)
//                    .body(APIResponse.ofResponse("Provided cooperative code is not valid"));
//
//         contactPerson.setCooperative(cooperative);
//
//        return contactPersonService.addContactPerson(contactPerson);
//    }
//
//    @GetMapping("/contact-person/{id}")
//    public ResponseEntity viewContactPerson(@PathVariable Integer id){
//        return contactPersonService.getContactPerson(id);
//    }
//
//    @DeleteMapping("/contact-person/{id}")
//    public ResponseEntity deleteContactPerson(@PathVariable Integer id){
//        return contactPersonService.deactivateContactPersons(id);
//    }
//
//
//    @GetMapping("/{code}/contact-person")
//    public ResponseEntity viewCooperativeContactPerson(@PathVariable String code,
//                                                       @RequestParam String active){
//
//        if  (active != null && (active.equalsIgnoreCase("yes") || active.equalsIgnoreCase("no")))
//            return contactPersonService.getActiveContactPersons(code, active.equalsIgnoreCase("yes"));
//
//        return contactPersonService.getContactPersons(code);
//    }
//
//}
