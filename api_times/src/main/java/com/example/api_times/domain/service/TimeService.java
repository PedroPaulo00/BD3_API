package com.example.api_times.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api_times.domain.model.Time;
import com.example.api_times.domain.repository.TimeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    @Autowired
    private TimeRepository timeRepository;

    public List<Time> listarTimes() {
        return timeRepository.findAll();
    }

    public Time buscarTimePorId(Long id) {
    return timeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Time não encontrado"));
}

    

    public Time salvarTime(Time time) {
        return timeRepository.save(time);
    }

    public void excluirTime(Long id) {
        timeRepository.deleteById(id);
    }
}
