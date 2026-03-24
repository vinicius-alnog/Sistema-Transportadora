package com.logistica.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventoRastreio {

    private StatusEncomenda status;
    private String localizacao;
    private String observacao;
    private LocalDateTime dataHora;

    public EventoRastreio() {}

    public EventoRastreio(StatusEncomenda status, String localizacao, String observacao) {
        this.status = status;
        this.localizacao = localizacao;
        this.observacao = observacao;
        this.dataHora = LocalDateTime.now();
    }
    public EventoRastreio(StatusEncomenda status, String localizacao, String observacao, LocalDateTime dataHora) {
        this.status = status;
        this.localizacao = localizacao;
        this.observacao = observacao;
        this.dataHora = dataHora;
    }

    public StatusEncomenda getStatus() { return status; }
    public String getLocalizacao() { return localizacao; }
    public String getObservacao() { return observacao; }
    public LocalDateTime getDataHora() { return dataHora; }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return "[" + dataHora.format(dtf) + "] " + status + " - " + localizacao + " | " + observacao;
    }
}