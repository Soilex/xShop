package com.github.soilex.xshop.jwt;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter(AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString
public class Token {
    private String subject;
    private String audience;
    private String issuer;
    private String authority;
    private LocalDateTime expiration;
    private String data;
}
