package com.shabarecords.farmersmodule.repository.farmer.farm;

import com.shabarecords.farmersmodule.models.farm.CoffeeTreesRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */
public interface CoffeeTreeRepository extends JpaRepository<CoffeeTreesRecord, Integer> {

    List<CoffeeTreesRecord> findAllByFarm_Id(Integer farm_id);

    CoffeeTreesRecord findFirstByCurrentRecord(boolean currentRecord);
}
