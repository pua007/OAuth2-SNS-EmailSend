package com.housing.back.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Component
public class JwtProvider {

    private final Key key;

    public JwtProvider(@Value("${secret.key}") String sesecretKey) {
        this.key = Keys.hmacShaKeyFor(sesecretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String create(String userId){
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        String jwt = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setSubject(userId).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();

        return jwt;
    }

    public String validate(String jwt){
        String subject = null;

        try {
            Claims claims = Jwts.parserBuilder().setSigningKey(key)
                    .build().parseClaimsJws(jwt)
                    .getBody();
            subject = claims.getSubject();
        }catch (Exception exception){
            exception.printStackTrace();
            return  subject;
        }
        return "";
    }
}
