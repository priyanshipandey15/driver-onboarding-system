package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverOnboardingRequest;
import org.springframework.http.ResponseEntity;

public interface DriverOnboardingSvc {
    public ResponseEntity<DriverOnboardingRequest> onboardDriver(DriverOnboardingRequest request);
}
