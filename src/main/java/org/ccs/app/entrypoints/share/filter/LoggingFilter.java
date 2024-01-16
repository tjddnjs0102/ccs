package org.ccs.app.entrypoints.share.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.StreamUtils;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.Enumeration;

@WebFilter("/*")
@Order(10)
public class LoggingFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        log.info("Request method: {}, URL: {}, headers: [{}], params: [{}], Body: {}",
                httpRequest.getMethod(), httpRequest.getMethod(), this.getHeaderAsString(httpRequest), this.getRequestBody(httpRequest));

        CharResponseWrapper responseWrapper = new CharResponseWrapper(httpResponse);

        chain.doFilter(request, response);

        log.info("Response headers: [{}], Body: {}", this.getHeaderAsString(httpResponse), responseWrapper.toString());
    }

    private String getHeaderAsString(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder headers = new StringBuilder();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            headers.append(headerName).append(": ").append(headerValue).append("\n");
        }

        return headers.toString();
    }

    private String getHeaderAsString(HttpServletResponse response) {
        StringBuilder headers = new StringBuilder();
        response.getHeaderNames().stream().forEach(it -> headers.append(it).append(":").append(response.getHeaders(it)).append("\n"));
        return headers.toString();
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        ServletRequestWrapper requestWrapper = new ServletRequestWrapper(request);
        return StreamUtils.copyToString(requestWrapper.getInputStream(), Charset.defaultCharset());
    }

    class CharResponseWrapper extends HttpServletResponseWrapper {
        private final CharArrayWriter output;

        public CharResponseWrapper(HttpServletResponse response) {
            super(response);
            output = new CharArrayWriter();
        }

        @Override
        public PrintWriter getWriter() {
            return new PrintWriter(output);
        }

        @Override
        public String toString() {
            return output.toString();
        }
    }
}
