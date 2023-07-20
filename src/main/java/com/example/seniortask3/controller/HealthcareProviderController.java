package com.example.seniortask3.controller;

import com.example.seniortask3.model.HealthcareProvider;
import com.example.seniortask3.service.HealthcareProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/healthcareproviders")
public class HealthcareProviderController {

    private final HealthcareProviderService healthcareProviderService;

    @Autowired
    public HealthcareProviderController(HealthcareProviderService healthcareProviderService) {
        this.healthcareProviderService = healthcareProviderService;
    }

    @GetMapping("/healthcareproviders")
    public List<HealthcareProvider> getAllHealthcareProviders() {
        return healthcareProviderService.getAllHealthcareProviders();
    }

    @GetMapping("/healthcareproviders/{id}")
    public ResponseEntity<HealthcareProvider> getHealthcareProviderById(@PathVariable Long id) {
        HealthcareProvider healthcareProvider = healthcareProviderService.getHealthcareProviderById(id);
        if (healthcareProvider != null) {
            return new ResponseEntity<>(healthcareProvider, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/healthcareproviders")
    public ResponseEntity<HealthcareProvider> saveHealthcareProvider(@Valid @RequestBody HealthcareProvider healthcareProvider) {
        HealthcareProvider savedHealthcareProvider = healthcareProviderService.saveHealthcareProvider(healthcareProvider);
        return new ResponseEntity<>(savedHealthcareProvider, HttpStatus.CREATED);
    }

    @PutMapping("/healthcareproviders/{id}")
    public ResponseEntity<HealthcareProvider> updateHealthcareProvider(@PathVariable Long id,@Valid @RequestBody HealthcareProvider updatedHealthcareProvider) {
        HealthcareProvider healthcareProvider = healthcareProviderService.getHealthcareProviderById(id);
        if (healthcareProvider != null) {
            updatedHealthcareProvider.setId(id);
            HealthcareProvider savedHealthcareProvider = healthcareProviderService.saveHealthcareProvider(updatedHealthcareProvider);
            return new ResponseEntity<>(savedHealthcareProvider, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/healthcareproviders/{id}")
    public ResponseEntity<Void> deleteHealthcareProvider(@PathVariable Long id) {
        healthcareProviderService.deleteHealthcareProviderById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add other healthcare provider-related endpoints as needed
}

