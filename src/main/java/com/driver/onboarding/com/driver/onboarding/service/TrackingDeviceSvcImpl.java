package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.TrackingDevice;
import com.driver.onboarding.com.driver.onboarding.dto.signup.TrackingDeviceStatus;
import com.driver.onboarding.com.driver.onboarding.repository.TrackingDeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TrackingDeviceSvcImpl implements TrackingDeviceService{
    @Autowired
    private TrackingDeviceRepository trackingDeviceRepository;
    @Override
    public TrackingDevice shipDevice(Long driverId, Long trackingDeviceId) {
        UUID dispatchId = UUID.randomUUID();
        TrackingDevice trackingDevice = new TrackingDevice();
        trackingDevice.setDispatch_id(dispatchId);
        trackingDevice.setDriverId(driverId);
        trackingDevice.setStatus(TrackingDeviceStatus.SHIPPING);
        trackingDeviceRepository.save(trackingDevice);

        //send to third party shipping agency & once doc is delivered we can update status as shipped for our reference

        trackingDevice.setStatus(TrackingDeviceStatus.SHIPPED);
        trackingDeviceRepository.save(trackingDevice);
        return trackingDevice;

    }
}
