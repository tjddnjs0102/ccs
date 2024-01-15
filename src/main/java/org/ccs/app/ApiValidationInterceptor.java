package org.ccs.app;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ApiValidationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // API 경로가 '/api/**' 일 때만 검증을 수행
        if (request.getRequestURI().equals("/api/**")) {
            // 여기에 API 검증 로직 추가
            boolean isValidRequest = performApiValidation(request);

            if (!isValidRequest) {
                // 유효하지 않은 요청인 경우
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid API Request");
                return false; // 요청 처리 중단
            }
        }

        return true; // 유효성 검증이 성공하면 요청 계속 진행
    }

    private boolean performApiValidation(HttpServletRequest request) {
        // 여기에 실제 API 검증 로직을 구현
        // 필요에 따라 특정 조건을 확인하고 유효성 검사 결과를 반환
        return true;
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
