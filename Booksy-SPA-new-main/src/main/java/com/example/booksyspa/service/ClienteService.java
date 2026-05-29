package com.example.booksyspa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.booksyspa.model.Cliente;
import com.example.booksyspa.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes() {
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente) {
        // Validar que el rut no esté repetido
        if (clienteRepository.findByRut(cliente.getRut()).isPresent()) {
            throw new RuntimeException("Ya existe un cliente con el rut " + cliente.getRut());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente getClienteId(int id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente con id " + id + " no existe"));
    }

    public Cliente getClientePorRut(String rut) {
        return clienteRepository.findByRut(rut)
                .orElseThrow(() -> new RuntimeException("El cliente con rut " + rut + " no existe"));
    }

    @Transactional
    public Cliente updateCliente(int id, Cliente clienteActualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente con id " + id + " no existe"));

        // Validar que el RUT no se duplique si se intenta cambiar
        if (clienteActualizado.getRut() != null && !clienteActualizado.getRut().equals(clienteExistente.getRut())) {
            clienteRepository.findByRut(clienteActualizado.getRut()).ifPresent(c -> {
                throw new RuntimeException("El RUT " + clienteActualizado.getRut() + " ya está en uso por otro cliente.");
            });
            clienteExistente.setRut(clienteActualizado.getRut());
        }

        // Actualizar otros campos de forma selectiva
        Optional.ofNullable(clienteActualizado.getNombre()).ifPresent(clienteExistente::setNombre);
        Optional.ofNullable(clienteActualizado.getApellido()).ifPresent(clienteExistente::setApellido);
        Optional.ofNullable(clienteActualizado.getEmail()).ifPresent(clienteExistente::setEmail);
        Optional.ofNullable(clienteActualizado.getTelefono()).ifPresent(clienteExistente::setTelefono);

        return clienteRepository.save(clienteExistente);
    }

    @Transactional
    public void deleteCliente(int id) {
        clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El cliente con id " + id + " no existe"));
        clienteRepository.deleteById(id);
    }
}