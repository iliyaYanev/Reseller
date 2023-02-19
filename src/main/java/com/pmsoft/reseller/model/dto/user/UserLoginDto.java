package com.pmsoft.reseller.model.dto.user;

import com.pmsoft.reseller.validator.annotation.ValidateLoginForm;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
@ValidateLoginForm
public class UserLoginDto {

    @NotNull
    @Length(min = 3, max = 20, message = "Username length must be between 3 and 20 characters!")
    private String username;

    @NotNull
    @Length(min = 3, max = 20, message = "Password length must be between 3 and 20 characters!")
    private String password;
}
