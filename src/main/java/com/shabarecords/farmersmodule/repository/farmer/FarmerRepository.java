package com.shabarecords.farmersmodule.repository.farmer;

import com.shabarecords.farmersmodule.models.farmer.Farmer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Odinga David
 * @since : 5/20/21, Thu
 */
public interface FarmerRepository extends JpaRepository<Farmer, String> {

    boolean existsByGrowerId(String growerId);
}
