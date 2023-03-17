package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverUpdateRequest;
import com.driver.onboarding.com.driver.onboarding.exception.DriverNotFoundException;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class DriverOperationSvcImpl implements DriverOperationSvc {

    @Autowired
    DriverSignUpRepository driverSignUpRepository;
    @Override
    public List<Driver> getAllDrivers() {
       return driverSignUpRepository.findAll();
    }

    @Override
    public Driver getUniqueDrivers(String email) {
        Optional<Driver> driver = Optional.ofNullable(driverSignUpRepository.findByEmail(email));
        return  driver.get();
    }

    @Override
    public Driver updateDriver(DriverUpdateRequest updateDriver) {

        // Optional<Driver> db_object = Optional.ofNullable(driverSignUpRepository.find(email));

            Driver driver = driverSignUpRepository.findByEmail(updateDriver.getEmail());
            if(driver == null){
                throw new DriverNotFoundException("Driver with the given email not found");
            }
            //override it
            driver.setPassword(updateDriver.getPassword());
            driver.setPhone(updateDriver.getPhone());
            driver.setCarColor(updateDriver.getCarColor());
            driver.setCarLicensePlate(updateDriver.getCarLicensePlate());
            driver.setCarColor(updateDriver.getCarColor());
            return driverSignUpRepository.save(driver);


    }

    @Transactional
    public void deleteDriver(String email) {
//        if(email==null){
//            return null;
//        }
//        else {
            Driver driver = driverSignUpRepository.findByEmail(email);
            driverSignUpRepository.deleteByEmail(email);
            log.info("Driver deleted with {id}", email);
//            return driver;
//        }
    }
}
