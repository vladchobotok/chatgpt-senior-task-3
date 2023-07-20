package com.example.seniortask3.repository;

import com.example.seniortask3.model.Appointment;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // You can add custom query methods here if needed
}
