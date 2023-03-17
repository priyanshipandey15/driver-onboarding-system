package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Document;
import com.driver.onboarding.com.driver.onboarding.exception.BackgroundCheckException;
import com.driver.onboarding.com.driver.onboarding.repository.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class BackgroundVerificationServiceImplTest {
    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private BackgroundVerificationServiceImpl backgroundVerificationService;

    @Test
    public void verifyBackgroundWithValidDriverIdShouldReturnTrue() {
        Long driverId = 123L;
        List<Document> documents = new ArrayList<>();
        documents.add(new Document());
        Mockito.when(documentRepository.findByDriverId(driverId)).thenReturn(documents);
        boolean result = backgroundVerificationService.verifyBackground(driverId);
        assertTrue(result);
    }

    @Test(expected = BackgroundCheckException.class)
    public void verifyBackgroundWithInvalidDriverIdShouldThrowException() {
        Long driverId = 123L;
        Mockito.when(documentRepository.findByDriverId(driverId)).thenReturn(Collections.emptyList());
        backgroundVerificationService.verifyBackground(driverId);
    }
}
