package com.driver.onboarding.com.driver.onboarding.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DriverUpdateRequest {

    @NotBlank
    @Email
    private String email;

    private String phone;
    private String password;
    private String carModel;
    private String carColor;
    private String carLicensePlate;

    // Getters and setters
    // ...

    // toString() method for debugging/logging purposes

    @Override
    public String toString() {
        return "DriverSignupRequest{" +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", carModel='" + carModel + '\'' +
                ", carColor='" + carColor + '\'' +
                ", carLicensePlate='" + carLicensePlate + '\'' +
                '}';
    }
}

