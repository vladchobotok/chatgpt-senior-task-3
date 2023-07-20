package com.example.seniortask3.unit;

import com.example.seniortask3.controller.HealthcareProviderController;
import com.example.seniortask3.model.HealthcareProvider;
import com.example.seniortask3.service.HealthcareProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class HealthcareProviderControllerTest {

    @Mock
    private HealthcareProviderService healthcareProviderService;

    @InjectMocks
    private HealthcareProviderController healthcareProviderController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllHealthcareProviders() {
        // Prepare test data
        List<HealthcareProvider> providers = new ArrayList<>();
        providers.add(new HealthcareProvider(1L, "Dr. Smith", "Cardiologist"));
        providers.add(new HealthcareProvider(2L, "Dr. Johnson", "Dermatologist"));

        // Mock the service method
        when(healthcareProviderService.getAllHealthcareProviders()).thenReturn(providers);

        // Call the controller method
        List<HealthcareProvider> result = healthcareProviderController.getAllHealthcareProviders();

        // Assert the result
        assertEquals(providers.size(), result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetHealthcareProviderById() {
        // Prepare test data
        HealthcareProvider provider = new HealthcareProvider(1L, "Dr. Smith", "Cardiologist");

        // Mock the service method
        when(healthcareProviderService.getHealthcareProviderById(1L)).thenReturn(provider);

        // Call the controller method
        ResponseEntity<HealthcareProvider> responseEntity = healthcareProviderController.getHealthcareProviderById(1L);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the healthcare provider in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(provider.getId(), responseEntity.getBody().getId());
        assertEquals(provider.getName(), responseEntity.getBody().getName());
        // Add more assertions as needed
    }

    @Test
    public void testSaveHealthcareProvider() {
        // Prepare test data
        HealthcareProvider providerToSave = new HealthcareProvider(1L, "Dr. Smith", "Cardiologist");
        HealthcareProvider savedProvider = new HealthcareProvider(1L, "Dr. Smith", "Cardiologist");

        // Mock the service method
        when(healthcareProviderService.saveHealthcareProvider(providerToSave)).thenReturn(savedProvider);

        // Call the controller method
        ResponseEntity<HealthcareProvider> responseEntity = healthcareProviderController.saveHealthcareProvider(providerToSave);

        // Assert the response status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Assert the saved healthcare provider in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(savedProvider.getId(), responseEntity.getBody().getId());
        assertEquals(savedProvider.getName(), responseEntity.getBody().getName());
        // Add more assertions as needed
    }

    @Test
    public void testUpdateHealthcareProvider() {
        // Prepare test data
        Long providerId = 1L;
        HealthcareProvider existingProvider = new HealthcareProvider(providerId, "Dr. Smith", "Cardiologist");
        HealthcareProvider updatedProvider = new HealthcareProvider(providerId, "Dr. Johnson", "Dermatologist");

        // Mock the service method
        when(healthcareProviderService.getHealthcareProviderById(providerId)).thenReturn(existingProvider);
        when(healthcareProviderService.saveHealthcareProvider(updatedProvider)).thenReturn(updatedProvider);

        // Call the controller method
        ResponseEntity<HealthcareProvider> responseEntity = healthcareProviderController.updateHealthcareProvider(providerId, updatedProvider);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the updated healthcare provider in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedProvider.getId(), responseEntity.getBody().getId());
        assertEquals(updatedProvider.getName(), responseEntity.getBody().getName());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteHealthcareProvider() {
        // Prepare test data
        Long providerId = 1L;

        // Call the controller method
        ResponseEntity<Void> responseEntity = healthcareProviderController.deleteHealthcareProvider(providerId);

        // Assert the response status
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called with the correct providerId
        verify(healthcareProviderService, times(1)).deleteHealthcareProviderById(providerId);
    }

    // Add more tests for other healthcare provider-related endpoints if needed
}
