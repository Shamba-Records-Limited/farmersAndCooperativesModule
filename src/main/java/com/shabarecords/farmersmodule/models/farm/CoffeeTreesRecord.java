package com.shabarecords.farmersmodule.models.farm;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class CoffeeTreesRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique=true,nullable = false)
    private Integer id;

    @ManyToOne
    private Farm farm;

    @NotNull
    @Column(name = "numberOfTrees",nullable = false)
    private int numberOfTrees;

    @Column(name = "dateAdded",nullable = false)
    private LocalDate dateAdded;

    @JsonIgnore
    private boolean currentRecord=true;

}
