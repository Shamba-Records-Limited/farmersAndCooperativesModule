package com.shabarecords.farmersmodule.services.implementation;

import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import com.shabarecords.farmersmodule.repository.farmer.FarmerContactRepository;
import com.shabarecords.farmersmodule.services.FarmerContactService;
import com.shabarecords.farmersmodule.utils.APIResponse;
import com.shabarecords.farmersmodule.utils.enums.ContactPriority;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@Service
@RequiredArgsConstructor
@Slf4j
 class FarmerContactServiceImpl implements FarmerContactService {

    private final FarmerContactRepository contactRepository;


    @Override
    public List<FarmerContact> getFarmerContact(String farmer_id) {
        return contactRepository.findAllByFarmer_GrowerId(farmer_id);
    }

    @Override
    public ResponseEntity addContact(FarmerContact contact) {
        try {
            if (contact.getPriority().equals(ContactPriority.PRIMARY))
            contactRepository.updateContact(contact.getFarmer().getGrowerId(), contact.getType());

            contactRepository.save(contact);

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(APIResponse.ofSuccess());

        } catch (Exception e) {
            log.error("Error : {}", e.getMessage());

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(APIResponse.ofInternalServerError());

        }
    }
}
