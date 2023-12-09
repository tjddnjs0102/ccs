package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.UUID;

@WebFilter("/*")
@Order(Integer.MIN_VALUE)
public class MDCFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final UUID uuid = UUID.randomUUID();
        MDC.put("traceId", uuid.toString());
        chain.doFilter(request, response);
        MDC.clear();
    }
}
