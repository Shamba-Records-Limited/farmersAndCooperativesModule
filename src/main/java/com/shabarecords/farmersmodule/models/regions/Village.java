package com.shabarecords.farmersmodule.models.regions;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Data
@Entity
public class Village {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id", nullable=false, unique=true)
    private Integer id;

    @Column(name="name", nullable=false, length=50)
    private String name;

    @ManyToOne
    private SubCounty  subCounty;
}
