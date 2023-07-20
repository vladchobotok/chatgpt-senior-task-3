package com.example.seniortask3.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "healthcare_provider_id", nullable = false)
    private HealthcareProvider healthcareProvider;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    // Add other appointment attributes (e.g., description, status) as needed

    // Constructors, getters, and setters

    public Appointment(Long id, Patient patient, HealthcareProvider healthcareProvider, LocalDateTime appointmentDateTime) {
        this.id = id;
        this.patient = patient;
        this.healthcareProvider = healthcareProvider;
        this.appointmentDateTime = appointmentDateTime;
    }

    public Appointment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public HealthcareProvider getHealthcareProvider() {
        return healthcareProvider;
    }

    public void setHealthcareProvider(HealthcareProvider healthcareProvider) {
        this.healthcareProvider = healthcareProvider;
    }

    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }
}

