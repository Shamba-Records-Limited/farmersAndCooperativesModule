package com.shabarecords.farmersmodule.repository;


import com.shabarecords.farmersmodule.models.Farmers;
import com.shabarecords.farmersmodule.utils.databind.Farmer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */

@Repository
public interface FarmerRepository extends JpaRepository<Farmers, Long> {

    boolean existsByPrimaryPhone(String phoneNo);

    boolean existsByCode(String code);

    boolean existsByNationalId(String nationalId);

    Optional<Farmer> findByCode(String code);


    Page<Farmer> findBy(Pageable pageable);
}
