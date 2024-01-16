package org.ccs.app.core.share.authenticate;

public class AuthenticateHolder {
    private static final ThreadLocal<Authenticate> authenticateHolder = new ThreadLocal<>();

    public static void setAuthenticate(Authenticate authenticate) {
        authenticateHolder.set(authenticate);
    }

    public static Authenticate getLoginUser() {
        return authenticateHolder.get();
    }

    public static void clear() {
        authenticateHolder.remove();
    }
}
