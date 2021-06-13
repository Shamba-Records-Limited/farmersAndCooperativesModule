package com.shabarecords.farmersmodule.utils.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.models.CooperativeMemberShip;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

public interface Cooperative {


    public Long getId();

    public String getName();

    @JsonProperty("coopCode")
    public String getCode();

    public String getRegistrationNo();

    public String getAddress();

    public  Integer getNoOfFarmers();

    public String getPhoneNo();

    public String getEmail();

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getDateCreated();


}
