package com.myorganisation.SkillEdge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ServerController {

    @GetMapping
    public String serverStatus() {
        return "Server is live!";
    }
}
