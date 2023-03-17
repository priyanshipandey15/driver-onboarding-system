package com.driver.onboarding.com.driver.onboarding.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverAvailabilityRequest {

    @NotBlank
    Long driverId;

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;
}
