package com.tls.xblog.configuration;


import com.tls.xblog.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration/*(proxyBeanMethods = false)*/
public class MyMvcConfigurer implements WebMvcConfigurer{
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
       //config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        interceptorRegistry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/","/login","/notlogin","/css/**","/fonts/**","/images/**","/js/**");//配置过滤
    }
    /*@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {

                registry
                        //配置允许被跨源访问的请求路径
                        //配置允许跨源的地址，若上方配置了mapper而此处不配置地址，则默认所有地址*
                        .addMapping("/**")
                        .allowedMethods("*")// 3允许任何方法（post、get等）
                        //.allowedOrigins("*")
                        .allowedOriginPatterns("*")// 1允许任何域名使用
                        .allowedHeaders("*")// 2允许任何头
                        .allowCredentials(true)
                        .maxAge(3600L);// 4.解决跨域请求两次，预检请求的有效期，单位为秒

            }
        };
    }*/
}
