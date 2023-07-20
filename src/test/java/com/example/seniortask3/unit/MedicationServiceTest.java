package com.example.seniortask3.unit;

import com.example.seniortask3.model.Medication;
import com.example.seniortask3.repository.MedicationRepository;
import com.example.seniortask3.service.MedicationService;
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
public class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService;

    @Test
    public void testGetAllMedications() {
        // Prepare test data
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication(1L, "Medication 1"));
        medications.add(new Medication(2L, "Medication 2"));

        // Mock the repository method
        Mockito.when(medicationRepository.findAll()).thenReturn(medications);

        // Call the service method
        List<Medication> result = medicationService.getAllMedications();

        // Assert the result
        Assertions.assertEquals(2, result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetMedicationById() {
        // Prepare test data
        Medication medication = new Medication(1L, "Medication 1");

        // Mock the repository method
        Mockito.when(medicationRepository.findById(1L)).thenReturn(Optional.of(medication));

        // Call the service method
        Medication result = medicationService.getMedicationById(1L);

        // Assert the result
        Assertions.assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
    public void testSaveMedication() {
        // Prepare test data
        Medication medicationToSave = new Medication(1L, "Medication 1");
        Medication savedMedication = new Medication(1L, "Medication 1");

        // Mock the repository method
        Mockito.when(medicationRepository.save(medicationToSave)).thenReturn(savedMedication);

        // Call the service method
        Medication result = medicationService.saveMedication(medicationToSave);

        // Assert the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedMedication.getId(), result.getId());
        Assertions.assertEquals(savedMedication.getName(), result.getName());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteMedicationById() {
        // Prepare test data
        Long medicationIdToDelete = 1L;

        // Call the service method
        medicationService.deleteMedicationById(medicationIdToDelete);

        // Verify that the repository method was called with the correct medicationId
        Mockito.verify(medicationRepository, Mockito.times(1)).deleteById(medicationIdToDelete);
    }

    // Add more tests for other medication-related service methods if needed
}
