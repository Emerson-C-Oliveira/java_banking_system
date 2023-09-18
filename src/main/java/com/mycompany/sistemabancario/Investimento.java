/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemabancario;

/**
 *
 * @author emers
 */
public class Investimento {
    // Taxas de juros anuais
    public static final double TAXA_CDB = 0.10; // 10% ao ano
    public static final double TAXA_LCI = 0.08; // 8% ao ano

    public static double calcularRendimentoMensal(double valorInvestido, double taxaAnual) {
        double taxaMensal = taxaAnual / 12.0; // Taxa mensal
        return valorInvestido * taxaMensal;
    }

    public static double calcularRendimentoAnual(double valorInvestido, double taxaAnual) {
        return calcularRendimentoMensal(valorInvestido, taxaAnual) * 12.0; // 12 meses
    }
}

