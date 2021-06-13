package com.shabarecords.farmersmodule.utils.databind;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.utils.validation.PhoneNumberConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class UpdateCooperativeRequest {

    private String name;



    private String registrationNo;


    private String address;


    private String phoneNo;


    private String email;

}
