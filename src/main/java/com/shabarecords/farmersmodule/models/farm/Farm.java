package com.shabarecords.farmersmodule.models.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @Column(name = "id",unique = true,nullable = false)
    private Integer id;

    @ManyToOne
    @JsonIgnore
    private Farmer farmer;

    @Column(name = "size",length = 150,nullable = false)
    private String size;

    @Column(name = "coodinates",length = 150,nullable = false)
    private String coordinates;

    @ManyToOne
    private Village village;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private LocalDate dateAdded=LocalDate.now();
}
