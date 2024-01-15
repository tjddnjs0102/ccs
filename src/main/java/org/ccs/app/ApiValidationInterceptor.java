package org.ccs.app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

public class ApiValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();

        // API 경로가 '/public/'로 시작하면 누구나 접근 가능
        if (path.startsWith("/public/")) {
            return true;
        }

        // API 경로가 '/api/'로 시작하면 토큰 필요
        if (path.startsWith("/api/")) {
            String token = extractTokenFromRequest(request);
            if (token == null) {
                sendErrorResponse(response, "Token is required for /api");
                return false;
            }
            // TODO:토큰이 유효한지 확인하는 로직 추가
            // ...

            return true;
        }

        // API 경로가 '/admin/'로 시작하면 토큰 및 권한 필요
        if (path.startsWith("/admin/")) {
            String token = extractTokenFromRequest(request);
            if (token == null) {
                sendErrorResponse(response, "Token is required for /admin");
                return false;
            }
            // TODO:토큰이 유효하고, 관리자 권한을 가지고 있는지 확인하는 로직 추가
            // ...

            return true;
        }

        // 다른 경우는 허용하지 않음
        sendErrorResponse(response, "Invalid API path");
        return false;
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        // TODO:토큰 추출 로직
        // ...
        return null; // 토큰이 없는 경우
    }

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, errorMessage);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 요청 처리 후, 뷰가 렌더링되기 전에 실행되는 메소드
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 뷰가 렌더링된 후 실행되는 메소드
    }
}
