package com.shabarecords.farmersmodule.models;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@Table(name = "CooperatioveMemberShip")
@Data
@EqualsAndHashCode(exclude = {"cooperative", "farmer"})
@NoArgsConstructor
public class CooperativeMemberShip implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "coopMemberShipNo", length = 50, nullable = false)
    private String coopMemberShipNo;

    @JoinColumn(name = "farmer", referencedColumnName = "Id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Farmers farmer;

    @JoinColumn(name = "cooperative", referencedColumnName = "Id")
    @ManyToOne(optional = true, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private Cooperatives cooperative;


    @Column(name = "dateCreated", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateCreated;


    @Column(name = "lastModified", updatable = false, insertable = false,
            columnDefinition = "TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE"
                    + " CURRENT_TIMESTAMP")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModified;

    public CooperativeMemberShip(String coopMemberShipNo, Farmers farmer, Cooperatives cooperative) {
        this.coopMemberShipNo = coopMemberShipNo;
        this.farmer = farmer;
        this.cooperative = cooperative;
    }

    public static CooperativeMemberShip of(String memberShipNo, Farmers farmers, Cooperatives cooperative) {
        return new CooperativeMemberShip(memberShipNo, farmers, cooperative);
    }
}
