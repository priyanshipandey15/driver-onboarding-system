package com.driver.onboarding.com.driver.onboarding.exception;
public class TrackingDeviceNotFoundException extends RuntimeException {

    public TrackingDeviceNotFoundException(String message) {
        super(message);
    }

    public TrackingDeviceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

