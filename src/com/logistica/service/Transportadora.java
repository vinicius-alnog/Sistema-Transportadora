package com.logistica.service;

import com.logistica.exception.EntregadorNaoEncontradoException;
import com.logistica.model.Encomenda;
import com.logistica.model.Entregador;

import java.util.ArrayList;
import java.util.List;

public class Transportadora {

    private List<Encomenda> encomendas;
    private List<Entregador> entregadores;

    public Transportadora() {
        this.encomendas = new ArrayList<>();
        this.entregadores = new ArrayList<>();
    }

    // MÉTODOS
    public void registrarEncomenda(Encomenda e) {
        encomendas.add(e);
    }
    public void registrarEntregador(Entregador e) {
        entregadores.add(e);
    }
    public Encomenda buscarEncomendaPorCodigo(String codigo) {
        for (Encomenda e : encomendas) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        throw new EntregadorNaoEncontradoException("Encomenda não encontrada: " + codigo);
    }
    public Entregador buscarEntregadorPorNome(String nome) {
        for (Entregador e : entregadores) {
            if (e.getNome().equalsIgnoreCase(nome)) {
                return e;
            }
        }
        throw new EntregadorNaoEncontradoException("Entregador não encontrado: " + nome);
    }
    public void atribuirEntregador(String codigoEncomenda, String nomeEntregador) {
        Encomenda encomenda = null;
        for (Encomenda e : encomendas) {
            if (e.getCodigo().equals(codigoEncomenda)) {
                encomenda = e;
                break;
            }
        }
        if (encomenda == null) {
            throw new EntregadorNaoEncontradoException("Encomenda não encontrada: " + codigoEncomenda);
        }

        for (Entregador entregador : entregadores) {
            if (entregador.getNome().equals(nomeEntregador)) {
                entregador.receberEncomenda(encomenda);
                return;
            }
        }
        throw new EntregadorNaoEncontradoException("Entregador não encontrado: " + nomeEntregador);
    }
}
