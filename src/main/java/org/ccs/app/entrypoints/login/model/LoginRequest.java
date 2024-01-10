package org.ccs.app.entrypoints.login.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class LoginRequest {

    @NotBlank(message = "request.valid.email.required")
    @Email(message = "request.valid.invalid.email")
    private String email;

    @NotBlank(message = "request.valid.password.required")
    private String password;
}
