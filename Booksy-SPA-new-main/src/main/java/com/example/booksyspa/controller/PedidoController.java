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
@CrossOrigin("*")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<Pedido>> getAllPedidos() {
        return ResponseEntity.ok(pedidoService.getPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable int id) {
        return ResponseEntity.ok(pedidoService.getPedidoId(id));
    }

    @PostMapping
    public ResponseEntity<Pedido> createPedido(@RequestBody Pedido pedido) {
        Pedido nuevoPedido = pedidoService.savePedido(pedido);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable int id, @RequestBody Pedido pedido) {
        Pedido pedidoActualizado = pedidoService.updatePedido(id, pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePedido(@PathVariable int id) {
        pedidoService.deletePedido(id);
        return ResponseEntity.noContent().build();
    }

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
