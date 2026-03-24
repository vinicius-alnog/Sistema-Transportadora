package com.logistica.model;

import com.logistica.exception.TransicaoStatusInvalidaException;

import java.util.List;
import java.util.ArrayList;

public class Encomenda {

    // ATRIBUTOS
    private static int contador = 0;
    private String codigo;
    private String remetente;
    private String destinatario;
    private double peso;
    private List<EventoRastreio> eventos;

    // CONSTRUTORES
    public Encomenda() { }
    public Encomenda(String remetente, String destinatario, double peso) {
        this.codigo = "ENC-" + (++contador);
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.peso = peso;
        this.eventos = new ArrayList<>();
    }

    // MÉTODOS
    public StatusEncomenda getStatusAtual() { // RETORNA O STATUS ATUAL DA ENCOMENDA
        return eventos.get(eventos.size() - 1).getStatus();
    }
    public void adicionarEvento(EventoRastreio evento) { // ADICIONAR EVENTO AO RASTREIO
        if (eventos.isEmpty()) {
            if (evento.getStatus() != StatusEncomenda.COLETADO) {
                throw new TransicaoStatusInvalidaException("O primeiro evento deve ser COLETADO.");
            }
        } else {
            StatusEncomenda atual = getStatusAtual();
            if (!atual.podeTransitarPara(evento.getStatus())) {
                throw new TransicaoStatusInvalidaException(
                        "Transição de status inválida: " + atual + " para " + evento.getStatus());
            }
        }
        eventos.add(evento);

        int tentativas = 0;
        for (EventoRastreio e : eventos) {
            if (e.getStatus() == StatusEncomenda.TENTATIVA_FALHA) {
                tentativas++;
            }
        }
        if (tentativas >= 2) {
            eventos.add(new EventoRastreio(
                    StatusEncomenda.EM_DEVOLUCAO,
                    "Centro de Distribuição",
                    "Muitas tentativas de entrega falhas"));
        }
    }
    public double calcularFrete(double distanciaKm) {
        return peso * 3.50 + distanciaKm * 0.80;
    }

    // GETTERS
    public static int getContador() { return contador; }
    public String getRemetente() { return remetente; }
    public String getCodigo() { return codigo; }
    public String getDestinatario() { return destinatario; }
    public double getPeso() { return peso; }
    public List<EventoRastreio> getEventos() { return eventos; }

        @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Encomenda: ").append(codigo);
        sb.append("\nDe: ").append(remetente);
        sb.append("\nPara: ").append(destinatario);
        sb.append("\nPeso: ").append(peso).append(" kg");
        sb.append("\nStatus atual: ").append(getStatusAtual());
        sb.append("\n");
        sb.append("\n--- Histórico ---");
        for (EventoRastreio e : eventos) {
            sb.append("\n").append(e);
        }
        return sb.toString();
    }
}