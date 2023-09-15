package com.example.api_times.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.example.api_times.domain.dto.dono.DonoRequestDTO;
import com.example.api_times.domain.dto.dono.DonoResponseDTO;
import com.example.api_times.domain.exception.ResourceBadRequestException;
import com.example.api_times.domain.exception.ResourceNotFoundException;
import com.example.api_times.domain.model.Dono;
import com.example.api_times.domain.repository.DonoRepository;

@Service
public class DonoService implements ICRUDService <DonoRequestDTO, DonoResponseDTO> {
    @Autowired
    private DonoRepository donoRepository;
    @Autowired

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<DonoResponseDTO> obterTodos() {
        List<Dono> donos = donoRepository.findAll();
        return donos.stream().map(dono -> mapper.map(dono, DonoResponseDTO.class)).collect(Collectors.toList());
    }

    @Override
    public DonoResponseDTO obterPorId(Long id) {
        Optional<Dono> optDono = donoRepository.findById(id);
        if(optDono.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível enocontrar o dono com o id:" + id);
        }
        return Map.map(optDono.get(), DonoResponseDTO.class);
    }

    @Override
    public DonoResponseDTO cadastrar(DonoRequestDTO dto) {
        if(dto.getEmail()==null || dto.getSenha() == null){
            throw new ResourceBadRequestException("Email e senha são obrigatórios!");
        }
        Optional<Dono> optDono = donoRepository.findByEmail(dto.getEmail());
        if(optDono.isPresent()){
            throw new ResourceBadRequestException("Já existe um dono cadastrado com esse email!" + dto.getEmail());
        }
        Dono dono = mapper.map(dto, Dono.class);
        dono.setDataCadastro(new Date());
        String senha = passwordEncoder.encode(dono.getSenha());
        dono.setSenha(senha);
        dono.setId(null);
        dono = donoRepository.save(dono);
        return mapper.map(dono, DonoResponseDTO.class);

    }

    @Override
    public DonoResponseDTO atualizar(Long id, DonoRequestDTO dto) {
        
       DonoResponseDTO donoBanco = obterPorId(id);
        if(dto.getEmail()==null || dto.getSenha() == null){
            throw new ResourceBadRequestException("Email e senha são obrigatórios!");
        }
        Dono dono = mapper.map(dto, Dono.class);
        dono.setId(id);
        dono.setDataCadastro(donoBanco.getDataCadastro()); 
        dono.setDataInativacao(donoBanco.getDataInativacao());
        dono = donoRepository.save(dono);
        return mapper.map(dono, DonoResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        Optional<Dono> optDono = donoRepository.findById(id);
        if(optDono.isEmpty()){
            throw new ResourceNotFoundException("Não foi possível enocontrar o dono com o id:" + id);
        }
        Dono dono = optDono.get();
        dono.setDataInativacao(new Date());
        donoRepository.save(dono);
    }
}