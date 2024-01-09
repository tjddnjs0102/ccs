package org.ccs.app.entrypoints.login.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class LoginRequest { // 다국어 적용

    @NotBlank(message = "{email.required}")
    @Email(message = "{invalid.email}")
    private String email;

    @NotBlank(message = "{password.required}")
    private String password;
}
