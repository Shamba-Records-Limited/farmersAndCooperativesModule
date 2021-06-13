package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.utils.enums.Gender;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface CoopFarmers {

    public Long getId();

    @JsonProperty("memberShipNo")
    public String getCoopMemberShipNo();

    @Value("#{target.farmer.firstName +' '+ target.farmer.lastName}")
    public String getFarmerName();

    @Value("#{target.farmer.primaryPhone}")
    public String getPhoneNo();

    @Value("#{target.farmer.nationalId}")
    public String getNationalId();

    @Value("#{target.farmer.code}")
    public String getGrowerCode();

    @JsonProperty("regionName")
    @Value("#{target.farmer.region.name}")
    public String getRegionName();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Value("#{target.farmer.dateOfBirth}")
    public LocalDate getDateOfBirth();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Value("#{target.farmer.dateOfJoining}")
    public LocalDate getDateOfJoining();

    @Value("#{target.farmer.gender}")
    public Gender getGender();


}
