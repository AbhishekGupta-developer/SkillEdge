package com.myorganisation.SkillEdge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth ->
                                auth
                                        .requestMatchers("/").permitAll()
                                        .requestMatchers("/api/user", "/api/user/**").permitAll()
                                        .requestMatchers("/api/**").authenticated()
                                        .anyRequest().authenticated()
                )
                .formLogin(form ->form.permitAll())
                .logout(logout -> logout.permitAll());

        return httpSecurity.build();
    }
}
