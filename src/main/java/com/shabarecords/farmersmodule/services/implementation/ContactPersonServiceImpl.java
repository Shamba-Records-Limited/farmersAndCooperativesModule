package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
import com.shabarecords.farmersmodule.repository.cooperative.ContactPersonRepository;
import com.shabarecords.farmersmodule.services.ContactPersonService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author : Odinga David
 * @since : 5/29/21, Sat
 */

@Service
@RequiredArgsConstructor
@Slf4j
class ContactPersonServiceImpl implements ContactPersonService {

    private final ContactPersonRepository repository;

    @Override
    public ResponseEntity<APIResponse> addContactPerson(ContactPerson person) {
        try {

            repository.save(person);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity getContactPerson(Integer id) {
        try {

            Optional<ContactPerson> contactPerson = repository.findById(id);

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
    public ResponseEntity getContactPersons(String coopCode) {
        try {

            List<ContactPerson> contactPerson = repository.findAllByCooperative_Code(coopCode);

            if (contactPerson.size() > 0)
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(contactPerson);

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No records found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity getActiveContactPersons(String coopCode, boolean active) {
        try {

            List<ContactPerson> contactPerson = repository.findAllByCooperative_CodeAndActive(coopCode, active);

            if (contactPerson.size() > 0)
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(contactPerson);

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }

    @Override
    public ResponseEntity deactivateContactPersons(Integer id) {
        try {

            Optional<ContactPerson> contactPerson = repository.findById(id);

            if (contactPerson.isPresent()) {
                ContactPerson person = contactPerson.get();
                person.setActive(false);
                person.setEffectiveTo(LocalDate.now());

                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(APIResponse.ofSuccess());
            }

            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(APIResponse.ofResponse("No record found"));

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }
}
