package com.logistica.service;

import com.logistica.exception.EntregadorNaoEncontradoException;
import com.logistica.model.*;

import java.util.Scanner;

public class SistemaLogistica {

    private Transportadora transportadora;
    private Scanner sc;

    public SistemaLogistica(Scanner sc) {
        this.transportadora = new Transportadora();
        this.sc = sc;
    }

    public void menuExibir() {
        System.out.println("=== SISTEMA DE LOGÍSTICA ===");
        System.out.println("1 - Cadastrar entregador");
        System.out.println("2 - Cadastrar encomenda");
        System.out.println("3 - Adicionar evento de rastreio");
        System.out.println("4 - Atribuir entregador a encomenda");
        System.out.println("5 - Calcular frete");
        System.out.println("6 - Buscar encomenda por código");
        System.out.println("7 - Exibir encomendas de um entregador");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    public void menuVoltar() {
        System.out.println();
        System.out.println("Pressione Enter para voltar ao menu");
        sc.nextLine();
    }
    public void menuCadastrarEntregador() {
        System.out.print("Digite o nome do entregador: ");
        String nome = sc.nextLine();
        System.out.print("Digite a região de atuação: ");
        String regiao = sc.nextLine();

        Entregador entregador = new Entregador(nome, regiao);
        transportadora.registrarEntregador(entregador);
        System.out.println();
        System.out.println("Entregador cadastrado com sucesso!");
        System.out.println(entregador);
        menuVoltar();
    }
    public void menuCadastrarEncomenda() {
        System.out.print("Digite o remetente: ");
        String remetente = sc.nextLine();
        System.out.print("Digite o destinatário: ");
        String destinatario = sc.nextLine();
        System.out.println("Digite o endereço do destinatário: ");
        String enderecoDestinatario = sc.nextLine();
        System.out.println("Digite a cidade do destinatário: ");
        String cidadeDestinatario = sc.nextLine();
        System.out.println("Digite o CEP do destinatário: ");
        String cepDestinatario = sc.nextLine();
        System.out.print("Digite o peso da encomenda (kg): ");
        double peso = sc.nextDouble();
        sc.nextLine();

        Endereco endereco = new Endereco(enderecoDestinatario, cidadeDestinatario, cepDestinatario);
        Encomenda encomenda = new Encomenda(remetente, destinatario, peso, endereco);
        transportadora.registrarEncomenda(encomenda);
        System.out.println();
        System.out.println("Encomenda cadastrada com sucesso!");
        System.out.println(encomenda);
        menuVoltar();
    }
    public void menuAdicionarEventoRastreio() {
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

        menuVoltar();

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

        menuVoltar();
    }
    public void menuCalcularFrete() {
        System.out.print("Número da encomenda: ");
        String numero = sc.nextLine();
        String codigo = "ENC-" + numero;

        try {
            System.out.print("Distância em km: ");
            double distancia = sc.nextDouble();
            sc.nextLine();

            Encomenda encomenda = transportadora.buscarEncomendaPorCodigo(codigo);
            System.out.println("Frete: R$ " + encomenda.calcularFrete(distancia));
        } catch (EntregadorNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: É necessário digitar a distância em números para calcular o frete");
        }

        menuVoltar();
    }
    public void menuBuscarEncomenda() {
        System.out.print("Número da encomenda: ");
        String numero = sc.nextLine();
        String codigo = "ENC-" + numero;

        try {
            Encomenda encomenda = transportadora.buscarEncomendaPorCodigo(codigo);
            System.out.println(encomenda);
        } catch (EntregadorNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        menuVoltar();
    }
    public void menuExibirEncomendasEntregador() {
        System.out.print("Nome do entregador: ");
        String nomeEntregador = sc.nextLine();

        try {
            Entregador entregador = transportadora.buscarEntregadorPorNome(nomeEntregador);
            System.out.println(entregador);
            System.out.println("--- Encomendas ---");
            if (entregador.getEncomendas().isEmpty()) {
                System.out.println("Nenhuma encomenda atribuída a este entregador.");
            } else {
                for (Encomenda e : entregador.getEncomendas()) {
                    System.out.println(e);
                    System.out.println("------------------");
                }
            }
        } catch (EntregadorNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        menuVoltar();
    }
}
