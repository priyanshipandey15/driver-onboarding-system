package com.driver.onboarding.com.driver.onboarding.repository;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverSignupRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverSignUpRepository extends JpaRepository<Driver,Long> {
    Driver findByEmail(String email);
    Driver findByPhone(String phone);
    void deleteByEmail(String email);
}
