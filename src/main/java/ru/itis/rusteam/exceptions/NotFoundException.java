package ru.itis.rusteam.exceptions;

/**
 * @author Elizaveta Belskaya
 */
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
