package com.shabarecords.farmersmodule.repository.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface ContactPersonRepository extends JpaRepository<ContactPerson, Integer> {

    List<ContactPerson> findAllByCooperative_Code(String cooperative_code);

    List<ContactPerson> findAllByCooperative_CodeAndActive(String cooperative_code, boolean active);
}
