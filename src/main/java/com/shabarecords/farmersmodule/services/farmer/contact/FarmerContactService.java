package com.shabarecords.farmersmodule.services.farmer.contact;

import com.shabarecords.farmersmodule.models.farmer.FarmerContact;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/26/21, Wed
 */
public interface FarmerContactService {


    List<FarmerContact> getFarmerContact(String farmer_id);

    FarmerContact addContact(FarmerContact contact);
}
