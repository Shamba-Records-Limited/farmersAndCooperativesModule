package com.shabarecords.farmersmodule.models.farmer;

import com.shabarecords.farmersmodule.enums.farmer.ContactPriority;
import com.shabarecords.farmersmodule.enums.farmer.ContactType;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author : Odinga David
 * @since : 5/16/21, Sun
 */

@Data
@Entity
public class FarmerContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigDecimal code;

    @ManyToOne
    private Farmer farmer;

    private String value;

    @Enumerated(EnumType.STRING)
    private ContactType type;

    @Enumerated(EnumType.STRING)
    private ContactPriority  priority= ContactPriority.SECONDARY;

}
