package com.example.api_times.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api_times.domain.model.Time;


@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

}
