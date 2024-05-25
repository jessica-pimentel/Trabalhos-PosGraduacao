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
public abstract class Conta {
    public long id;
    public Cliente cliente;
    public double saldo;

    public Conta(long id, Cliente cliente, double saldoInicial) {
        this.id = id;
        this.cliente = cliente;
        this.saldo = saldoInicial;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void deposita(double valor) throws Exception{
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do depósito não pode ser negativo ou zero. Valor=" + valor);
        }
        this.saldo += valor;
    }

    public void saca(double valor) throws Exception{
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor do saque não pode ser negativo ou zero. Valor=" + valor);
        }
        if (this.saldo < valor) {
            throw new IllegalArgumentException("Saldo insuficiente para o saque.");
        }
        this.saldo -= valor;
    }

    public abstract void aplicaJuros();

    public abstract int getNumero();
}
