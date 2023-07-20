package com.example.seniortask3.repository;

import com.example.seniortask3.model.HealthcareProvider;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthcareProviderRepository extends JpaRepository<HealthcareProvider, Long> {
    // You can add custom query methods here if needed
}

