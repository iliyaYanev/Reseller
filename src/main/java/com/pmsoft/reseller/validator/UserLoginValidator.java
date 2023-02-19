package com.pmsoft.reseller.validator;

import com.pmsoft.reseller.model.dto.user.UserDto;
import com.pmsoft.reseller.model.dto.user.UserLoginDto;
import com.pmsoft.reseller.service.UserService;
import com.pmsoft.reseller.validator.annotation.ValidateLoginForm;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLoginValidator implements ConstraintValidator<ValidateLoginForm, UserLoginDto> {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public UserLoginValidator(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initialize(ValidateLoginForm constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserLoginDto userLoginDto, ConstraintValidatorContext context) {
        UserDto user = this.userService.getByUsername(userLoginDto.getUsername());

        return user != null && passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword());
    }
}
