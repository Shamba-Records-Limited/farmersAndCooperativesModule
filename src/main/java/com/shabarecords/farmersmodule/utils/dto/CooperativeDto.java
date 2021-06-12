package com.shabarecords.farmersmodule.utils.dto;

import com.shabarecords.farmersmodule.models.cooperative.Cooperative;
import lombok.Data;

/**
 * @author : Odinga David
 * @since : 6/11/21, Fri
 */

@Data
public class CooperativeDto {

    private String name;
    private String code;
    private String subCountyCode;
    private String address;
    private String primaryPhone;
    private String secondaryPhone;
    private String primaryEmail;
    private String secondaryEmail;


    public Cooperative toCooperative(){
        return Cooperative.builder()
                .name(name)
                .code(code)
                .address(address)
                .primaryPhone(primaryPhone)
                .secondaryPhone(secondaryPhone)
                .primaryEmail(primaryEmail)
                .secondaryEmail(secondaryEmail)
                .build();
    }
}
