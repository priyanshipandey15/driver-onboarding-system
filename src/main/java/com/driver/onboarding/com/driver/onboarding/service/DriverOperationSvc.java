package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface DriverOperationSvc {

    public List<Driver> getAllDrivers();

    public Driver getUniqueDrivers(String email);


    Driver updateDriver(DriverUpdateRequest updateDriver);

    public void deleteDriver(String email);

}
