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
    @Column(name = "code",nullable = false)
    private int code;

    @ManyToOne
    private Farmer farmer;

    @Column(name = "subCountyCode",length = 50,nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",length = 10,nullable = false)
    private ContactType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority",length = 10,nullable = false)
    private ContactPriority  priority= ContactPriority.SECONDARY;

}
