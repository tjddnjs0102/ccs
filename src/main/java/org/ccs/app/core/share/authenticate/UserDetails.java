




package org.ccs.app.core.share.authenticate;

import lombok.Getter;
import lombok.ToString;
import org.ccs.app.core.user.domain.Role;

import java.util.List;

@Getter @ToString
public class UserDetails {
    private Long id;
    private String email;
    private List<Role> roles;
}
