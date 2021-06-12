package com.shabarecords.farmersmodule.repository;

import com.shabarecords.farmersmodule.models.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */
@Repository
public interface CooperativeRepository extends JpaRepository<Cooperative, Long> {
}
