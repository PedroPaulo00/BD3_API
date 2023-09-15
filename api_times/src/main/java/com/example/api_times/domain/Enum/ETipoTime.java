package com.example.api_times.domain.Enum;

public enum ETipoTime {
    MASCULINO("Masculino"),
    FEMININO("Feminino");
    private String valor;

    private ETipoTime(String valor){
        this.valor = valor;
    }

    public String getvalor() {
        return valor;
    }
}
