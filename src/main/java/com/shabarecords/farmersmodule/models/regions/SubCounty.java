package com.shabarecords.farmersmodule.models.regions;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Entity
@Data
public class SubCounty {
    @Id
    private String subCountyCode;

    @ManyToOne
    private County county;

    private String name;
}
