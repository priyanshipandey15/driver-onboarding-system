package com.driver.onboarding.com.driver.onboarding.controller.signup;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverSignupRequest;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import com.driver.onboarding.com.driver.onboarding.service.DriverAvailabilitySvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverOnboardingSvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverOperationSvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverSignUpSvcImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

    @InjectMocks
    private DriverController driverController;

    @Mock
    private DriverSignUpSvcImpl driverSignUpSvc;

    @Mock
    private DriverOnboardingSvcImpl driverOnboardingSvc;

    @Mock
    private DriverSignUpRepository driverSignUpRepository;

    @Mock
    private DriverAvailabilitySvcImpl driverAvailabilitySvc;

    @Mock
    private DriverOperationSvcImpl driverOperationSvc;

    @Test
    public void testSignup() {
        // Prepare input data
        DriverSignupRequest request = new DriverSignupRequest();
        request.setFirstName("priyanshi");
        request.setLastName("pandey");
        request.setEmail("pp15@example.com");
        request.setPhone("1234567890");
        request.setPassword("password");
        request.setLicenseNumber("123456");
        request.setCarModel("Mercedes_maybach");
        request.setCarColor("Black");
        request.setCarLicensePlate("ABC123");

        // mock the findByEmail and findByPhone methods of the driverSignUpRepository
        when(driverSignUpRepository.findByEmail(request.getEmail())).thenReturn(null);
        when(driverSignUpRepository.findByPhone(request.getPhone())).thenReturn(null);

        // call the signup method of driverController
        ResponseEntity<DriverSignupRequest> responseEntity = driverController.signup(request);

        // verify that the driverSignUpSvc.signup() method is called with the correct argument
        ArgumentCaptor<Driver> argument = ArgumentCaptor.forClass(Driver.class);
        verify(driverSignUpSvc).signup(argument.capture());
        assertEquals(request.getFirstName(), argument.getValue().getFirstName());

        // verify that the status code of the response entity is HttpStatus.OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}