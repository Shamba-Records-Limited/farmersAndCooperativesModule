package com.shabarecords.farmersmodule.services.cooperative.contact;

import com.shabarecords.farmersmodule.models.cooperative.CoopContact;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */
public interface CoopContactService {

    CoopContact addOrUpdateCooperative(CoopContact contact);

    List<CoopContact> addContacts(List<CoopContact> contacts);

    CoopContact getContact(BigDecimal contact_id);

    List<CoopContact> getCoopContact(String coop_code);
}
