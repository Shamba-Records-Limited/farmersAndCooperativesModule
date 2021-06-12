package com.shabarecords.farmersmodule.models.cooperative;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.models.regions.SubCounty;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Data
@Entity
@Builder
public class Cooperative {

    @Column(name = "name",length = 150,nullable = false)
    private String name;

    @Id
    @Column(name = "code",unique=true, length = 10,nullable = false)
    private String code;

    @ManyToOne
    private SubCounty subCounty;

    @Column(name = "address",length = 150,nullable = false)
    private String address;

    @Column(name = "primaryPhone",unique=true, length = 13,nullable = false)
    private String primaryPhone;

    @Column(name = "secondaryPhone",unique=true, length = 13,nullable = false)
    private String secondaryPhone;

    @Column(name = "primaryEmail",unique=true, length = 100,nullable = false)
    private String primaryEmail;

    @Column(name = "secondaryEmail",unique=true, length = 100,nullable = false)
    private String secondaryEmail;
}
