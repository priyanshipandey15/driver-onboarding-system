package com.driver.onboarding.com.driver.onboarding.dao.signup;

import com.driver.onboarding.com.driver.onboarding.dto.signup.TrackingDeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tracking_device")
public class TrackingDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "status")
    private TrackingDeviceStatus status;

    @Column(name = "dispatch_id")
    private UUID dispatch_id;

    @Column(name = "device_id")
    private String deviceId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive(boolean b) {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public TrackingDeviceStatus getStatus() {
        return status;
    }

    public void setStatus(TrackingDeviceStatus status) {
        this.status = status;
    }

    public UUID getDispatch_id() {
        return dispatch_id;
    }

    public void setDispatch_id(UUID dispatch_id) {
        this.dispatch_id = dispatch_id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}

