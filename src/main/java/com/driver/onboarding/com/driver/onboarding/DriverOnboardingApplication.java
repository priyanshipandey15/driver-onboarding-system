package com.driver.onboarding.com.driver.onboarding;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.driver.onboarding.com.driver.onboarding"})
@EnableJpaRepositories("com.driver.onboarding.com.driver.onboarding.repository")
@Slf4j
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DriverOnboardingApplication {
	public static void main(String[] args) {
		SpringApplication.run(DriverOnboardingApplication.class, args);
		log.info("Started main application....");
	}
}
