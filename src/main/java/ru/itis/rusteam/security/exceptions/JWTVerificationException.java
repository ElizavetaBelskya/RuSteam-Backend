package ru.itis.rusteam.security.exceptions;

public class JWTVerificationException extends RuntimeException {

    public JWTVerificationException(Throwable cause) {
        super(cause);
    }

}
