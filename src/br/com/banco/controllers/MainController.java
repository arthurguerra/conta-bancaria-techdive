package br.com.banco.controllers;

import br.com.banco.contas.Conta;

public class MainController {

    public static Conta criaConta(int opcaoTipoDeConta) {
        return Conta.criaConta(opcaoTipoDeConta);
    }
}
