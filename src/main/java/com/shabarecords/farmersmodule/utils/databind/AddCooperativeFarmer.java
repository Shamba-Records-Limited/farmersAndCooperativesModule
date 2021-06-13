package com.shabarecords.farmersmodule.utils.databind;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class AddCooperativeFarmer {

    @NotBlank(message = "memberShipNo is required")
    @ApiModelProperty(required = true)
    private String memberShipNo;

    @NotNull(message = "farmerId is required")
    @ApiModelProperty(required = true)
    private Long farmerId;

}
