package com.TOMY.portfolio.portfolio_backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
@Tag(name = "Health", description = "Endpoint para verificar el estado del servidor")
public class HealthController {

    @GetMapping
    @Operation(summary = "Health check", description = "Verifica que el servidor esté funcionando correctamente")
    public String health() {
        return "OK";
    }
}