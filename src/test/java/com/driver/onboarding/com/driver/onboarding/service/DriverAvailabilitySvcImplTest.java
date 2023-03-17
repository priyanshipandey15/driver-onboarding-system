package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverAvailabilityRequest;
import com.driver.onboarding.com.driver.onboarding.repository.DriverOnboardRepository;
import com.driver.onboarding.com.driver.onboarding.repository.TrackingDeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class DriverAvailabilitySvcImplTest {

    @Mock
    private TrackingDeviceRepository trackingDeviceRepository;

    @Mock
    private DriverOnboardRepository driverOnboardRepository;

    @InjectMocks
    private DriverAvailabilitySvcImpl driverAvailabilitySvcImpl;

    @Test
    public void testIsAvailable() {
        DriverAvailabilityRequest driverAvailabilityRequest = new DriverAvailabilityRequest();
        driverAvailabilityRequest.setEmail("test@example.com");

        Driver driver = new Driver();
        driver.setEmail("test@example.com");
        driver.setAvailable(false);

        Mockito.when(driverOnboardRepository.findByEmail(driverAvailabilityRequest.getEmail())).thenReturn(driver);

        boolean result = driverAvailabilitySvcImpl.isAvailable(driverAvailabilityRequest);

        assertTrue(result);
        assertTrue(driver.isAvailable());
        Mockito.verify(driverOnboardRepository).save(driver);
    }

    @Test
    public void testIsAvailableNoDriverFound() {
        DriverAvailabilityRequest driverAvailabilityRequest = new DriverAvailabilityRequest();
        driverAvailabilityRequest.setEmail("test@example.com");

        Mockito.when(driverOnboardRepository.findByEmail(driverAvailabilityRequest.getEmail())).thenReturn(null);

        boolean result = driverAvailabilitySvcImpl.isAvailable(driverAvailabilityRequest);

        assertFalse(result);
        Mockito.verifyZeroInteractions(trackingDeviceRepository);
        Mockito.verify(driverOnboardRepository, never()).save(any(Driver.class));
    }
}
