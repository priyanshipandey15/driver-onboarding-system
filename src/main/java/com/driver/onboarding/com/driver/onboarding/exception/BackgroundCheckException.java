package com.driver.onboarding.com.driver.onboarding.exception;
public class BackgroundCheckException extends RuntimeException {

    public BackgroundCheckException(String message) {
        super(message);
    }

    public BackgroundCheckException(String message, Throwable cause) {
        super(message, cause);
    }
}
