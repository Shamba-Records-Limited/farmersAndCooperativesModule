package com.shabarecords.farmersmodule.repository;


import com.shabarecords.farmersmodule.models.CooperativeMemberShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperativeMemberShipRepository extends JpaRepository<CooperativeMemberShip, Long> {
}
