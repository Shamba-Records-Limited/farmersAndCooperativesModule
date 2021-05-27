package com.shabarecords.farmersmodule.models.regions;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Data
@Entity
public class Village {
    @Id
    private Integer id;

    private String name;

    @ManyToOne
    private SubCounty  subCounty;
}
