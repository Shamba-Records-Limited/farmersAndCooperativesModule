package com.shabarecords.farmersmodule.models.cooperative;

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
    private Long id;

    @ManyToOne
    private Farmer farmer;

    @ManyToOne
    private Cooperative cooperative;

    private boolean active;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
}
