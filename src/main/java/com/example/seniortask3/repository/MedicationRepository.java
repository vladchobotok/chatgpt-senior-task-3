package com.example.seniortask3.repository;

import com.example.seniortask3.model.Medication;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long> {
    // You can add custom query methods here if needed
}

