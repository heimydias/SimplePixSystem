package com.heimydias.pixdemo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "tb_pix")
public class Pix {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTransaction;
    @Column(precision = 13, scale = 2)
    private BigDecimal valor;

    public Pix() {
    }

    public Pix(BigDecimal valor) {
        this.valor = valor;
    }

    public UUID getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(UUID idTransaction) {
        this.idTransaction = idTransaction;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
