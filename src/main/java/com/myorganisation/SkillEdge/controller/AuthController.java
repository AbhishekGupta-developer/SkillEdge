package com.myorganisation.SkillEdge.controller;

import com.myorganisation.SkillEdge.dto.request.AuthRequestDto;
import com.myorganisation.SkillEdge.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public String generateToken(@RequestBody AuthRequestDto authRequestDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequestDto.getUsername(),
                            authRequestDto.getPassword()
                    )
            );

            // Generate token
            return jwtUtil.generateToken(authRequestDto.getUsername());
        } catch(AuthenticationException e) {
            System.out.println("An exception occurred: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
