package com.shabarecords.farmersmodule.models.farmer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.utils.enums.Gender;
import com.shabarecords.farmersmodule.models.regions.Village;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Odinga David
 * @since : 5/16/21, Sun
 */

@Data
@Entity
public class Farmer {
    @Id
    @Column(name = "growerId",length = 10,unique = true,nullable = false)
    private String growerId;

    @Column(name = "firstName",length = 30,nullable = false)
    private String firstName;

    @Column(name = "middleName",length = 30,nullable = false)
    private String middleName;

    @Column(name = "lastName",length = 30,nullable = false)
    private String lastName;

    @Column(name = "nationalId",length = 10,nullable = false)
    private String nationalId;

    private LocalDate dateOfBirth;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate dateOfJoining=LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "gender",length = 7,nullable = false)
    private Gender gender;

    @Column(name = "membershipNo",length = 10,nullable = false)
    private String coopMemberShipNo;

    @ManyToOne
    private Village village;


}
