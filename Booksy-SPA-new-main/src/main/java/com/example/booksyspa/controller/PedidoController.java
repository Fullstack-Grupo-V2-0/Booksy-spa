package com.example.booksyspa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.booksyspa.model.Pedido;
import com.example.booksyspa.service.PedidoService;

@RestController
@RequestMapping("/api/v2/pedidos")
@CrossOrigin("*") // Permite peticiones desde cualquier origen (útil para desarrollo con SPA)
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        Pedido pedido = pedidoService.getPedidoId(id);
        if (pedido != null) {
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.savePedido(pedido);
            return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Si el servicio lanza una excepción (ej: cliente o libro no existe), devolvemos un error 400.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoActualizado = pedidoService.updatePedido(id, pedido);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        try {
            pedidoService.deletePedido(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            // Si el pedido no existe, el delete puede fallar.
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoints de búsqueda

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<Pedido>> getPedidosByCliente(@PathVariable int idCliente) {
        List<Pedido> pedidos = pedidoService.getPedidosPorCliente(idCliente);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/libro/{idLibro}")
    public ResponseEntity<List<Pedido>> getPedidosByLibro(@PathVariable int idLibro) {
        List<Pedido> pedidos = pedidoService.getPedidosPorLibro(idLibro);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<Pedido>> getPedidosByEstado(@RequestParam String estado) {
        List<Pedido> pedidos = pedidoService.getPedidosPorEstado(estado);
        return ResponseEntity.ok(pedidos);
    }
}