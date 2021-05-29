package com.shabarecords.farmersmodule.models.cooperative;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabarecords.farmersmodule.utils.enums.ContactPriority;
import com.shabarecords.farmersmodule.utils.enums.ContactType;
import lombok.Data;

import javax.persistence.*;

/**
 * @author : Odinga David
 * @since : 5/22/21, Sat
 */

@Data
@Entity
@Table(name="cop_contact")
public class CoopContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code",unique=true, nullable = false)
    private Integer code;

    @ManyToOne
    @JsonIgnore
    private Cooperative cooperative;

    @Column(name = "value",length = 30,nullable = false)
    private String value;

    @Enumerated(EnumType.STRING)
    @Column(name = "type",length = 10,nullable = false)
    private ContactType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority",length = 10,nullable = false)
    @JsonIgnore
    private ContactPriority priority= ContactPriority.SECONDARY;
}
