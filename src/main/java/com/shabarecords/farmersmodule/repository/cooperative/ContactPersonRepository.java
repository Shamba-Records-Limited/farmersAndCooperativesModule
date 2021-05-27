package com.shabarecords.farmersmodule.repository.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface ContactPersonRepository extends JpaRepository<ContactPerson, Integer> {
}
