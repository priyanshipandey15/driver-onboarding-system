package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Document;
import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverOnboardingRequest;
import com.driver.onboarding.com.driver.onboarding.dao.signup.TrackingDevice;
import com.driver.onboarding.com.driver.onboarding.dto.signup.TrackingDeviceStatus;
import com.driver.onboarding.com.driver.onboarding.repository.DocumentRepository;
import com.driver.onboarding.com.driver.onboarding.repository.DriverOnboardRepository;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class DriverOnboardingSvcImplTest {

    @Mock
    private DriverOnboardRepository driverRepository;

    @Mock
    private DocumentRepository documentRepository;

    @Mock
    private DriverSignUpRepository driverSignUpRepository;

    @Mock
    private BackgroundVerificationServiceImpl backgroundVerificationService;

    @Mock
    private TrackingDeviceSvcImpl trackingDeviceService;

    @InjectMocks
    private DriverOnboardingSvcImpl driverOnboardingSvc;

    private DriverOnboardingRequest driverOnboardingRequest;

    @Mock
    private DriverSignUpSvcImpl driverSignUp;

    private Driver driver;

    private Document document;

    @Before
    public void setUp() throws Exception {
        driverOnboardingRequest = new DriverOnboardingRequest();
        driverOnboardingRequest.setEmail("test@test.com");
        driverOnboardingRequest.setTrackingDeviceId(1L);
        driverOnboardingRequest.setDocumentIds(Arrays.asList(1L, 2L, 3L));
        driverOnboardingRequest.setEmail("test@test.com");

        driver = new Driver();
        driver.setId(1L);

        document = new Document();
        document.setDriverId(1L);
        document.setDocumentIds(Arrays.asList(1L, 2L, 3L).toString());

    }

    @Test
    public void testOnboardDriver() {
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setStatus(TrackingDeviceStatus.SHIPPING);
        trackingDevice.setDriverId(driver.getId());
        trackingDevice.setActive(true);
        Mockito.when(driverSignUpRepository.findByEmail(driverOnboardingRequest.getEmail()))
                .thenReturn(driver);
        Mockito.when(documentRepository.save(Mockito.any(Document.class))).thenReturn(document);
        Mockito.when(trackingDeviceService.shipDevice(driver.getId(), driverOnboardingRequest.getTrackingDeviceId()))
                .thenReturn(trackingDevice);

        ResponseEntity<DriverOnboardingRequest> response = driverOnboardingSvc.onboardDriver(driverOnboardingRequest);

        Assert.assertEquals(response.getStatusCode(),HttpStatus.OK );

    }

    @Test
    public void testOnboardDriverWithInvalidEmail() {
        Mockito.when(driverSignUpRepository.findByEmail(driverOnboardingRequest.getEmail()))
                .thenReturn(null);

        ResponseEntity<DriverOnboardingRequest> response = driverOnboardingSvc.onboardDriver(driverOnboardingRequest);

        Assert.assertEquals(response.getStatusCode(),HttpStatus.CONFLICT );
        Assert.assertNull(response.getBody());
    }
}
