package com.shabarecords.farmersmodule.repository.cooperative;

import com.shabarecords.farmersmodule.models.cooperative.CoopContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */
public interface CoopContactRepository extends JpaRepository<CoopContact, BigDecimal> {
    List<CoopContact> findByCooperative_Code(String cooperative_code);
}
