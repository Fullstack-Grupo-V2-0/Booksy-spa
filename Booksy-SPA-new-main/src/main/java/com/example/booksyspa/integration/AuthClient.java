package com.example.booksyspa.integration;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class AuthClient {

    private final RestClient restClient;

    // La URL base se inyecta desde application.properties
    public AuthClient(@Value("${usuario-service.url}") String usuarioServiceUrl) {
        this.restClient = RestClient.builder().baseUrl(usuarioServiceUrl).build();
    }

    // Consulta al microservicio de usuarios si existe un usuario con ese email
    @SuppressWarnings("unchecked")
    public Map<String, Object> verificarUsuarioPorEmail(String email) {
        return this.restClient.get()
                .uri("/email/{email}", email)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new RuntimeException("Usuario no encontrado con email: " + email);
                })
                .body(Map.class);
    }
}
