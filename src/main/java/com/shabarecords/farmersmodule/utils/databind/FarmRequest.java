package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class FarmRequest implements Serializable {

    @NotBlank(message = "farm location is required")
    @ApiModelProperty(required = true)
    private String location;

    @NotBlank(message = "farm description is required")
    @ApiModelProperty(required = true)
    private String description;

    @NotNull(message = "noOfAcres of acres is required")
    @ApiModelProperty(required = true)
    private Double noOfAcres;


    private Integer noOfTrees;

    private String regionCode;
}
