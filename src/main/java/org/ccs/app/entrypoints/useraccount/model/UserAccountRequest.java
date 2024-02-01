package org.ccs.app.entrypoints.useraccount.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.ccs.app.core.user.domain.Gender;

@Getter @Setter
public class UserAccountRequest {

    @NotBlank(message = "request.valid.username.required")
    private String username;

    @NotBlank(message = "request.valid.email.required")
    @Email(message = "request.valid.invalid.email")
    private String email;

    @NotBlank(message = "request.valid.password.required")
    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String phoneNumber;

    private Integer parish;

    private Integer cellGroup;


    private String zipcode;

    private String address;
}
