package com.driver.onboarding.com.driver.onboarding.controller.signup;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Driver;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverAvailabilityRequest;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverOnboardingRequest;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverSignupRequest;
import com.driver.onboarding.com.driver.onboarding.dto.signup.DriverUpdateRequest;
import com.driver.onboarding.com.driver.onboarding.repository.DriverSignUpRepository;
import com.driver.onboarding.com.driver.onboarding.service.DriverAvailabilitySvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverOnboardingSvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverOperationSvcImpl;
import com.driver.onboarding.com.driver.onboarding.service.DriverSignUpSvcImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/drivers")
@Api(value = "Driver Onboarding API", tags = "Driver Onboarding")
public class DriverController {

    @Autowired
    private DriverSignUpSvcImpl driverSignupSvc;

    @Autowired
    private DriverOnboardingSvcImpl driverOnboardSvc;

    @Autowired
    private DriverSignUpRepository driverSignUpRepository;

    @Autowired
    private DriverAvailabilitySvcImpl driverAvailabilitySvc;

    @Autowired
    private DriverOperationSvcImpl driverOperationSvc;

    @PostMapping("/signup")
    @ApiOperation(value = "Sign-up a new driver", response = DriverSignupRequest.class)
    public ResponseEntity<DriverSignupRequest> signup(@RequestBody DriverSignupRequest request) {
        if (driverSignUpRepository.findByEmail(request.getEmail()) != null ||
                driverSignUpRepository.findByPhone(request.getPhone()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        // Create and save the driver object
        Driver driver = new Driver(request.getFirstName(), request.getLastName(), request.getEmail(), request.getPhone(),
                request.getPassword(), request.getLicenseNumber(), request.getCarModel(),
                request.getCarColor(), request.getCarLicensePlate());
        driverSignupSvc.signup(driver);
        return ResponseEntity.ok(request);
    }


    @PostMapping("/onboarding")
    @ApiOperation(value = "Onboard a new driver", response = Driver.class)
    public ResponseEntity<DriverOnboardingRequest> onboardDriver(@RequestBody DriverOnboardingRequest driverOnboardingRequest) {
        ResponseEntity<DriverOnboardingRequest> driverOnboardingRequestResponseEntity =
                driverOnboardSvc.onboardDriver(driverOnboardingRequest);
        return driverOnboardingRequestResponseEntity;
    }

    @PostMapping("/availability")
    public ResponseEntity<DriverAvailabilityRequest> driverAvailability(@RequestBody DriverAvailabilityRequest driverAvailabilityRequest) {
        if (driverAvailabilitySvc.isAvailable(driverAvailabilityRequest)) {
            log.info("Driver is Available to take rides");
            return ResponseEntity.ok(driverAvailabilityRequest);
        }
        log.info("Driver is Available to take rides");
        return (ResponseEntity<DriverAvailabilityRequest>) ResponseEntity.notFound();

    }

    @GetMapping("all/driver")
    public ResponseEntity<List<Driver>> getAllDrivers() {
        log.info("Listing all the drivers");
        return ResponseEntity.ok(driverOperationSvc.getAllDrivers());
    }
    @GetMapping("/driver/{email}")
    public ResponseEntity<Driver> getDriverbyId(String email) {
        log.info("Listing driver with {email}",email);
        return  ResponseEntity.ok(driverOperationSvc.getUniqueDrivers(email));
    }

    @DeleteMapping("driver/{email}")
    public ResponseEntity<String> deleteDriverbyId(String email){
        driverOperationSvc.deleteDriver(email);
        return ResponseEntity.ok("Deleted");
    }

    @PutMapping("/updateDriver")
    public ResponseEntity<Driver> updateDriver(DriverUpdateRequest driverUpdateRequest){
        log.info("Updating driver details");
        return ResponseEntity.ok(driverOperationSvc.updateDriver(driverUpdateRequest));
    }

}

