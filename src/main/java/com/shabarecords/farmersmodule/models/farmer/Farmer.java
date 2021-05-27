package com.shabarecords.farmersmodule.models.farmer;

import com.shabarecords.farmersmodule.enums.farmer.Gender;
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
    private String growerId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String nationalId;

    private LocalDate dateOfBirth;

    private LocalDate dateOfJoining;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String coopMemberShipNo;

    @ManyToOne
    private Village village;


}
