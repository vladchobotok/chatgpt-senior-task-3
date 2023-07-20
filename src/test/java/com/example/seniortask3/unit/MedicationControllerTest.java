package com.example.seniortask3.unit;

import com.example.seniortask3.controller.MedicationController;
import com.example.seniortask3.model.Medication;
import com.example.seniortask3.service.MedicationService;
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

public class MedicationControllerTest {

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private MedicationController medicationController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllMedications() {
        // Prepare test data
        List<Medication> medications = new ArrayList<>();
        medications.add(new Medication(1L, "Medicine A"));
        medications.add(new Medication(2L, "Medicine B"));

        // Mock the service method
        when(medicationService.getAllMedications()).thenReturn(medications);

        // Call the controller method
        List<Medication> result = medicationController.getAllMedications();

        // Assert the result
        assertEquals(medications.size(), result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetMedicationById() {
        // Prepare test data
        Medication medication = new Medication(1L, "Medicine A");

        // Mock the service method
        when(medicationService.getMedicationById(1L)).thenReturn(medication);

        // Call the controller method
        ResponseEntity<Medication> responseEntity = medicationController.getMedicationById(1L);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the medication in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(medication.getId(), responseEntity.getBody().getId());
        assertEquals(medication.getName(), responseEntity.getBody().getName());
        // Add more assertions as needed
    }

    @Test
    public void testSaveMedication() {
        // Prepare test data
        Medication medicationToSave = new Medication(1L, "Medicine A");
        Medication savedMedication = new Medication(1L, "Medicine A");

        // Mock the service method
        when(medicationService.saveMedication(medicationToSave)).thenReturn(savedMedication);

        // Call the controller method
        ResponseEntity<Medication> responseEntity = medicationController.saveMedication(medicationToSave);

        // Assert the response status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Assert the saved medication in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(savedMedication.getId(), responseEntity.getBody().getId());
        assertEquals(savedMedication.getName(), responseEntity.getBody().getName());
        // Add more assertions as needed
    }

    @Test
    public void testUpdateMedication() {
        // Prepare test data
        Long medicationId = 1L;
        Medication existingMedication = new Medication(medicationId, "Medicine A");
        Medication updatedMedication = new Medication(medicationId, "Updated Medicine");

        // Mock the service method
        when(medicationService.getMedicationById(medicationId)).thenReturn(existingMedication);
        when(medicationService.saveMedication(updatedMedication)).thenReturn(updatedMedication);

        // Call the controller method
        ResponseEntity<Medication> responseEntity = medicationController.updateMedication(medicationId, updatedMedication);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the updated medication in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedMedication.getId(), responseEntity.getBody().getId());
        assertEquals(updatedMedication.getName(), responseEntity.getBody().getName());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteMedication() {
        // Prepare test data
        Long medicationId = 1L;

        // Call the controller method
        ResponseEntity<Void> responseEntity = medicationController.deleteMedication(medicationId);

        // Assert the response status
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called with the correct medicationId
        verify(medicationService, times(1)).deleteMedicationById(medicationId);
    }

    // Add more tests for other medication-related endpoints if needed
}
