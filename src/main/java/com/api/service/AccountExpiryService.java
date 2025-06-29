package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.api.entity.Developer;
import com.api.repository.DeveloperRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccountExpiryService {
    
	
    private final DeveloperService developerService;
    
    @Autowired
    private DeveloperRepository devRepo;
    
    public AccountExpiryService(DeveloperService developerService) {
        this.developerService = developerService;
    }
    
    
    @Scheduled(cron = "10 4 15 * * ?")  // Runs daily at midnight
    public void disableExpiredAccounts() {
        List<Developer> developers = devRepo.findAll();
        LocalDate today = LocalDate.now();
        
        developers.forEach(dev -> {
            if (dev.getAccountExpiryDate() != null && 
                dev.getAccountExpiryDate().isBefore(today)) {
                dev.setActive(false); // You'll need to add this field to your entity
                developerService.saveData(dev);
            }
        });
    }
}
