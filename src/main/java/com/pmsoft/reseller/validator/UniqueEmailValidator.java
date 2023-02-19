package com.pmsoft.reseller.validator;

import com.pmsoft.reseller.model.dto.user.UserDto;
import com.pmsoft.reseller.service.UserService;
import com.pmsoft.reseller.validator.annotation.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    private final UserService userService;

    public UniqueEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        UserDto user = this.userService.getByEmail(email);

        return user == null;
    }
}
