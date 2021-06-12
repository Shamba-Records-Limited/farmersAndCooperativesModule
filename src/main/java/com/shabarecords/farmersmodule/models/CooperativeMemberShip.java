package com.shabarecords.farmersmodule.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "CooperatioveMemberShip")
@Data
@EqualsAndHashCode(exclude = {"cooperative", "farmer"})
public class CooperativeMemberShip implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;


    @Column(name = "coopMemberShipNo", length = 50, unique = true, nullable = false)
    private String coopMemberShipNo;

    @JoinColumn(name = "farmer", referencedColumnName = "Id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Farmers farmer;

    @JoinColumn(name = "cooperative", referencedColumnName = "Id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Cooperative cooperative;


    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;


    @Column(name = "lastModified", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE"
                    + " CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;


}
