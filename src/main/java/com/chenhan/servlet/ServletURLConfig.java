package com.chenhan.servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletURLConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean(){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new MyServlet());//配置自己的servlet
        servletRegistrationBean.addUrlMappings("/myServlet");
        servletRegistrationBean.addUrlMappings("/myServlet1");

        return servletRegistrationBean;
    }

}
