package com.shabarecords.farmersmodule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shabarecords.farmersmodule.utils.databind.AddCooperativeRequest;
import com.shabarecords.farmersmodule.utils.databind.UpdateCooperativeRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "Cooperative")
@Data
@EqualsAndHashCode(exclude = {"memberShip", "farmSet"})
@NoArgsConstructor
public class Cooperatives implements Serializable {

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

    @OneToMany(mappedBy = "cooperative", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private Set<CooperativeMemberShip> memberShip;

    @Column(name = "phoneNo", length = 20, nullable = false, unique = true)
    private String phoneNo;

    @Column(name = "email", length = 100, unique = true)
    private String email;

    @OneToMany(mappedBy = "cooperatives", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
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


    public Cooperatives(String name, String code, String registrationNo, String address, String phoneNo, String email) {

        this.name = name;
        this.code = code;
        this.registrationNo = registrationNo;
        this.address = address;
        this.phoneNo = phoneNo;
        this.email = email;
    }

    public static Cooperatives of(AddCooperativeRequest request) {

        return new Cooperatives(request.getName(),
                request.getCode(),
                request.getRegistrationNo(),
                request.getAddress(),
                request.getPhoneNo(),
                request.getEmail());
    }

    public static Cooperatives ofUpdate(Cooperatives cooperative, UpdateCooperativeRequest updateRequest) {
        if (updateRequest.getAddress() != null)
            cooperative.setAddress(updateRequest.getAddress());

        if (updateRequest.getEmail() != null)
            cooperative.setEmail(updateRequest.getEmail());

        if (updateRequest.getName() != null)
            cooperative.setName(updateRequest.getName());


        if (updateRequest.getPhoneNo() != null)
            cooperative.setPhoneNo(updateRequest.getPhoneNo());

        if (updateRequest.getRegistrationNo() != null)
            cooperative.setRegistrationNo(updateRequest.getRegistrationNo());


        return cooperative;
    }
}
