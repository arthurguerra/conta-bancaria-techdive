package br.com.banco.app;

import br.com.banco.contas.Conta;
import br.com.banco.utils.Menus;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("BEM VINDO AO BANCO TECH DIVE!");
        iniciar();

    }

    public static void iniciar() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            Menus.exibeMenuInicial();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch(Exception e) {
                e.getMessage();
            }
            Menus.escolheOpcao(opcao);
        } while (opcao != 9);
        System.out.println("Obrigado por utilizar o Banco TechDive, até a próxima!");
    }

}
