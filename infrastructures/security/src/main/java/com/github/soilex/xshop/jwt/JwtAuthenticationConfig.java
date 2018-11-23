package com.github.soilex.xshop.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.security.jwt")
@Data
public class JwtAuthenticationConfig {
    private String secret;
    private String domain;
    private String issuer;
    private String tokenHeaderName = "Authorize";
    private String tokenPrefix = "Bearer";
}
