package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
import com.shabarecords.farmersmodule.utils.APIResponse;
import org.springframework.http.ResponseEntity;

/**
 * @author : Odinga David
 * @since : 5/29/21, Sat
 */
public interface ContactPersonService {

    ResponseEntity<APIResponse> addContactPerson(ContactPerson person);

    ResponseEntity getContactPerson(Integer id);

    ResponseEntity getContactPersons(String coopCode);

    ResponseEntity getActiveContactPersons(String coopCode, boolean active);

    ResponseEntity deactivateContactPersons(Integer id);


}
