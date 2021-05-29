package com.shabarecords.farmersmodule.models.regions;

import lombok.Data;

import javax.persistence.Column;
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
    @Column(name = "subCountyCode",length = 10,unique = true,nullable = false)
    private String subCountyCode;

    @ManyToOne
    private County county;

    @Column(name = "name",length = 50,nullable = false)
    private String name;
}
