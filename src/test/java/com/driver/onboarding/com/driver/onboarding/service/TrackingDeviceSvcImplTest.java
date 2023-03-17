package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dao.signup.TrackingDevice;
import com.driver.onboarding.com.driver.onboarding.dto.signup.TrackingDeviceStatus;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import com.driver.onboarding.com.driver.onboarding.repository.TrackingDeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrackingDeviceSvcImplTest {

    @InjectMocks
    private TrackingDeviceSvcImpl trackingDeviceSvc;

    @Mock
    private DriverSignUpRepository driverSignUpRepository;

    @Mock
    private TrackingDeviceRepository trackingDeviceRepository;

    @Test
    public void testShipDevice() {
        Long driverId = 1L;
        Long trackingDeviceId = 2L;

        // Mock driver
        Driver driver = new Driver();
        driver.setId(driverId);
        driver.setEmail("driver@example.com");

        // Mock tracking device
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setId(trackingDeviceId);
        trackingDevice.setDriverId(driverId);
        trackingDevice.setStatus(TrackingDeviceStatus.SHIPPED);
        UUID dispatchId = UUID.randomUUID();
        trackingDevice.setDispatch_id(dispatchId);
        Mockito.when(trackingDeviceRepository.save(any())).thenReturn(trackingDevice);

        // Test shipDevice method
        TrackingDevice result = trackingDeviceSvc.shipDevice(driverId, trackingDeviceId);

        // Assertions
        assertNotNull(result);
        assertEquals(driverId, result.getDriverId());
        assertEquals(TrackingDeviceStatus.SHIPPED, result.getStatus());
        assertNotNull(result.getDispatch_id());
        verify(trackingDeviceRepository, times(2)).save(any());
    }
}
