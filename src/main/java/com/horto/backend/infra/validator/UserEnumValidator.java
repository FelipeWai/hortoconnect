package com.horto.backend.infra.validator;

import com.horto.backend.core.enums.UserRoleEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserEnumValidator implements ConstraintValidator<ValidUserRole, UserRoleEnum> {

    @Override
    public void initialize(ValidUserRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRoleEnum value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        for (UserRoleEnum role : UserRoleEnum.values()) {
            if (role == value) {
                return true;
            }
        }
        return false;
    }
}