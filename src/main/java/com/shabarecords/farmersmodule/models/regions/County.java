package com.shabarecords.farmersmodule.models.regions;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Data
@Entity
public class County {
    @Id
    @Column(name = "countyCode",length = 10,unique = true,nullable = false)
    private String countyCode;

    @Column(name = "name",length = 50,nullable = false)
    private String name;
}
