package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/26/21, Wed
 */
public interface FarmerContactService {


    List<FarmerContact> getFarmerContact(String farmer_id);

    ResponseEntity addContact(FarmerContact contact);
}
