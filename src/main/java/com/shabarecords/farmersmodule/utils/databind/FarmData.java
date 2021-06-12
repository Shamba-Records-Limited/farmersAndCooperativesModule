package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface FarmData {
    public Long getId();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDateCreated();

    public String getLocation();

    public String getDescription();

    public Double getNoOfAcres();

    public String getNoOfTrees();

    @JsonProperty("regionName")
    @Value("#{target.region.name}")
    public String getName();
}
