package com.logistica.service;

import com.logistica.exception.EntregadorNaoEncontradoException;
import com.logistica.model.Encomenda;
import com.logistica.model.Entregador;
import com.logistica.model.EventoRastreio;
import com.logistica.model.StatusEncomenda;

import java.util.Scanner;

public class SistemaLogistica {

    private Transportadora transportadora;
    private Scanner sc;

    public SistemaLogistica(Scanner sc) {
        this.transportadora = new Transportadora();
        this.sc = sc;
    }

    public void exibirMenu() {
        System.out.println("=== SISTEMA DE LOGÍSTICA ===");
        System.out.println("1 - Cadastrar entregador");
        System.out.println("2 - Cadastrar encomenda");
        System.out.println("3 - Adicionar evento de rastreio");
        System.out.println("4 - Atribuir entregador a encomenda");
        System.out.println("5 - Calcular frete");
        System.out.println("6 - Buscar encomenda por código");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    public void voltarMenu() {
        System.out.println();
        System.out.println("Pressione Enter para voltar ao menu");
        sc.nextLine();
    }
    public void cadastrarEntregador() {
        System.out.print("Digite o nome do entregador: ");
        String nome = sc.nextLine();
        System.out.print("Digite a região de atuação: ");
        String regiao = sc.nextLine();

        Entregador entregador = new Entregador(nome, regiao);
        transportadora.registrarEntregador(entregador);
        System.out.println();
        System.out.println("Entregador cadastrado com sucesso!");
        System.out.println(entregador);
        voltarMenu();
    }
    public void cadastrarEncomenda() {
        System.out.print("Digite o remetente: ");
        String remetente = sc.nextLine();
        System.out.print("Digite o destinatário: ");
        String destinatario = sc.nextLine();
        System.out.print("Digite o peso da encomenda (kg): ");
        double peso = sc.nextDouble();
        sc.nextLine();

        Encomenda encomenda = new Encomenda(remetente, destinatario, peso);
        transportadora.registrarEncomenda(encomenda);
        System.out.println();
        System.out.println("Encomenda cadastrada com sucesso!");
        System.out.println(encomenda);
        voltarMenu();
    }
    public void adicionarEventoRastreio() {
        System.out.println("Código da encomenda (apenas os números inseridos em seguida do 'ENC-': ");
        String numero = sc.nextLine();
        String codigo = "ENC-" + numero;

        try {
            Encomenda encomenda = transportadora.buscarEncomendaPorCodigo(codigo);
            System.out.println("Status atual: " + encomenda.getStatusAtual());

            StatusEncomenda proximo = encomenda.proximoStatus();
            if (proximo == null) {
                System.out.println("Encomenda já está no status final: " + encomenda.getStatusAtual());
                return;
            }

            System.out.print("Localização: ");
            String localizacao = sc.nextLine();
            System.out.print("Observação: ");
            String observacao = sc.nextLine();

            EventoRastreio evento = new EventoRastreio(proximo, localizacao, observacao);
            encomenda.adicionarEvento(evento);

            System.out.println("Status atualizado para: " + proximo);
            System.out.println(encomenda);
        } catch (EntregadorNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        voltarMenu();

    }
    public void menuAtribuirEntrega() {
        System.out.println("Número da encomenda (apenas os números inseridos em seguida do 'ENC-': ");
        String numeroEncomenda = sc.nextLine();
        String codigoEncomenda = "ENC-" + numeroEncomenda;

        System.out.println("Nome do entregador que deseja atribuir a encomenda: ");
        String nomeEntregador = sc.nextLine();

        try {
            transportadora.atribuirEntregador(codigoEncomenda, nomeEntregador);
            System.out.println("Entregador " + nomeEntregador + " atribuído à encomenda " + codigoEncomenda + " com sucesso!");
        } catch (EntregadorNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        voltarMenu();
    }
}
