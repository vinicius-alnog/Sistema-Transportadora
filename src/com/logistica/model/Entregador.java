package com.logistica.model;

import java.util.ArrayList;
import java.util.List;

public class Entregador {

    private String nome;
    private String regiao;
    private List<Encomenda> encomendas;

    public Entregador(String nome, String regiao) {
        this.nome = nome;
        this.regiao = regiao;
        this.encomendas = new ArrayList<>();
    }
    // MÉTODOS
    public void receberEncomenda(Encomenda encomenda) {
        encomendas.add(encomenda);
    }
    // GETTERS
    public String getNome() { return nome; }
    public String getRegiao() { return regiao; }
    public List<Encomenda> getEncomendas() { return encomendas; }

    @Override
    public String toString() {
        return "Entregador: " + nome + " | Região: " + regiao + " | Encomendas: " + encomendas.size();
        }
}
