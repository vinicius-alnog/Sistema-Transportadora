package com.logistica.service;

import com.logistica.model.Encomenda;
import com.logistica.model.Entregador;

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

}
