package com.shabarecords.farmersmodule.services.farmer.contact;

import com.shabarecords.farmersmodule.models.farmer.FarmerContact;
import com.shabarecords.farmersmodule.repository.farmer.FarmerContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@Service
@RequiredArgsConstructor
 class FarmerContactServiceImpl implements FarmerContactService {

    private final FarmerContactRepository contactRepository;


    @Override
    public List<FarmerContact> getFarmerContact(String farmer_id) {
        return contactRepository.findAllByFarmer_GrowerId(farmer_id);
    }

    @Override
    public FarmerContact addContact(FarmerContact contact) {
        return contactRepository.save(contact);
    }
}
