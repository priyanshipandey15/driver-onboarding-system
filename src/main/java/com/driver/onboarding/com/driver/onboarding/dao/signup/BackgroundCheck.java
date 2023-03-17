package com.driver.onboarding.com.driver.onboarding.dao.signup;

import com.driver.onboarding.com.driver.onboarding.dto.signup.BackgroundCheckStatus;

import javax.persistence.*;

@Entity
@Table(name = "background_check")
public class BackgroundCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BackgroundCheckStatus status;

    // Constructors, getters and setters

    public BackgroundCheck() {
    }

    public BackgroundCheck(Long driverId) {
        this.driverId = driverId;
        this.status = BackgroundCheckStatus.PENDING;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public BackgroundCheckStatus getStatus() {
        return status;
    }

    public void setStatus(BackgroundCheckStatus status) {
        this.status = status;
    }
}

