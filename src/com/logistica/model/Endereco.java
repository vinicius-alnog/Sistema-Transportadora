package com.logistica.model;

public class Endereco {

    private String rua;
    private Integer numero;
    private String cidade;
    private Integer cep;

    public Endereco() {}

    public Endereco(String rua, Integer numero, String cidade, Integer cep) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }
    public Integer getNumero() {
        return numero;
    }
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }
    public Integer getCep() {
        return cep;
    }
    public void setCep(Integer cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "Rua " + rua + ", " + numero + " - " + cidade + " (" + cep + ")";
    }
}
