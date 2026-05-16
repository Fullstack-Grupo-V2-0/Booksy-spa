package com.example.booksyspa.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.booksyspa.model.Cliente;
import com.example.booksyspa.model.EstadoPedido;
import com.example.booksyspa.model.Libro;
import com.example.booksyspa.model.Pedido;
import com.example.booksyspa.repository.ClienteRepository;
import com.example.booksyspa.repository.LibroRepository;
import com.example.booksyspa.repository.PedidoRepository;

@Service
public class PedidoService {
    // La anotación @Transactional asegura que todas las operaciones de BD se completen o ninguna.

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Pedido> getPedidos() {
        return pedidoRepository.findAll();
    }

    @Transactional
    public Pedido savePedido(Pedido pedido) {
        // 1. Validar que el cliente exista y obtener la entidad completa (manejada por JPA)
        Cliente cliente = clienteRepository.findById(pedido.getCliente().getIdCliente())
                .orElseThrow(() -> new RuntimeException("El cliente con id " + pedido.getCliente().getIdCliente() + " no existe"));

        // 2. Validar que el libro exista y obtener la entidad completa
        Libro libro = libroRepository.findById(pedido.getLibro().getIdLibro())
                .orElseThrow(() -> new RuntimeException("El libro con id " + pedido.getLibro().getIdLibro() + " no existe"));

        // 3. Crear un nuevo objeto Pedido para asegurar que no se usen datos no deseados del request
        Pedido nuevoPedido = new Pedido();
        nuevoPedido.setCliente(cliente); // Asignar la entidad completa
        nuevoPedido.setLibro(libro);     // Asignar la entidad completa

        // 4. Asignar datos de negocio
        nuevoPedido.setFechaPedido(LocalDate.now());
        nuevoPedido.setTotal(libro.getPrecio()); // El precio viene del libro que obtuvimos de la BD
        nuevoPedido.setEstado(EstadoPedido.PENDIENTE); // Usar el Enum
        nuevoPedido.setUrlDescarga(null); // La URL se genera después del pago

        return pedidoRepository.save(nuevoPedido);
    }

    public Pedido getPedidoId(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public List<Pedido> getPedidosPorCliente(int idCliente) {
        if (!clienteRepository.existsById(idCliente)) {
            return Collections.emptyList();
        }
        return pedidoRepository.findByClienteIdCliente(idCliente);
    }

    public List<Pedido> getPedidosPorLibro(int idLibro) {
        return pedidoRepository.findByLibroIdLibro(idLibro);
    }

    public List<Pedido> getPedidosPorEstado(String estadoStr) {
        try {
            // Convertimos el String a nuestro Enum de forma segura
            EstadoPedido estado = EstadoPedido.valueOf(estadoStr.toUpperCase());
            return pedidoRepository.findByEstado(estado);
        } catch (IllegalArgumentException e) {
            // Si el string no corresponde a ningún estado del Enum, devuelve una lista vacía.
            return Collections.emptyList();
        }
    }

    @Transactional
    public Pedido updatePedido(int id, Pedido pedidoActualizado) {
        // 1. Buscar el pedido existente
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("El pedido con id " + id + " no existe"));

        // 2. Lógica de actualización: Generalmente, solo se actualiza el estado de un pedido.
        // Aquí, solo permitiremos la actualización del estado.
        if (pedidoActualizado.getEstado() != null) {
            pedidoExistente.setEstado(pedidoActualizado.getEstado());
        }

        // 3. Ejemplo de lógica adicional: si el estado cambia a PAGADO, generar URL de descarga.
        if (pedidoExistente.getEstado() == EstadoPedido.PAGADO && pedidoExistente.getUrlDescarga() == null) {
            // Aquí iría la lógica para generar una URL segura. Por ahora, usamos un placeholder.
            pedidoExistente.setUrlDescarga("www.booksyspa.cl/descarga/" + pedidoExistente.getLibro().getIsbn() + "/" + UUID.randomUUID().toString());
        }

        return pedidoRepository.save(pedidoExistente);
    }

    @Transactional
    public void deletePedido(int id) {
        pedidoRepository.deleteById(id);
    }
}