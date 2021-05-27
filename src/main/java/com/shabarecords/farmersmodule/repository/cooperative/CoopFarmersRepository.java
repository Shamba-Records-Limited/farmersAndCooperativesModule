package com.shabarecords.farmersmodule.repository.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.CooperativeFarmers;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface CoopFarmersRepository extends JpaRepository<CooperativeFarmers, Integer> {

    CooperativeFarmers findFirstByFarmer_GrowerIdAndActive(String farmer_growerId, boolean active);
}
