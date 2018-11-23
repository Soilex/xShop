package com.github.soilex.xshop.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@WebFilter(urlPatterns = "/**", filterName = "jwtAuthenticationFilter")
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtAuthenticationConfig config;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorize = request.getHeader(config.getTokenHeaderName());
        String tokenPrefix = config.getTokenPrefix().trim() + " ";
        if (StringUtils.startsWithIgnoreCase(authorize, tokenPrefix)) {
            String token = authorize.substring(tokenPrefix.length());
            Token jwt;
            try {
                jwt = Jwts.retrieveToken(token);
            } catch (ExpiredJwtException e) {
                chain.doFilter(request, response);
                return;
            }

            String roles = jwt.getAuthority();
            List<GrantedAuthority> authorities = StringUtils.isBlank(roles) ? null :
                    Arrays.stream(roles.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwt, null, authorities);
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }
}
