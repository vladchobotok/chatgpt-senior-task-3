package com.example.seniortask3.unit;

import com.example.seniortask3.model.HealthcareProvider;
import com.example.seniortask3.repository.HealthcareProviderRepository;
import com.example.seniortask3.service.HealthcareProviderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class HealthcareProviderServiceTest {

    @Mock
    private HealthcareProviderRepository healthcareProviderRepository;

    @InjectMocks
    private HealthcareProviderService healthcareProviderService;

    @Test
    public void testGetAllHealthcareProviders() {
        // Prepare test data
        List<HealthcareProvider> healthcareProviders = new ArrayList<>();
        healthcareProviders.add(new HealthcareProvider(1L, "Provider 1", "Specialty 1"));
        healthcareProviders.add(new HealthcareProvider(2L, "Provider 2", "Specialty 2"));

        // Mock the repository method
        Mockito.when(healthcareProviderRepository.findAll()).thenReturn(healthcareProviders);

        // Call the service method
        List<HealthcareProvider> result = healthcareProviderService.getAllHealthcareProviders();

        // Assert the result
        Assertions.assertEquals(2, result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetHealthcareProviderById() {
        // Prepare test data
        HealthcareProvider healthcareProvider = new HealthcareProvider(1L, "Provider 1", "Specialty 1");

        // Mock the repository method
        Mockito.when(healthcareProviderRepository.findById(1L)).thenReturn(Optional.of(healthcareProvider));

        // Call the service method
        HealthcareProvider result = healthcareProviderService.getHealthcareProviderById(1L);

        // Assert the result
        Assertions.assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
    public void testSaveHealthcareProvider() {
        // Prepare test data
        HealthcareProvider healthcareProviderToSave = new HealthcareProvider(1L, "Provider 1", "Specialty 1");
        HealthcareProvider savedHealthcareProvider = new HealthcareProvider(1L, "Provider 1", "Specialty 1");

        // Mock the repository method
        Mockito.when(healthcareProviderRepository.save(healthcareProviderToSave)).thenReturn(savedHealthcareProvider);

        // Call the service method
        HealthcareProvider result = healthcareProviderService.saveHealthcareProvider(healthcareProviderToSave);

        // Assert the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedHealthcareProvider.getId(), result.getId());
        Assertions.assertEquals(savedHealthcareProvider.getName(), result.getName());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteHealthcareProviderById() {
        // Prepare test data
        Long healthcareProviderIdToDelete = 1L;

        // Call the service method
        healthcareProviderService.deleteHealthcareProviderById(healthcareProviderIdToDelete);

        // Verify that the repository method was called with the correct healthcareProviderId
        Mockito.verify(healthcareProviderRepository, Mockito.times(1)).deleteById(healthcareProviderIdToDelete);
    }

    // Add more tests for other healthcare provider-related service methods if needed
}
