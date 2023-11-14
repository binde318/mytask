package com.netbinde.brilloconnectest.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JwtProviderTest {

    private static final String SECRET_KEY = "mySecretKey123";

    @Test
    void generateAndVerifyJWT() {
        JwtProvider jwtProvider = new JwtProvider();

        String jwt = jwtProvider.generateJWT("testuser", SECRET_KEY);

        String verificationResult = jwtProvider.verifyJWT(jwt, SECRET_KEY);

        assertEquals("verification pass", verificationResult);
    }

    @Test
    void verifyInvalidJWT() {
        JwtProvider jwtProvider = new JwtProvider();

        String invalidJwt = "invalidjwtstring";
        String verificationResult = jwtProvider.verifyJWT(invalidJwt, SECRET_KEY);

        assertEquals("verification fails", verificationResult);
    }
}
