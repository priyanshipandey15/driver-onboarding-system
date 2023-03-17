package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverAvailabilityRequest;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class DriverAvailabilitySvcImpl implements DriverAvailabilitySvc{

    @Autowired
    DriverSignUpRepository driverSignUpRepository;

    @Override
    public boolean isAvailable(DriverAvailabilityRequest driverAvailabilityRequest) {
        Driver driver = driverSignUpRepository.findByEmail(driverAvailabilityRequest.getEmail());
        if(driver == null){
            log.error("No such driver exists, first sign-up driver");
            return false;
        }
        driver.setAvailable(true);
        driverSignUpRepository.save(driver);
        log.debug("This driver is good to take rides");
        return true;
}

}