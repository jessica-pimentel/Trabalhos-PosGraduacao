/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bancorrw.conta;

import bancorrw.cliente.Cliente;

/**
 *
 * @author rafae
 */
public class ContaInvestimento extends Conta{
    private double taxaRemuneracaoInvestimento;
    private double montanteMinimo;
    private double depositoMinimo;

    // Construtor
    public ContaInvestimento(double taxaRemuneracaoInvestimento, double montanteMinimo, double depositoMinimo, double saldoInicial, long id, Cliente cliente) throws Exception{
        super(id, cliente, saldoInicial);
        this.taxaRemuneracaoInvestimento = taxaRemuneracaoInvestimento;
        this.montanteMinimo = montanteMinimo;
        this.depositoMinimo = depositoMinimo;
        if (saldoInicial < montanteMinimo) {
            throw new Exception("Saldo não pode ser menor que montante mínimo.");
        }
        cliente.addContaInvestimento(this);
    }

    public double getTaxaRemuneracaoInvestimento() {
        return taxaRemuneracaoInvestimento;
    }
    
    public void setTaxaRemuneracaoInvestimento(double taxaRemuneracaoInvestimento) {
        this.taxaRemuneracaoInvestimento = taxaRemuneracaoInvestimento;
    }

    public double getMontanteMinimo() {
        return montanteMinimo;
    }

    public void setMontanteMinimo(double montanteMinimo) {
        this.montanteMinimo = montanteMinimo;
    }

    public double getDepositoMinimo() {
        return depositoMinimo;
    }

    public void setDepositoMinimo(double depositoMinimo) {
        this.depositoMinimo = depositoMinimo;
    }

    @Override
    public void deposita(double valor) throws Exception {
        if (valor < depositoMinimo) {
            throw new Exception("Valor do depóstio não atingiu o mínimo. Valor Depósito=" + valor + " Depóstio Mínimo=" + depositoMinimo);
        }
        super.saldo += valor;
    }

    @Override
    public void saca(double valor) throws Exception {
        if (valor <= 0) {
        throw new IllegalArgumentException("Valor do saque não pode ser negativo ou zero. Valor=" + valor);
        }
        if (this.getSaldo() - valor < montanteMinimo) {
            throw new Exception("Saldo insuficiente para saque. Valor Saque=" + valor + " Saldo=" + this.getSaldo() + " Montante Minimo=" + montanteMinimo);
        }
        super.saldo -= valor;
    }

    @Override
    public void aplicaJuros() {
        super.saldo += super.saldo * taxaRemuneracaoInvestimento;
    }

    @Override
    public int getNumero() {
        return (int) id; 
    }
}
