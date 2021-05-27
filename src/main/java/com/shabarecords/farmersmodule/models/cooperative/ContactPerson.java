package com.shabarecords.farmersmodule.models.cooperative;

import com.shabarecords.farmersmodule.models.title.Title;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Entity
@Data
public class ContactPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String middleName;

    private String lastName;

    @ManyToOne
    private Title title;

    @ManyToOne
    private Cooperative cooperative;

    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;


}
