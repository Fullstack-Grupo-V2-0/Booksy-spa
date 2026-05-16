package com.example.booksyspa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.booksyspa.model.EstadoPedido;
import com.example.booksyspa.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    // Busca pedidos por el ID de la entidad Cliente relacionada.
    List<Pedido> findByClienteIdCliente(int idCliente);

    // Busca pedidos por el ID de la entidad Libro relacionada.
    List<Pedido> findByLibroIdLibro(int idLibro);

    // Busca por el tipo Enum, lo que es más seguro que usar un String.
    List<Pedido> findByEstado(EstadoPedido estado);
}