package com.example.api_times.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api_times.domain.model.Dono;

public interface DonoRepository extends JpaRepository<Dono, Long> {
    Optional<Dono> findByEmail (String email);
}