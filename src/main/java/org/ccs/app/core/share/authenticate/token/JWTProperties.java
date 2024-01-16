package org.ccs.app.core.share.authenticate.token;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.expiration-ms")
public class JWTProperties {

    private long access;
    private long refresh;

    @PostConstruct
    public void setEnumValue() {
        JWTType.ACCESS.setExpirationMs(access);
        JWTType.REFRESH.setExpirationMs(refresh);
    }
}
