package org.ccs.app.entrypoints.share.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.ccs.app.core.share.authenticate.Authenticate;
import org.ccs.app.core.share.authenticate.AuthenticateHolder;
import org.ccs.app.entrypoints.share.model.ContentBody;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApiContextFilter implements Filter {

    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authenticate authenticate = AuthenticateHolder.get();

        if (!authenticate.isAuthenticated()) { // authenticated 필드가 false인 경우
            sendUnauthenticatedResponse(response);
            return;
        }

        chain.doFilter(request, response);
    }

    private void sendUnauthenticatedResponse(ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        ContentBody<String> unauthenticatedBody = new ContentBody<>(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized: 로그인이 필요합니다.",
                "",
                null);


        httpResponse.setContentType("application/json");
        httpResponse.getWriter().write(mapper.writeValueAsString(unauthenticatedBody));
    }
}
