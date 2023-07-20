package com.example.seniortask3.unit;

import com.example.seniortask3.model.Patient;
import com.example.seniortask3.repository.PatientRepository;
import com.example.seniortask3.service.PatientService;
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
public class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    public void testGetAllPatients() {
        // Prepare test data
        List<Patient> patients = new ArrayList<>();
        patients.add(new Patient(1L, "John", "Doe", 1001L));
        patients.add(new Patient(2L, "Jane", "Smith", 1002L));

        // Mock the repository method
        Mockito.when(patientRepository.findAll()).thenReturn(patients);

        // Call the service method
        List<Patient> result = patientService.getAllPatients();

        // Assert the result
        Assertions.assertEquals(2, result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetPatientById() {
        // Prepare test data
        Patient patient = new Patient(1L, "John", "Doe", 1001L);

        // Mock the repository method
        Mockito.when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Call the service method
        Patient result = patientService.getPatientById(1L);

        // Assert the result
        Assertions.assertNotNull(result);
        // Add more assertions as needed
    }
    @Test
    public void testSavePatient() {
        // Prepare test data
        Patient patientToSave = new Patient(1L, "John", "Doe", 1001L);
        Patient savedPatient = new Patient(1L, "John", "Doe", 1001L);

        // Mock the repository method
        Mockito.when(patientRepository.save(patientToSave)).thenReturn(savedPatient);

        // Call the service method
        Patient result = patientService.savePatient(patientToSave);

        // Assert the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedPatient.getId(), result.getId());
        Assertions.assertEquals(savedPatient.getFirstName(), result.getFirstName());
        // Add more assertions as needed
    }

    @Test
    public void testDeletePatientById() {
        // Prepare test data
        Long patientIdToDelete = 1L;

        // Call the service method
        patientService.deletePatientById(patientIdToDelete);

        // Verify that the repository method was called with the correct patientId
        Mockito.verify(patientRepository, Mockito.times(1)).deleteById(patientIdToDelete);
    }

}
