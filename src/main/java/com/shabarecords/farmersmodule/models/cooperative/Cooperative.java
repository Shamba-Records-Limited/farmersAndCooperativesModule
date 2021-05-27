package com.shabarecords.farmersmodule.models.cooperative;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.models.regions.SubCounty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Data
@Entity
public class Cooperative {

    private String name;

    @Id
    private String code;

    @ManyToOne
    private SubCounty subCounty;

    private String address;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private Integer numberOfTrees;
}
