package com.shabarecords.farmersmodule.utils.validation;


import com.shabarecords.farmersmodule.utils.PhoneNumberUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class PhoneNumberValidator implements
        ConstraintValidator<PhoneNumberConstraint, String> {

    @Override
    public void initialize(PhoneNumberConstraint phone) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {


        boolean isValidPhone = false;
        if (!Objects.isNull(contactField)) {
            try {
                isValidPhone = PhoneNumberUtil.isValidPhoneNumber(contactField);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isValidPhone;
    }

}
