package com.example.api_times.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebMvc
public class WebSecurityConfig {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AutoConfiguration authenticationConfiguration;
    @Autowired
    private UserDetailsSecurityServer userDetailsSecurityServer;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Authentication authenticationManager(AopAutoConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.headers().frameOptions().disable().and()
        .cors().and()
        .csrf().disable()
        .authorizeHttpRequests((auth) ->
         auth.requestMatchers(HttpMethod.POST, "/api/treinadores").permitAll()
        .anyRequest().authenticated())
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager(authenticationConfiguration), jwtUtil));
        http.addFilter(new JwtAutorizationFilter(authenticationManager(authenticationConfiguration), jwtUtil, userDetailsSecurityServer));

        return http.build();
    }
}