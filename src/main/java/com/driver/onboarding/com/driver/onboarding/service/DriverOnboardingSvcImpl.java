package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Document;
import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverOnboardingRequest;
import com.driver.onboarding.com.driver.onboarding.repository.DocumentRepository;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DriverOnboardingSvcImpl implements DriverOnboardingSvc{
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DriverSignUpRepository driverSignUpRepository;

    @Autowired
    private BackgroundVerificationServiceImpl backgroundVerificationService;

    @Autowired
    private TrackingDeviceSvcImpl trackingDeviceService;

    @Override
    public ResponseEntity<DriverOnboardingRequest> onboardDriver(DriverOnboardingRequest driverOnboardingRequest) {
        Driver driver = driverSignUpRepository.findByEmail(driverOnboardingRequest.getEmail());
        if(driver == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        Document document = Document.builder().driverId(driver.getId())
                .type(driverOnboardingRequest.getEmail()).documentIds(driverOnboardingRequest.getDocumentIds().toString())
                .build();
        documentRepository.save(document);
        backgroundVerificationService.verifyBackground(driver.getId());
        trackingDeviceService.shipDevice(driver.getId(), driverOnboardingRequest.getTrackingDeviceId());

        return ResponseEntity.ok(driverOnboardingRequest);

    }
}
