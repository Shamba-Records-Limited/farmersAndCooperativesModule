package com.shabarecords.farmersmodule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "Cooperative")
@Data
@EqualsAndHashCode(exclude = {"memberShip"})
public class Cooperative implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "code", length = 20, unique = true)
    private String code;

    @Column(name = "registrationNo", nullable = false, length = 30, unique = true)
    private String registrationNo;


    @Column(name = "address", length = 200)
    private String address;

    @OneToMany(mappedBy = "cooperative", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<CooperativeMemberShip> memberShip;

    @Column(name = "phoneNo", length = 20, nullable = false, unique = true)
    private String phoneNo;

    @Column(name = "email", length = 100, unique = true)
    private String email;


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
