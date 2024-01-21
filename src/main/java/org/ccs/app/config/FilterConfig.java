package org.ccs.app.config;

import org.ccs.app.entrypoints.share.filter.ApiContextFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ApiContextFilter> apiContextFilterRegistration() {
        FilterRegistrationBean<ApiContextFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ApiContextFilter());
        registration.addUrlPatterns("/api/*");
        registration.setOrder(32);
        return registration;
    }
}
