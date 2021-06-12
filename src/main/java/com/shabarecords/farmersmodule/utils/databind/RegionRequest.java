package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel
@Getter
@Setter
public class RegionRequest implements Serializable {

    @NotBlank(message = "code is required")
    @ApiModelProperty(required = true)
    private String code;

    @NotBlank(message = "name is required")
    @ApiModelProperty(required = true)
    private String name;
}
