package com.example.seniortask3.controller;

import com.example.seniortask3.model.Medication;
import com.example.seniortask3.service.MedicationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medications")
public class MedicationController {

    private final MedicationService medicationService;

    @Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping("/medications")
    public List<Medication> getAllMedications() {
        return medicationService.getAllMedications();
    }

    @GetMapping("/medications/{id}")
    public ResponseEntity<Medication> getMedicationById(@PathVariable Long id) {
        Medication medication = medicationService.getMedicationById(id);
        if (medication != null) {
            return new ResponseEntity<>(medication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/medications")
    public ResponseEntity<Medication> saveMedication(@Valid @RequestBody Medication medication) {
        Medication savedMedication = medicationService.saveMedication(medication);
        return new ResponseEntity<>(savedMedication, HttpStatus.CREATED);
    }

    @PutMapping("/medications/{id}")
    public ResponseEntity<Medication> updateMedication(@PathVariable Long id,@Valid @RequestBody Medication updatedMedication) {
        Medication medication = medicationService.getMedicationById(id);
        if (medication != null) {
            updatedMedication.setId(id);
            Medication savedMedication = medicationService.saveMedication(updatedMedication);
            return new ResponseEntity<>(savedMedication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/medications/{id}")
    public ResponseEntity<Void> deleteMedication(@PathVariable Long id) {
        medicationService.deleteMedicationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add other medication-related endpoints as needed
}

