package com.example.booksyspa.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booksyspa.dto.LoginRequest;
import com.example.booksyspa.integration.AuthClient;

@RestController
@RequestMapping("/api/v2/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthClient authClient;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Map<String, Object> usuario = authClient.verificarUsuarioPorEmail(loginRequest.getEmail());
            
            // Verificar que la password coincida
            String passwordGuardada = (String) usuario.get("password");
            if (!passwordGuardada.equals(loginRequest.getPassword())) {
                return ResponseEntity.status(401).body(Map.of(
                    "mensaje", "Credenciales inválidas"
                ));
            }

            return ResponseEntity.ok(Map.of(
                "mensaje", "Usuario verificado correctamente",
                "usuario", usuario
            ));
        } catch (RuntimeException e) {
            System.out.println("Error de login: " + e.getMessage());
            return ResponseEntity.status(401).body(Map.of(
                "mensaje", "Credenciales inválidas"
            ));
        }
    }
}