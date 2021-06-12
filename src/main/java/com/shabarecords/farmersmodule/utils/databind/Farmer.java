package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.models.Region;
import com.shabarecords.farmersmodule.utils.enums.Gender;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface Farmer {


    public Long getId();

    @JsonProperty("growerCode")
    public String getCode();

    public String getFirstName();

    public String getMiddleName();

    public String getLastName();

    public String getNationalId();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getDateOfBirth();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate getDateOfJoining();

    public Gender getGender();

    public String getEmail();

    public String getPrimaryPhone();

    public String getSecondaryPhone();

    @JsonProperty("regionName")
    @Value("#{target.region.name}")
    public String getName();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDateCreated();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getLastModified();
}
