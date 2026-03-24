package com.logistica.model;

public enum StatusEncomenda {
    COLETADO,
    EM_TRANSITO,
    SAIU_PARA_ENTREGA,
    TENTATIVA_FALHA,
    ENTREGUE,
    EM_DEVOLUCAO;

    public boolean podeTransitarPara(StatusEncomenda proximo) {
        switch (this) {
            case COLETADO:
                return proximo == EM_TRANSITO;
            case EM_TRANSITO:
                return proximo == SAIU_PARA_ENTREGA;
            case SAIU_PARA_ENTREGA:
                return proximo == ENTREGUE || proximo == TENTATIVA_FALHA;
            case TENTATIVA_FALHA:
                return proximo == SAIU_PARA_ENTREGA || proximo == EM_DEVOLUCAO;
            default:
                return false;
        }
    }
}
