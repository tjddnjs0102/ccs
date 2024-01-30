package org.ccs.app.core.share.authenticate.token;

import lombok.Getter;
import lombok.Setter;

public enum JWTType {

    ACCESS(3600*1000l)
    ,
    REFRESH(604800*1000l)
    ,

    ;

    JWTType(long expirationMs) {
        this.expirationMs = expirationMs;
    }

    @Getter @Setter
    private long expirationMs;
}
