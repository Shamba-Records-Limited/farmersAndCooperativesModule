package com.shabarecords.farmersmodule.models.farm;

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
    private Integer id;

    @ManyToOne
    private Farm farm;

    @NotNull
    private int numberOfTrees;

    private LocalDate dateAdded;

    private boolean currentRecord;


}
