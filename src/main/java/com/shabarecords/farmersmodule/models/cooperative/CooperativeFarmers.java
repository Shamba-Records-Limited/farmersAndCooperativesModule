package com.shabarecords.farmersmodule.models.cooperative;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.models.farmer.Farmer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Odinga David
 * @since : 5/24/21, Mon
 */

@Data
@Entity
public class CooperativeFarmers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name = "id",unique = true,nullable = false)
    private Long id;

    @ManyToOne
    private Farmer farmer;

    @ManyToOne
    private Cooperative cooperative;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private boolean active = true;


    private LocalDate effectiveFrom = LocalDate.now();

    private LocalDate effectiveTo;
}
