package org.ccs.app.core.share.authenticate.token;

import lombok.Getter;
import lombok.Setter;

public enum JWTType {

    ACCESS
    ,
    REFRESH
    ,

    ;

    @Getter @Setter
    private long expirationMs;
}
