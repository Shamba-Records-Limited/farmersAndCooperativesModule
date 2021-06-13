package com.shabarecords.farmersmodule.repository;

import com.shabarecords.farmersmodule.models.Cooperatives;
import com.shabarecords.farmersmodule.utils.databind.Cooperative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */
@Repository
public interface CooperativeRepository extends JpaRepository<Cooperatives, Long> {

    boolean existsByPhoneNo(String phoneNo);

    boolean existsByCode(String code);

    boolean existsByEmail(String email);

    boolean existsByRegistrationNo(String registrationNo);


    @Query(value = "select c.Id,c.name,c.code,c.registrationNo,c.address,count(cm.Id) as NoOfFarmers,c.phoneNo,c.email,c.dateCreated" +
            " from Cooperatives c left join CooperativeMemberShip cm on c.Id = cm.cooperative where c.code = :code group by c.Id ", nativeQuery = true)
    Cooperative getCooperative(@Param("code") String code);

    @Query(value = "select c.Id,c.name,c.code,c.registrationNo,c.address,count(cm.Id) as NoOfFarmers,c.phoneNo,c.email,c.dateCreated" +
            " from Cooperatives c left join CooperativeMemberShip cm on c.Id = cm.cooperative group by c.Id order by c.dateCreated desc", nativeQuery = true)
    List<Cooperative> getCooperatives();
}
