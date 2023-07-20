package com.example.seniortask3.model;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private Long patientId; // You may use a unique identifier for the patient

    public Patient(String firstName, String lastName, Long patientId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patientId = patientId;
    }

    public Patient(Long id, String firstName, String lastName, Long patientId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patientId = patientId;
    }

    public Patient(){

    }

    // Add other patient attributes (e.g., contact details, medical history) as needed

    // Constructors, getters, and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}

