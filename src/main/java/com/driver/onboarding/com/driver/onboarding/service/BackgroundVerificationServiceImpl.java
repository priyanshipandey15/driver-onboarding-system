package com.driver.onboarding.com.driver.onboarding.service;

import com.driver.onboarding.com.driver.onboarding.dao.signup.Document;
import com.driver.onboarding.com.driver.onboarding.exception.BackgroundCheckException;
import com.driver.onboarding.com.driver.onboarding.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BackgroundVerificationServiceImpl implements BackgroundVerificationService{

    @Autowired
    private DocumentRepository documentRepository;
    @Override
    public boolean verifyBackground(Long driverId) {
        List<Document> list = documentRepository.findByDriverId(driverId);
        //third party transfer to background API
        if(list.isEmpty()){
            throw new BackgroundCheckException("The provided driverId does not match the background check.");
        }
        else
            return true;
            }
        }

