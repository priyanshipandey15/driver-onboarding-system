package com.driver.onboarding.com.driver.onboarding.controller.signup;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverAvailabilityRequest;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverOnboardingRequest;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverSignupRequest;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverUpdateRequest;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import com.driver.onboarding.com.driver.onboarding.service.DriverAvailabilitySvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverOnboardingSvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverOperationSvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverSignUpSvcImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DriverControllerTest {

    @InjectMocks
    private DriverController driverController;

    @Mock
    private DriverSignUpSvcImpl driverSignUpSvc;

    @Mock
    private DriverAvailabilitySvcImpl driverAvailabilityService;

    @Mock
    private DriverOnboardingSvcImpl driverOnboardingSvc;

    @Mock
    private DriverSignUpRepository driverSignUpRepository;

    @Mock
    private DriverAvailabilitySvcImpl driverAvailabilitySvc;

    @Mock
    private DriverOperationSvcImpl driverOperationSvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
//        driverController = new DriverController(driverAvailabilityService);
    }

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
    @Test
    public void onboardDriverSuccess() {
        DriverOnboardingRequest driverOnboardingRequest = new DriverOnboardingRequest();
        // Create driver object and set its properties
        Driver driver = new Driver();
        driver.setFirstName("priyanshi");
        driver.setLastName("pandey");
        driver.setEmail("pp15@example.com");
        driver.setPhone("1234567890");
        driver.setPassword("password");
        driver.setLicenseNumber("ABC123");
        driver.setCarModel("Mercedes_maybach");
        driver.setCarColor("Black");
        driver.setCarLicensePlate("ABC123");
        when(driverOnboardingSvc.onboardDriver(driverOnboardingRequest))
                .thenReturn(new ResponseEntity<>(driverOnboardingRequest, HttpStatus.OK));

        ResponseEntity<DriverOnboardingRequest> response = driverController.onboardDriver(driverOnboardingRequest);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(driverOnboardingRequest, response.getBody());
    }

    @Test
    public void testGetAllDrivers() {
        // Create some test drivers
        Driver driver1 = new Driver();
        driver1.setId(1L);
//        driver1.setName("John Doe");
        driver1.setEmail("johndoe@example.com");

        Driver driver2 = new Driver();
        driver2.setId(2L);
//        driver2.setName("Jane Smith");
        driver2.setEmail("janesmith@example.com");

        // Mock the driverOperationSvc to return these test drivers
        List<Driver> testDrivers = Arrays.asList(driver1, driver2);
        when(driverOperationSvc.getAllDrivers()).thenReturn(testDrivers);

        // Call the controller method
        ResponseEntity<List<Driver>> response = driverController.getAllDrivers();

        // Assert that the response contains the correct drivers
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Driver> drivers = response.getBody();
        assertNotNull(drivers);
        assertEquals(2, drivers.size());
//        assertEquals("John Doe", drivers.get(0).getName());
        assertEquals("janesmith@example.com", drivers.get(1).getEmail());
    }
    @Test
    public void testGetDriverbyId() {
        // Create a test driver
        Driver testDriver = new Driver();
        testDriver.setId(1L);
        testDriver.setFirstName("John");
        testDriver.setLastName("Doe");
        testDriver.setEmail("johndoe@example.com");

        // Mock the driverOperationSvc to return the test driver
        when(driverOperationSvc.getUniqueDrivers("johndoe@example.com")).thenReturn(testDriver);

        // Call the controller method
        ResponseEntity<Driver> response = driverController.getDriverbyId("johndoe@example.com");

        // Assert that the response contains the correct driver
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Driver driver = response.getBody();
        assertNotNull(driver);
        assertEquals("John", driver.getFirstName());
        assertEquals("Doe", driver.getLastName());
        assertEquals("johndoe@example.com", driver.getEmail());
    }

    @Test
    public void testDeleteDriverbyId() {
        // Call the controller method
        ResponseEntity<String> response = driverController.deleteDriverbyId("johndoe@example.com");

        // Assert that the response is correct
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted", response.getBody());

        // Verify that the driverOperationSvc.deleteDriver() method was called with the correct email
        verify(driverOperationSvc).deleteDriver("johndoe@example.com");
    }



    @Test
    public void testUpdateDriver() {
        DriverUpdateRequest updateRequest = new DriverUpdateRequest();
        updateRequest.setEmail("john.doe@example.com");
        updateRequest.setPhone("+1-202-555-0101");
        updateRequest.setCarLicensePlate("XYZ123456");

        Driver updatedDriver = new Driver();
        updatedDriver.setEmail("john.doe@example.com");
        updatedDriver.setFirstName("John");
        updatedDriver.setLastName("Doe");
        updatedDriver.setPhone("+1-202-555-0101");
        updatedDriver.setLicenseNumber("XYZ123456");

        Mockito.when(driverOperationSvc.updateDriver(updateRequest)).thenReturn(updatedDriver);

        ResponseEntity<Driver> responseEntity = driverController.updateDriver(updateRequest);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(updatedDriver, responseEntity.getBody());
        Mockito.verify(driverOperationSvc, Mockito.times(1)).updateDriver(updateRequest);
    }

}