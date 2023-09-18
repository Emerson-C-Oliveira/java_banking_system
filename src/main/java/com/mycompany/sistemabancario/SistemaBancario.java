/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemabancario;

/**
 *
 * @author emers
 */
import java.util.Scanner;
import org.telegram.telegrambots.ApiContextInitializer;

public class SistemaBancario {
    private static ContaBancaria conta;

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            exibirMenu();
            char escolha = scanner.next().charAt(0);

            switch (escolha) {
                case '1':
                    criarConta(scanner);
                    break;
                case '2':
                    realizarTransacao(scanner);
                    break;
                case '3':
                    realizarInvestimento(scanner);
                    break;
                case '4':
                    System.out.println("Saindo do sistema bancário.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public static void exibirMenu() {
        System.out.println("==== Menu ====");
        System.out.println("1) Criar conta");
        System.out.println("2) Transações comuns");
        System.out.println("3) Investimentos");
        System.out.println("4) Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void criarConta(Scanner scanner) {
        System.out.print("Digite o nome: ");
        String nome = scanner.next();
        System.out.print("Digite o sobrenome: ");
        String sobrenome = scanner.next();
        System.out.print("Digite o CPF: ");
        String cpf = scanner.next();
        conta = new ContaBancaria(nome, sobrenome, cpf);
        System.out.println("Conta criada com sucesso!");
    }

    public static void realizarTransacao(Scanner scanner) {
        if (conta == null) {
            System.out.println("Crie uma conta antes de realizar transações.");
            return;
        }

        System.out.println("==== Transações comuns ====");
        System.out.println("1) Verificar saldo");
        System.out.println("2) Depositar valor");
        System.out.println("3) Retirar valor");
        System.out.println("4) Voltar");
        System.out.print("Escolha uma opção: ");
        char escolha = scanner.next().charAt(0);

        switch (escolha) {
            case 'a':
                System.out.println("Saldo: " + conta.getSaldo());
                break;
            case 'b':
                System.out.print("Digite o valor a depositar: ");
                double valorDeposito = scanner.nextDouble();
                conta.depositar(valorDeposito);
                System.out.println("Depósito realizado com sucesso!");
                break;
            case 'c':
                System.out.print("Digite o valor a sacar: ");
                double valorSaque = scanner.nextDouble();
                if (conta.sacar(valorSaque)) {
                    System.out.println("Saque realizado com sucesso!");
                } else {
                    System.out.println("Saldo insuficiente para saque.");
                }
                break;
            case 'x':
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public static void realizarInvestimento(Scanner scanner) {
        if (conta == null) {
            System.out.println("Crie uma conta antes de realizar investimentos.");
            return;
        }

        System.out.println("==== Investimentos ====");
        System.out.println("1) CDB (10% ao ano)");
        System.out.println("2) LCI (8% ao ano)");
        System.out.println("3) Voltar");
        System.out.print("Escolha uma opção: ");
        char escolha = scanner.next().charAt(0);

        switch (escolha) {
            case '1':
                investirCDB(scanner);
                break;
            case '2':
                investirLCI(scanner);
                break;
            case '3':
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    public static void investirCDB(Scanner scanner) {
        System.out.print("Digite o valor a ser investido em CDB: ");
        double valorInvestido = scanner.nextDouble();
        System.out.print("Digite a quantidade de parcelas (meses): ");
        int quantidadeParcelas = scanner.nextInt();

        // Calcular o rendimento mensal em CDB
        double rendimentoMensalCDB = calcularRendimentoMensal(valorInvestido, 0.10 / 12);

        // Calcular o valor total após o período de investimento
        double valorTotalCDB = calcularValorTotal(valorInvestido, rendimentoMensalCDB, quantidadeParcelas);

        // Calcular o rendimento total
        double rendimentoTotalCDB = calcularRendimentoTotal(valorInvestido, valorTotalCDB);

        // Adicionar o valor total ao saldo da conta
        conta.depositar(valorTotalCDB);

        System.out.println("Investimento em CDB realizado com sucesso!");
        System.out.println("Rendimento do CDB no período informado: " + rendimentoTotalCDB);
        System.out.println("Quantidade de parcelas informada pelo usuário: " + quantidadeParcelas);
    }

    public static void investirLCI(Scanner scanner) {
        System.out.print("Digite o valor a ser investido em LCI: ");
        double valorInvestido = scanner.nextDouble();
        System.out.print("Digite a quantidade de parcelas (meses): ");
        int quantidadeParcelas = scanner.nextInt();

        // Calcular o rendimento mensal em LCI
        double rendimentoMensalLCI = calcularRendimentoMensal(valorInvestido, 0.08 / 12);

        // Calcular o valor total após o período de investimento
        double valorTotalLCI = calcularValorTotal(valorInvestido, rendimentoMensalLCI, quantidadeParcelas);

        // Calcular o rendimento total
        double rendimentoTotalLCI = calcularRendimentoTotal(valorInvestido, valorTotalLCI);

        // Adicionar o valor total ao saldo da conta
        conta.depositar(valorTotalLCI);

        System.out.println("Investimento em LCI realizado com sucesso!");
        System.out.println("Rendimento do LCI no período informado: " + rendimentoTotalLCI);
        System.out.println("Quantidade de parcelas informada pelo usuário: " + quantidadeParcelas);
    }

    public static double calcularRendimentoMensal(double valorInvestido, double taxaMensal) {
        return valorInvestido * taxaMensal;
    }

    public static double calcularValorTotal(double valorInvestido, double rendimentoMensal, int quantidadeParcelas) {
        return valorInvestido + (rendimentoMensal * quantidadeParcelas);
    }

    public static double calcularRendimentoTotal(double valorInvestido, double valorTotal) {
        return valorTotal - valorInvestido;
    }
}


