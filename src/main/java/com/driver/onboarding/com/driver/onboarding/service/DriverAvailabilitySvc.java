package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverAvailabilityRequest;

public interface DriverAvailabilitySvc {

    public boolean isAvailable(DriverAvailabilityRequest driverAvailabilityRequest);


}
