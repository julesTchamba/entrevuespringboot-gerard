package com.example.entrevueSpringBoot.component;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FiltreInterdomaine extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {
        //j'autorise toutes les URI
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        //juste besoin de GET et POST
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST");
        httpServletResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type, accept, x-req");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}