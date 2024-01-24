package org.ccs.app.config;

import org.ccs.app.entrypoints.share.filter.AdminContextFilter;
import org.ccs.app.entrypoints.share.filter.ApiContextFilter;
import org.ccs.app.entrypoints.share.filter.JWTFilter;
import org.ccs.app.entrypoints.share.filter.MDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<MDCFilter> mdcFilter() {
        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MDCFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Integer.MIN_VALUE);
        return registrationBean;
    }

    // TODO: logging을 위해 request의 스트림을 소진해서 실제 requestbody가 null이 되는 현상 수정
//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(10);
//        return registrationBean;
//    }

    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilter() {
        FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JWTFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(20);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<ApiContextFilter> apiContextFilter() {
        FilterRegistrationBean<ApiContextFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ApiContextFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(21);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminContextFilter> adminContextFilter() {
        FilterRegistrationBean<AdminContextFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminContextFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(22);
        return registrationBean;
    }
}
