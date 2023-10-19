package com.heimydias.pixdemo.model;

import java.math.BigDecimal;

public class CreateUserRequest {
    private String name;
    private BigDecimal saldo;
    private String chavePix;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }
}
