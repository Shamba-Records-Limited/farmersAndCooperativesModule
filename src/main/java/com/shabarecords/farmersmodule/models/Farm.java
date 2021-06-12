package com.shabarecords.farmersmodule.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.shabarecords.farmersmodule.utils.databind.FarmRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "Farm")
@Data
@EqualsAndHashCode(exclude = {"farmer", "region"})
@NoArgsConstructor
public class Farm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "location", length = 50, nullable = false)
    private String location;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "noOfAcres", columnDefinition = "INT NOT NULL DEFAULT 0")
    private Double noOfAcres;

    @Column(name = "noOfTrees", columnDefinition = "INT DEFAULT 0")
    private Integer noOfTrees;

    @JoinColumn(name = "region", referencedColumnName = "code")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Region region;

    @JoinColumn(name = "farmerId", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Farmers farmer;

    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;


    @Column(name = "lastModified", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE"
                    + " CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;


    public Farm(String location, String description, Double noOfAcres, Integer noOfTrees, Region region, Farmers farmer) {
        this.location = location;
        this.description = description;
        this.noOfAcres = noOfAcres;
        this.noOfTrees = noOfTrees;
        this.region = region;
        this.farmer = farmer;
    }

    public static Farm of(FarmRequest farmRequest, Region region, Farmers farmers) {

        return new Farm(farmRequest.getLocation(), farmRequest.getDescription(), farmRequest.getNoOfAcres(), farmRequest.getNoOfTrees(),
                region, farmers);
    }
}
