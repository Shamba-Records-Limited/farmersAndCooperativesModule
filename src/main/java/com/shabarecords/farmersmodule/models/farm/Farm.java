package com.shabarecords.farmersmodule.models.farm;

import com.shabarecords.farmersmodule.models.farmer.Farmer;
import com.shabarecords.farmersmodule.models.regions.Village;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */
@Data
@Entity
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Farmer farmer;

    private String size;

    private String coordinates;

    @ManyToOne
    private Village village;

    private LocalDate dateAdded;
}
