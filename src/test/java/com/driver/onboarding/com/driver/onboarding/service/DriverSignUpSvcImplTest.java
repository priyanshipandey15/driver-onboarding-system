package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class DriverSignUpSvcImplTest {

    @Mock
    private DriverSignUpRepository driverRepository;

    @InjectMocks
    private DriverSignUpSvcImpl driverService;

    @Test
    public void testSignup() {
        // create a driver object
        Driver driver = new Driver();
        driver.setFirstName("John");
        driver.setLastName("Doe");
        driver.setEmail("johndoe@example.com");
        driver.setPhone("1234567890");

        // mock the save method of driver repository
        Mockito.when(driverRepository.save(driver)).thenReturn(driver);

        // call the signup method of driver service
        driverService.signup(driver);

        // verify that the save method of driver repository was called
        Mockito.verify(driverRepository, times(1)).save(driver);
    }
}
