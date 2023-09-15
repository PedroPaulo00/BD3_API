package com.example.api_times.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.api_times.domain.model.Time;
import com.example.api_times.domain.service.TimeService;

import java.util.List;

@RestController
@RequestMapping("/times")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping
    public List<Time> listarTimes() {
        return timeService.listarTimes();
    }

    @GetMapping("/{id}")
public ResponseEntity<Time> buscarTimePorId(@PathVariable Long id) {
    try {
        Time Time = timeService.buscarTimePorId(id);
        return ResponseEntity.ok(Time);
    } catch (RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


    @PostMapping
    public Time criarTime(@RequestBody Time Time) {
        return timeService.salvarTime(Time);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Time> atualizarTime(@PathVariable Long id, @RequestBody Time timeAtualizado) {
        try {
            Time timeExistente = timeService.buscarTimePorId(id);
    
            timeExistente.setNome(timeAtualizado.getNome());
            timeExistente.setCidade(timeAtualizado.getCidade());
            timeExistente.setEstado(timeAtualizado.getEstado());
            timeExistente.setAnoFundacao(timeAtualizado.getAnoFundacao());
            timeExistente.setTreinador(timeAtualizado.getTreinador());
            timeExistente.setCorPrincipal(timeAtualizado.getCorPrincipal());
            timeExistente.setCorSecundaria(timeAtualizado.getCorSecundaria());
    
            return ResponseEntity.ok(timeService.salvarTime(timeExistente));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirTime(@PathVariable Long id) {
        try {
            Time timeExistente = timeService.buscarTimePorId(id);
            timeService.excluirTime(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Time não encontrado");
        }
    }
    

}

