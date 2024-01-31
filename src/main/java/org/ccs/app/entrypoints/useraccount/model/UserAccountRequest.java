package org.ccs.app.entrypoints.useraccount.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserAccountRequest {

    @NotBlank(message = "request.valid.username.required")
    private String username;

    @NotBlank(message = "request.valid.email.required")
    @Email(message = "request.valid.invalid.email")
    private String email;

    @NotBlank(message = "request.valid.password.required")
    private String password;
}
