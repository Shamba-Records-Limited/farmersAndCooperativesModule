package com.shabarecords.farmersmodule.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shabarecords.farmersmodule.utils.databind.RegionRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author : Odinga David
 * @since : 5/23/21, Sun
 */

@Entity
@Table(name = "Region")
@Data
@ApiModel
public class Region implements Serializable {
    @Id
    @Column(name = "code", length = 10, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @OneToMany(mappedBy = "farmer", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
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

    public static Region of(RegionRequest regionRequest) {
        Region region = new Region();
            region.setCode(regionRequest.getCode());
            region.setName(regionRequest.getName());
            return region;
    }
}
