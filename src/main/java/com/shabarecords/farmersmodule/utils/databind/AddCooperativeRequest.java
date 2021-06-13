package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.utils.validation.PhoneNumberConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class AddCooperativeRequest {

    @NotBlank(message = "name is required")
    @ApiModelProperty(required = true)
    private String name;


    @JsonProperty("coopCode")
    private String code;

    @NotBlank(message = "registrationNo is required")
    @ApiModelProperty(required = true)
    private String registrationNo;


    private String address;

    @PhoneNumberConstraint
    @ApiModelProperty(required = true)
    private String phoneNo;

    @NotBlank(message = "email is required")
    @ApiModelProperty(required = true)
    private String email;

    @Valid
    private List<FarmRequest> farms = new ArrayList<>();
}
