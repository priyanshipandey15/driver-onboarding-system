package com.driver.onboarding.com.driver.onboarding.repository;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByDriverId(Long driverId);

}
