package com.heimydias.pixdemo.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity(name = "tb_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal saldo;
    @Column(unique = true)
    private String chavePix;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Pix> pix;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public List<Pix> getPix() {
        return pix;
    }

    public void setPix(List<Pix> pix) {
        this.pix = pix;
    }
}
