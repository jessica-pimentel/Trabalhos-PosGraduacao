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
public class ContaCorrente extends Conta{
    private double limite;
    private double taxaJurosLimite;

    // Construtor
    public ContaCorrente(double limite, double taxaJuros, long id, Cliente cliente, double saldo ) throws Exception{
        super(id, cliente, saldo);
        this.limite = limite;
        this.taxaJurosLimite = taxaJuros;
        this.cliente.setContaCorrente(this);
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getTaxaJurosLimite() {
        return taxaJurosLimite;
    }

    public void setTaxaJurosLimite(double taxaJuros) {
        this.taxaJurosLimite = taxaJuros;
    }

    @Override
    public void aplicaJuros() {
        if (saldo < 0) {
            saldo += saldo * taxaJurosLimite;  
        }
    }

    
    @Override
    public void saca(double valor) throws Exception{
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque não pode ser negativo ou zero. Valor=" + valor);
        }
        if (saldo + limite < valor) {
                    throw new Exception("Saldo insuficiente na conta.\n" +
                            "Valor saque=" + valor + "\n" +
                            "Saldo=" + this.saldo + "\n" +
                            "Limite=" + this.limite);
        }
        saldo -= valor;
    }

    @Override
    public int getNumero() {
        return (int) getId(); 
    }
}
