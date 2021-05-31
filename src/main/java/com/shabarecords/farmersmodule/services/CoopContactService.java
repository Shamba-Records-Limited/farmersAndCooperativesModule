package com.shabarecords.farmersmodule.services;

import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */
public interface CoopContactService {

    ResponseEntity addOrUpdateCooperative(CoopContact contact);

    List<CoopContact> addContacts(List<CoopContact> contacts);

    ResponseEntity getContact(Integer contact_id);

    List<CoopContact> getCoopContact(String coop_code);
}
