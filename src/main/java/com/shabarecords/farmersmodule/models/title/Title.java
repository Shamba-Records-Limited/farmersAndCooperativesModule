package com.shabarecords.farmersmodule.models.title;

import lombok.Data;

import javax.persistence.*;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Data
@Entity
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",unique = true,nullable = false)
    private Integer id;

    @Column(name = "name",length = 100,nullable = false)
    private String name;
}
