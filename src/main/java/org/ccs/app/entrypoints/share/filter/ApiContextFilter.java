package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ccs.app.core.share.authenticate.Authenticate;
import org.ccs.app.core.share.authenticate.AuthenticateHolder;
import org.ccs.app.entrypoints.share.model.ContentBody;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@WebFilter("/api/*")
@Order(32)
public class ApiContextFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authenticate authenticate = AuthenticateHolder.get();

        if (authenticate == null) {
            sendUnauthenticatedResponse(response);
            return;
        }

        chain.doFilter(request, response);
    }

    private void sendUnauthenticatedResponse(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ContentBody<String> unauthenticatedBody = new ContentBody<>(
                HttpServletResponse.SC_UNAUTHORIZED, // 401
                "Unauthorized: 로그인이 필요합니다.", // authenticate 객체가 없다는 것은 로그인을 하지 않은 상태라고 생각했습니다.
                "",
                null);

        String serializedBody = ContentBody.serialize(unauthenticatedBody);
        httpResponse.setContentType("application/json");
        httpResponse.getWriter().write(serializedBody);
    }
}
