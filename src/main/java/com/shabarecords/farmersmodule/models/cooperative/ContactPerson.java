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
    @Column(name = "id", unique = true,nullable = false)
    private Integer id;

    @Column(name = "firstName",length = 50,nullable = false)
    private String firstName;

    @Column(name = "middleName",length = 50)
    private String middleName;

    @Column(name = "lastName",length = 50,nullable = false)
    private String lastName;

    @ManyToOne
    private Title title;

    @ManyToOne
    private Cooperative cooperative;

    private LocalDate effectiveFrom = LocalDate.now();
    private LocalDate effectiveTo;


}
