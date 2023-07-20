package com.example.seniortask3.unit;

import com.example.seniortask3.controller.AppointmentController;
import com.example.seniortask3.model.Appointment;
import com.example.seniortask3.service.AppointmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllAppointments() {
        // Prepare test data
        List<Appointment> appointments = new ArrayList<>();
        appointments.add(new Appointment(1L, null, null, LocalDateTime.now()));
        appointments.add(new Appointment(2L, null, null, LocalDateTime.now().plusHours(1)));

        // Mock the service method
        when(appointmentService.getAllAppointments()).thenReturn(appointments);

        // Call the controller method
        List<Appointment> result = appointmentController.getAllAppointments();

        // Assert the result
        assertEquals(appointments.size(), result.size());
        // Add more assertions as needed
    }

    @Test
    public void testGetAppointmentById() {
        // Prepare test data
        Appointment appointment = new Appointment(1L, null, null, LocalDateTime.now());

        // Mock the service method
        when(appointmentService.getAppointmentById(1L)).thenReturn(appointment);

        // Call the controller method
        ResponseEntity<Appointment> responseEntity = appointmentController.getAppointmentById(1L);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the appointment in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(appointment.getId(), responseEntity.getBody().getId());
        // Add more assertions as needed
    }

    @Test
    public void testSaveAppointment() {
        // Prepare test data
        Appointment appointmentToSave = new Appointment(1L, null, null, LocalDateTime.now());
        Appointment savedAppointment = new Appointment(1L, null, null, LocalDateTime.now());

        // Mock the service method
        when(appointmentService.saveAppointment(appointmentToSave)).thenReturn(savedAppointment);

        // Call the controller method
        ResponseEntity<Appointment> responseEntity = appointmentController.saveAppointment(appointmentToSave);

        // Assert the response status
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        // Assert the saved appointment in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(savedAppointment.getId(), responseEntity.getBody().getId());
        // Add more assertions as needed
    }

    @Test
    public void testUpdateAppointment() {
        // Prepare test data
        Long appointmentId = 1L;
        Appointment existingAppointment = new Appointment(appointmentId, null, null, LocalDateTime.now());
        Appointment updatedAppointment = new Appointment(appointmentId, null, null, LocalDateTime.now().plusHours(1));

        // Mock the service method
        when(appointmentService.getAppointmentById(appointmentId)).thenReturn(existingAppointment);
        when(appointmentService.saveAppointment(updatedAppointment)).thenReturn(updatedAppointment);

        // Call the controller method
        ResponseEntity<Appointment> responseEntity = appointmentController.updateAppointment(appointmentId, updatedAppointment);

        // Assert the response status
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Assert the updated appointment in the response body
        assertNotNull(responseEntity.getBody());
        assertEquals(updatedAppointment.getId(), responseEntity.getBody().getId());
        // Add more assertions as needed
    }

    @Test
    public void testDeleteAppointment() {
        // Prepare test data
        Long appointmentId = 1L;

        // Call the controller method
        ResponseEntity<Void> responseEntity = appointmentController.deleteAppointment(appointmentId);

        // Assert the response status
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());

        // Verify that the service method was called with the correct appointmentId
        verify(appointmentService, times(1)).deleteAppointmentById(appointmentId);
    }

    // Add more tests for other appointment-related endpoints if needed
}
