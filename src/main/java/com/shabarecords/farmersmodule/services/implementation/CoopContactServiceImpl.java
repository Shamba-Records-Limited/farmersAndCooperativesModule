package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
import com.shabarecords.farmersmodule.repository.cooperative.CoopContactRepository;
import com.shabarecords.farmersmodule.services.CoopContactService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class CoopContactServiceImpl implements CoopContactService {

    private final CoopContactRepository repository;

    @Override
    public ResponseEntity addOrUpdateCooperative(CoopContact contact) {
        try {
            repository.updateContact(contact.getCooperative().getCode());

            repository.save(contact);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public List<CoopContact> addContacts(List<CoopContact> contacts) {
        return repository.saveAll(contacts);
    }

    @Override
    public ResponseEntity getContact(Integer contact_id) {
        try {

            Optional<CoopContact> contactPerson = repository.findById(contact_id);

            if (contactPerson.isPresent())
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(contactPerson.get());

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public List<CoopContact> getCoopContact(String coop_code) {
        return repository.findByCooperative_Code(coop_code);
    }
}
