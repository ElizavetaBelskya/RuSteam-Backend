package ru.itis.rusteam.security.utils;

import org.springframework.security.core.Authentication;
import ru.itis.rusteam.security.exceptions.JWTVerificationException;

import java.util.Map;

public interface JwtUtil {
    Map<String, String> generateTokens(String subject, String authority, String issuer);

    Authentication buildAuthentication(String token) throws JWTVerificationException;
}
