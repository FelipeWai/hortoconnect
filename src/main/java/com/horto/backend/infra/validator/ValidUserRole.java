package com.horto.backend.infra.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserEnumValidator.class)
public @interface ValidUserRole {
    String message() default "Role inválido. Deve ser um dos valores: ADMIN, CLIENT";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}