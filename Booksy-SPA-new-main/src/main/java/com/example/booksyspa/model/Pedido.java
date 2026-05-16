package com.example.booksyspa.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false)
    private Libro libro;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaPedido;
    private BigDecimal total;
    // Usar un Enum para el estado. (otra clase que define los posibles estados y no acepta otros valores)
    // EstadoPedido: PENDIENTE, PAGADO, DESCARGADO)
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;
    private String urlDescarga;
}