package com.TOMY.portfolio.portfolio_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class PruebaController {
    @RestController
    @RequestMapping("/api/health")
    public class HealthController {
    
        @GetMapping
        public String health() {
            return "OK";
        }
    } 
}