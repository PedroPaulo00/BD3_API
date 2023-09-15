package com.example.api_times.domain.dto.times;

import java.util.Date;

import com.example.api_times.domain.Enum.ETipoTime;

public class TimeRequestDTO {
    private Long id;
    private String descricao;
    private ETipoTime tipo;
    private Double valor;
    private Date dataCadastro;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public ETipoTime getTipo() {
        return tipo;
    }
    public void setTipo(ETipoTime tipo) {
        this.tipo = tipo;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}