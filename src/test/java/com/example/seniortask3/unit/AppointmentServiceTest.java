package com.example.seniortask3.unit;

import com.example.seniortask3.model.Appointment;
import com.example.seniortask3.model.HealthcareProvider;
import com.example.seniortask3.model.Patient;
import com.example.seniortask3.repository.AppointmentRepository;
import com.example.seniortask3.service.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    public void testGetAllAppointments() {
        // Prepare test data
        List<Appointment> appointments = new ArrayList<>();
        Patient patient1 = new Patient(1L, "John", "Doe", 1001L);
        Patient patient2 = new Patient(2L, "Jane", "Smith", 1002L);
        HealthcareProvider healthcareProvider1 = new HealthcareProvider(1L, "Dr. Smith", "Cardiologist");
        HealthcareProvider healthcareProvider2 = new HealthcareProvider(2L, "Dr. Johnson", "Dermatologist");
        appointments.add(new Appointment(1L, patient1, healthcareProvider1, LocalDateTime.now()));
        appointments.add(new Appointment(2L, patient2, healthcareProvider2, LocalDateTime.now()));

        // Mock the repository method
        Mockito.when(appointmentRepository.findAll()).thenReturn(appointments);

        // Call the service method
        List<Appointment> result = appointmentService.getAllAppointments();

        // Assert the result
        Assertions.assertEquals(2, result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetAppointmentById() {
        // Prepare test data
        Patient patient = new Patient(1L, "John", "Doe", 1001L);
        HealthcareProvider healthcareProvider = new HealthcareProvider(1L, "Dr. Smith", "Cardiologist");
        Appointment appointment = new Appointment(1L, patient, healthcareProvider, LocalDateTime.now());

        // Mock the repository method
        Mockito.when(appointmentRepository.findById(1L)).thenReturn(Optional.of(appointment));

        // Call the service method
        Appointment result = appointmentService.getAppointmentById(1L);

        // Assert the result
        Assertions.assertNotNull(result);
        // Add more assertions as needed
    }

    @Test
    public void testSaveAppointment() {
        // Prepare test data
        Patient patient = new Patient(1L, "John", "Doe", 1001L);
        HealthcareProvider healthcareProvider = new HealthcareProvider(1L, "Dr. Smith", "Cardiologist");
        Appointment appointmentToSave = new Appointment(1L, patient, healthcareProvider, LocalDateTime.now());
        Appointment savedAppointment = new Appointment(1L, patient, healthcareProvider, LocalDateTime.now());

        // Mock the repository method
        Mockito.when(appointmentRepository.save(appointmentToSave)).thenReturn(savedAppointment);

        // Call the service method
        Appointment result = appointmentService.saveAppointment(appointmentToSave);

        // Assert the result
        Assertions.assertNotNull(result);
        Assertions.assertEquals(savedAppointment.getId(), result.getId());
        Assertions.assertEquals(savedAppointment.getPatient(), result.getPatient());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteAppointmentById() {
        // Prepare test data
        Long appointmentIdToDelete = 1L;

        // Call the service method
        appointmentService.deleteAppointmentById(appointmentIdToDelete);

        // Verify that the repository method was called with the correct appointmentId
        Mockito.verify(appointmentRepository, Mockito.times(1)).deleteById(appointmentIdToDelete);
    }

    // Add more tests for other appointment-related service methods if needed
}
