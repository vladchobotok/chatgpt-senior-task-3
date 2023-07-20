package com.example.seniortask3.service;

import com.example.seniortask3.model.HealthcareProvider;
import com.example.seniortask3.repository.HealthcareProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HealthcareProviderService {

    private final HealthcareProviderRepository healthcareProviderRepository;

    @Autowired
    public HealthcareProviderService(HealthcareProviderRepository healthcareProviderRepository) {
        this.healthcareProviderRepository = healthcareProviderRepository;
    }

    // Service methods for healthcare provider-related business logic

    public List<HealthcareProvider> getAllHealthcareProviders() {
        return healthcareProviderRepository.findAll();
    }

    public HealthcareProvider getHealthcareProviderById(Long id) {
        return healthcareProviderRepository.findById(id).orElse(null);
    }

    public HealthcareProvider saveHealthcareProvider(HealthcareProvider healthcareProvider) {
        return healthcareProviderRepository.save(healthcareProvider);
    }

    public void deleteHealthcareProviderById(Long id) {
        healthcareProviderRepository.deleteById(id);
    }

    // Add other healthcare provider-related service methods as needed
}

