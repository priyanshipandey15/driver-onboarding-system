package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.TrackingDevice;

public interface TrackingDeviceService {

    public TrackingDevice shipDevice(Long driverId, Long trackingDeviceId) ;
}
