package com.driver.onboarding.com.driver.onboarding.dao.signup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    private String licenseNumber;

    @NotBlank
    private String carModel;

    @NotBlank
    private String carColor;

    @NotBlank
    private String carLicensePlate;

    private boolean isAvailable=false;
    public Driver(String firstName, String lastName, String email,
                  String phone, String password, String licenseNumber, String carModel,
                  String carColor, String carLicensePlate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.licenseNumber = licenseNumber;
        this.carModel = carModel;
        this.carColor = carColor;
        this.carLicensePlate = carLicensePlate;
    }

    public Driver(String phone, String email, List<Long> documentIds, Long backgroundCheckId, Long trackingDeviceId) {
    }


}
