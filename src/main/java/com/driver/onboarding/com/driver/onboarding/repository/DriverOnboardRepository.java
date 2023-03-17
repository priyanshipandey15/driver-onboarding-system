package com.driver.onboarding.com.driver.onboarding.repository;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverOnboardRepository extends JpaRepository<Driver, Long> {
    public Driver findByEmail(String email);
}
