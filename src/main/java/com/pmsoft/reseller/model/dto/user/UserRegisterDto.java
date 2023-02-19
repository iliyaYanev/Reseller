package com.pmsoft.reseller.model.dto.user;

import com.pmsoft.reseller.validator.annotation.PasswordMatch;
import com.pmsoft.reseller.validator.annotation.UniqueEmail;
import com.pmsoft.reseller.validator.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
@PasswordMatch
public class UserRegisterDto {

    @NotNull
    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters.")
    @UniqueUsername
    private String username;

    @Email(message = "Enter valid email address.")
    @NotNull
    @NotBlank(message = "Email cannot be empty.")
    @UniqueEmail
    private String email;

    @NotNull
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters.")
    private String password;

    @NotNull
    private String confirmPassword;
}
