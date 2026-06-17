package com.cotacao.seguros.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    private final JwtTokenProvider tokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String method = request.getMethod();
        String path = request.getRequestURI();
        String header = request.getHeader("Authorization");

        log.info(">>> [{}] {} | Authorization: {}", method, path,
                header != null ? header.substring(0, Math.min(header.length(), 30)) + "..." : "null");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (tokenProvider.validateToken(token)) {
                String username = tokenProvider.getUsernameFromToken(token);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info(">>> Authenticated user: {} | isAuthenticated: {} | authorities: {}",
                        username, auth.isAuthenticated(), auth.getAuthorities());
            } else {
                log.warn(">>> Invalid JWT token");
            }
        } else {
            log.info(">>> No Bearer token found in header");
        }

        log.info(">>> Authentication after filter: {} | isAuthenticated: {}",
                SecurityContextHolder.getContext().getAuthentication(),
                SecurityContextHolder.getContext().getAuthentication() != null
                        ? SecurityContextHolder.getContext().getAuthentication().isAuthenticated()
                        : "N/A");

        filterChain.doFilter(request, response);
    }
}
