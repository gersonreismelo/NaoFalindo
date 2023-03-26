package br.com.fiap.naofalindo.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(0)
    private BigDecimal valor;
    @NotNull
    private LocalDate data;
    @NotBlank @Size(min = 5, max = 30)
    private String descricao;
    
    protected Despesa() {
    }
    
    public Despesa(BigDecimal valor, LocalDate data, String descricao) {
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
    
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
    
    public LocalDate getData() {
        return data;
    }
    
    public void setData(LocalDate data) {
        this.data = data;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "Despesa [valor=" + valor + ", data=" + data + ", descricao=" + descricao + "]";
    }
}
