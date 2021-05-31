package com.shabarecords.farmersmodule.repository.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import com.shabarecords.farmersmodule.models.cooperative.CooperativeFarmers;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
public interface CoopFarmersRepository extends JpaRepository<CooperativeFarmers, Long> {

    boolean existsByFarmerGrowerIdAndCooperativeCodeAndActive(String farmer_growerId, String cooperative_code, boolean active);

    CooperativeFarmers findFirstByFarmerGrowerIdAndCooperativeCodeAndActive(String farmer_growerId, String cooperative_code, boolean active);

    CooperativeFarmers findFirstByFarmer_GrowerIdAndActive(String farmer_growerId, boolean active);

    List<CooperativeFarmers> findAllByCooperative_CodeAndActive(String cooperative_code, boolean active, Pageable pageRequest);

    List<CooperativeFarmers> findAllByCooperativeCode(String cooperative_code, Pageable request);
}
