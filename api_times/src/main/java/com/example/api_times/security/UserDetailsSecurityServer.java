package com.example.pokemon.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.pokemon.domain.model.Dono;
import com.example.pokemon.domain.repository.DonoRepository;

@Component
public class UserDetailsSecurityServer implements UserDetailsService {

    @Autowired
    private DonoRepository donoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Optional<Dono> optDono = donoRepository.findByEmail(username);
       if(optDono.isEmpty()){
        throw new UsernameNotFoundException("Usuário ou senha Inválidos.");
       }
       return optDono.get();
    }
    
}