package com.shabarecords.farmersmodule.utils.databind;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shabarecords.farmersmodule.utils.enums.Gender;
import com.shabarecords.farmersmodule.utils.validation.PhoneNumberConstraint;
import com.shabarecords.farmersmodule.utils.validation.ValueOfEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class AddFarmerRequest implements Serializable {

    @NotBlank(message = "firstName is required")
    @ApiModelProperty(required = true)
    private String firstName;

    private String middleName;

    private String code;

    @NotBlank(message = "firstName is required")
    @ApiModelProperty(required = true)
    private String lastName;

    @NotBlank(message = "nationalId is required")
    @ApiModelProperty(required = true)
    private String nationalId;

    @NotNull(message = "dateOfBirth is required")
    @ApiModelProperty(required = true)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;

    @NotNull(message = "dateOfBirth is required")
    @ApiModelProperty(required = true)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfJoining;


    @ValueOfEnum(enumClass = Gender.class)
    @ApiModelProperty(allowableValues = "MALE,FEMALE,UNKNOWN")
    private String gender;

    @Email(message = "A valid email is required")
    private String email;

    @PhoneNumberConstraint
    @ApiModelProperty(required = true)
    private String primaryPhone;


    private String secondaryPhone;

    private String regionCode;


    @Valid
    private List<FarmRequest> farms = new ArrayList<>();

}
