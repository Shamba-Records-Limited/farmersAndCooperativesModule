package com.shabarecords.farmersmodule.models.cooperative;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shabarecords.farmersmodule.models.farmer.Farmer;
import com.sun.istack.NotNull;
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
    @NotNull
    private Farmer farmer;

    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @JsonIgnore
    private Cooperative cooperative;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    @JsonIgnore
    private boolean active = true;

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private LocalDate effectiveFrom = LocalDate.now();

    @JsonProperty(access= JsonProperty.Access.READ_ONLY)
    private LocalDate effectiveTo;
}
