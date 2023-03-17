package com.driver.onboarding.com.driver.onboarding.repository;

import com.driver.onboarding.com.driver.onboarding.dao.signup.TrackingDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackingDeviceRepository extends JpaRepository<TrackingDevice, Long> {

    Optional<TrackingDevice> findByDeviceId(String deviceId);

    boolean existsByDeviceId(String deviceId);

    boolean existsByDriverId(Long id);

    Optional<TrackingDevice> findByDriverId(Long id);

}
