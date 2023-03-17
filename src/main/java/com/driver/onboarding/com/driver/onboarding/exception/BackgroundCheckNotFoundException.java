package com.driver.onboarding.com.driver.onboarding.exception;

public class BackgroundCheckNotFoundException extends RuntimeException {

    public BackgroundCheckNotFoundException(String message) {
        super(message);
    }

    public BackgroundCheckNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
