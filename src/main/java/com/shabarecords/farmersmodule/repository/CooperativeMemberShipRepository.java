package com.shabarecords.farmersmodule.repository;


import com.shabarecords.farmersmodule.models.CooperativeMemberShip;
import com.shabarecords.farmersmodule.models.Cooperatives;
import com.shabarecords.farmersmodule.utils.databind.CoopFarmers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CooperativeMemberShipRepository extends JpaRepository<CooperativeMemberShip, Long> {

    boolean existsByCooperativeAndCoopMemberShipNo(Cooperatives cooperative,String membership);

    List<CoopFarmers> findByCooperative_Id(Long coopId);
}
