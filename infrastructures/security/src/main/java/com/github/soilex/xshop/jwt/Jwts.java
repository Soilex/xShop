package com.github.soilex.xshop.jwt;

import com.github.soilex.xshop.mvc.SpringContextHolder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

public class Jwts {

    private static JwtAuthenticationConfig config = SpringContextHolder.getBean(JwtAuthenticationConfig.class);

    private static final String CLAIM_AUTHORITY = "aut";

    public static Token generateToken(long userId, String... roles) {
        String subject = String.valueOf(userId);
        String authority = Arrays.stream(roles).collect(Collectors.joining(",", "ROLE_", "")).toUpperCase();
        LocalDateTime expirationDate = LocalDateTime.now().plusDays(1);
        Date expiration = Date.from(expirationDate.atZone(ZoneId.systemDefault()).toInstant());
        String audience = subject + "@" + config.getDomain();
        String issuer = config.getIssuer() + "@" + config.getDomain();
        String encodedToken = io.jsonwebtoken.Jwts.builder()
                .setSubject(subject)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setIssuer(issuer)
                .setId(UUID.randomUUID().toString())
                .setExpiration(expiration)
                .setNotBefore(new Date())
                .claim(CLAIM_AUTHORITY, authority)
                .signWith(SignatureAlgorithm.HS512, config.getSecret())
                .compact();
        return new Token(subject, audience, issuer, authority, expirationDate, encodedToken);
    }

    public static Token retrieveToken(String token) {
        Claims claims = (Claims) io.jsonwebtoken.Jwts.parser()
                .setSigningKey(config.getSecret())
                .parse(token)
                .getBody();
        return new Token(
                claims.getSubject(),
                claims.getAudience(),
                claims.getIssuer(),
                claims.get(CLAIM_AUTHORITY, String.class),
                LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault()),
                token
        );
    }
}
