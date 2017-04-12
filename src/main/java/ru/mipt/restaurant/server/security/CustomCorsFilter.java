package ru.mipt.restaurant.server.security;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomCorsFilter extends CorsFilter {

    private static final String ALLOW_ORIGIN_HEADER = "Access-Control-Allow-Origin";
    private static final String EXPOSE_HEADERS_HEADER = "Access-Control-Expose-Headers";

    public CustomCorsFilter(CorsConfigurationSource configSource) {
        super(configSource);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        super.doFilterInternal(request, response, filterChain);
        if (response.getHeader(ALLOW_ORIGIN_HEADER) == null) {
            response.addHeader(ALLOW_ORIGIN_HEADER, "*");
        }
        if (response.getHeader(EXPOSE_HEADERS_HEADER) == null) {
            response.addHeader(EXPOSE_HEADERS_HEADER, "*");
        }
    }
}
