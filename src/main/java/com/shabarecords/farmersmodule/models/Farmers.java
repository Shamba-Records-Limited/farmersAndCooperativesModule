package com.shabarecords.farmersmodule.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.shabarecords.farmersmodule.utils.PhoneNumberUtil;
import com.shabarecords.farmersmodule.utils.databind.AddFarmerRequest;
import com.shabarecords.farmersmodule.utils.enums.Gender;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "Farmer")
@Data
@EqualsAndHashCode(exclude = {"farmer", "region"})

public class Farmers implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;


    @Column(name = "code", length = 20, unique = true)
    private String code;


    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;


    @Column(name = "middleName", length = 50)
    private String middleName;

    @Column(name = "lastName", length = 50)
    private String lastName;

    @Column(name = "nationalId", nullable = false, length = 15, unique = true)
    private String nationalId;

    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    @Column(name = "dateOfJoining")
    private LocalDate dateOfJoining;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", columnDefinition = "enum('MALE','UNKNOWN','FEMALE') NOT NULL DEFAULT 'UNKNOWN'")
    private Gender gender;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @Column(name = "primaryPhoneNo", length = 20, nullable = false, unique = true)
    private String primaryPhone;

    @Column(name = "secondaryPhoneNo", length = 20)
    private String secondaryPhone;

    @JoinColumn(name = "region", referencedColumnName = "code")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Region region;

    @OneToMany(mappedBy = "farmer", cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private Set<Farm> farmSet;

    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;


    @Column(name = "lastModified", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE"
                    + " CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;


    public static Farmers of(AddFarmerRequest request, Region region) {
        Farmers farmers = new Farmers();
        farmers.setCode(request.getCode());
        farmers.setFirstName(request.getFirstName());
        farmers.setMiddleName(request.getMiddleName());
        farmers.setLastName(request.getLastName());
        farmers.setNationalId(request.getNationalId());
        farmers.setDateOfBirth(request.getDateOfBirth());
        farmers.setDateOfJoining(request.getDateOfJoining());
        farmers.setGender(request.getGender() == null ? Gender.UNKNOWN : Gender.valueOf(request.getGender()));
        farmers.setEmail(request.getEmail());
        farmers.setPrimaryPhone(request.getPrimaryPhone());
        farmers.setSecondaryPhone(PhoneNumberUtil.getFormattedPhoneNumber(request.getSecondaryPhone()));
        farmers.setRegion(region);

        return farmers;


    }
}
