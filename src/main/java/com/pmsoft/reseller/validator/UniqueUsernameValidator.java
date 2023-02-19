package com.pmsoft.reseller.validator;

import com.pmsoft.reseller.model.dto.user.UserDto;
import com.pmsoft.reseller.service.UserService;
import com.pmsoft.reseller.validator.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        UserDto user = this.userService.getByUsername(username);

        return user == null;
    }
}
