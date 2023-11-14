package com.netbinde.brilloconnectest.configuration;

import com.netbinde.brilloconnectest.appcontants.AppContants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


import java.util.Base64;

public class JwtProvider {
    public String generateJWT(String subject, String secretKey) {
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secretKey.getBytes()))
                .compact();
    }

    public String verifyJWT(String jwt, String secretKey) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(secretKey.getBytes()))
                    .parseClaimsJws(jwt)
                    .getBody();

            System.out.println("Parsed Claims: " + claims);

            return AppContants.VERIFICATION_PASS;
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return AppContants.VERIFICATION_FAIL;
        }
    }
}
