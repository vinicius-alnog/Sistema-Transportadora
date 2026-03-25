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
            sistema.exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1: sistema.cadastrarEntregador(); break; // CADASTRO DE ENTREGADOR
                case 2: sistema.cadastrarEncomenda(); break; // CADASTRO DE ENCOMENDA
                case 3: sistema.adicionarEventoRastreio(); break; // ADICIONAR EVENTO DE RASTREIO
                case 4: sistema.menuAtribuirEntrega(); break; // ATRIBUIR ENTREGADOR A ENCOMENDA
                case 5:
                    System.out.println("Funcionalidade de calcular frete (a implementar)");
                    break;
                case 6:
                    System.out.println("Funcionalidade de buscar encomenda por código (a implementar)");
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        sc.close();
    }
}
