package com.logistica;

import com.logistica.exception.TransicaoStatusInvalidaException;
import com.logistica.model.Encomenda;
import com.logistica.model.Endereco;
import com.logistica.model.EventoRastreio;
import com.logistica.model.StatusEncomenda;
import com.logistica.service.SistemaLogistica;

import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args)  {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SistemaLogistica sistema = new SistemaLogistica(sc);

        int opcao = -1;

        while (opcao != 0) {
            sistema.menuExibir();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: sistema.menuCadastrarEntregador(); break; // CADASTRO DE ENTREGADOR
                case 2: sistema.menuCadastrarEncomenda(); break; // CADASTRO DE ENCOMENDA
                case 3: sistema.menuAdicionarEventoRastreio(); break; // ADICIONAR EVENTO DE RASTREIO
                case 4: sistema.menuAtribuirEntrega(); break; // ATRIBUIR ENTREGADOR A ENCOMENDA
                case 5: sistema.menuCalcularFrete(); break; // CALCULAR FRETE
                case 6: sistema.menuBuscarEncomenda(); break; // BUSCAR ENCOMENDA POR CÓDIGO
                case 7: sistema.menuExibirEncomendasEntregador(); break; // EXIBIR ENCOMENDAS DE UM ENTREGADOR
                case 0: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }
}
