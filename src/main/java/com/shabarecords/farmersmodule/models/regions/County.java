package com.shabarecords.farmersmodule.models.regions;

import lombok.Data;

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
    private String countyCode;
    private String name;
}
