package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverSignUpSvcImpl  implements  DriverSignUpService{
    @Autowired
    private DriverSignUpRepository driverRepository;

    public void signup(Driver driver) {
        driverRepository.save(driver);
    }
}

