package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shabarecords.farmersmodule.utils.enums.Gender;
import com.shabarecords.farmersmodule.utils.validation.ValueOfEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class UpdateFarmerRequest implements Serializable {


    private String firstName;

    private String middleName;


    private String lastName;


    private String nationalId;


    private LocalDate dateOfBirth;

    @ValueOfEnum(enumClass = Gender.class)
    @ApiModelProperty(allowableValues = "MALE,FEMALE,UNKNOWN")
    private Gender gender;

    private String email;


    private String primaryPhone;


    private String secondaryPhone;

    private String regionCode;
}
