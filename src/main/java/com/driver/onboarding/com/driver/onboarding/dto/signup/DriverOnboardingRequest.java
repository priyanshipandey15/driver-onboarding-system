package com.driver.onboarding.com.driver.onboarding.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Entity
public class DriverOnboardingRequest {

    @NotBlank
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotEmpty
    private List<Long> documentIds;

    @NotNull
    private Long backgroundCheckId;

    @NotNull
    private Long trackingDeviceId;

}

