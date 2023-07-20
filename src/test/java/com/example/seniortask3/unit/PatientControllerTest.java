package com.example.seniortask3.unit;

import com.example.seniortask3.controller.PatientController;
import com.example.seniortask3.model.Patient;
import com.example.seniortask3.service.PatientService;
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

public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllPatients() {
        // Prepare test data
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L, "John", "Doe", 1001L));
        patients.add(new Patient(2L, "Jane", "Smith", 1002L));

        // Mock the service method
        when(patientService.getAllPatients()).thenReturn(patients);

        // Call the controller method
        List<Patient> result = patientController.getAllPatients();

        // Assert the result
        assertEquals(patients.size(), result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetPatientById() {
        // Prepare test data
        Patient patient = new Patient(1L, "John", "Doe", 1001L);

        // Mock the service method
        when(patientService.getPatientById(1L)).thenReturn(patient);

        // Call the controller method
        ResponseEntity<Patient> responseEntity = patientController.getPatientById(1L);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the patient in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(patient.getId(), responseEntity.getBody().getId());
        assertEquals(patient.getFirstName(), responseEntity.getBody().getFirstName());
        // Add more assertions as needed
    }

    @Test
    public void testSavePatient() {
        // Prepare test data
        Patient patientToSave = new Patient(1L, "John", "Doe", 1001L);
        Patient savedPatient = new Patient(1L, "John", "Doe", 1001L);

        // Mock the service method
        when(patientService.savePatient(patientToSave)).thenReturn(savedPatient);

        // Call the controller method
        ResponseEntity<Patient> responseEntity = patientController.savePatient(patientToSave);

        // Assert the response status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Assert the saved patient in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(savedPatient.getId(), responseEntity.getBody().getId());
        assertEquals(savedPatient.getFirstName(), responseEntity.getBody().getFirstName());
        // Add more assertions as needed
    }

    @Test
    public void testUpdatePatient() {
        // Prepare test data
        Long patientId = 1L;
        Patient existingPatient = new Patient(patientId, "John", "Doe", 1001L);
        Patient updatedPatient = new Patient(patientId, "Updated", "Patient", 1001L);

        // Mock the service method
        when(patientService.getPatientById(patientId)).thenReturn(existingPatient);
        when(patientService.savePatient(updatedPatient)).thenReturn(updatedPatient);

        // Call the controller method
        ResponseEntity<Patient> responseEntity = patientController.updatePatient(patientId, updatedPatient);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the updated patient in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedPatient.getId(), responseEntity.getBody().getId());
        assertEquals(updatedPatient.getFirstName(), responseEntity.getBody().getFirstName());
        // Add more assertions as needed
    }

    @Test
    public void testDeletePatient() {
        // Prepare test data
        Long patientId = 1L;

        // Call the controller method
        ResponseEntity<Void> responseEntity = patientController.deletePatient(patientId);

        // Assert the response status
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called with the correct patientId
        verify(patientService, times(1)).deletePatientById(patientId);
    }

    // Add more tests for other patient-related endpoints if needed
}
