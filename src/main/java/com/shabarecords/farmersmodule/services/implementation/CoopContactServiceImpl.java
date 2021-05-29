package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
import com.shabarecords.farmersmodule.repository.cooperative.CoopContactRepository;
import com.shabarecords.farmersmodule.services.CoopContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Service
@RequiredArgsConstructor
public class CoopContactServiceImpl implements CoopContactService {

    private final CoopContactRepository repository;

    @Override
    public CoopContact addOrUpdateCooperative(CoopContact contact) {
        return repository.save(contact);
    }

    @Override
    public List<CoopContact> addContacts(List<CoopContact> contacts) {
        return repository.saveAll(contacts);
    }

    @Override
    public CoopContact getContact(BigDecimal contact_id) {
        return repository.getOne(contact_id);
    }

    @Override
    public List<CoopContact> getCoopContact(String coop_code) {
        return repository.findByCooperative_Code(coop_code);
    }
}
