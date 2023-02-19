package com.pmsoft.reseller.validator;

import com.pmsoft.reseller.model.dto.user.UserRegisterDto;
import com.pmsoft.reseller.validator.annotation.PasswordMatch;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRegisterDto> {

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRegisterDto userRegisterDto, ConstraintValidatorContext context) {

        if (userRegisterDto.getPassword() != null &&
            userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            return true;
        }

        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
            .addPropertyNode("confirmPassword")
            .addConstraintViolation()
            .disableDefaultConstraintViolation();

        return false;
    }
}
