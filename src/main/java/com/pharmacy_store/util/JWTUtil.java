package com.pharmacy_store.util;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Service
public class JWTUtil {
    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;
    private final JwtEncoder jwtEncoder;

    public JWTUtil(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @Value("${jwt.secretkey}")
    private String jwtKey;
    @Value("${jwt.expiration}")
    private long expiration;

    // private SecretKey getSecretKey() {
    // byte[] keyBytes = com.nimbusds.jose.util.Base64.from(jwtKey).decode();
    // return new SecretKeySpec(keyBytes, 0, keyBytes.length,
    // JWTUtil.JWT_ALGORITHM.getName());
    // }

    // @Bean
    // public JwtEncoder jwtEncoder() {
    // return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
    // }

    public String crateToken(Authentication authentication) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.expiration, ChronoUnit.SECONDS);

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuedAt(now)
            .expiresAt(validity)
            .subject(authentication.getName())
            .claim("cuong", authentication)
            .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
    }
