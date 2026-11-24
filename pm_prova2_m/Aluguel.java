/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pm_prova2_m;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author glbra
 */
public abstract class Aluguel {

    private String nomeCliente;
    private int quantidadeDias;
    private double valorTotal;
    private StatusAluguel status;
    private final List<Veiculo> veiculos = new ArrayList<Veiculo>();

    public Aluguel(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        if (quantidadeDias <= 0) {
            throw new IllegalArgumentException("A quantidade de dias deve ser maior que zero.");
        }
        if (valorTotal < 0) {
            throw new IllegalArgumentException("O valor total não pode ser negativo.");
        }
        if (status == null) {
            throw new IllegalArgumentException("O status do aluguel é obrigatório.");
        }

        this.nomeCliente = nomeCliente.trim();
        this.quantidadeDias = quantidadeDias;
        this.valorTotal = valorTotal;
        this.status = status;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        if (nomeCliente == null || nomeCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatório.");
        }
        this.nomeCliente = nomeCliente.trim();
    }

    public int getQuantidadeDias() {
        return quantidadeDias;
    }

    public void setQuantidadeDias(int quantidadeDias) {
        if (quantidadeDias <= 0) {
            throw new IllegalArgumentException("A quantidade de dias deve ser maior que zero.");
        }
        this.quantidadeDias = quantidadeDias;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        if (valorTotal < 0) {
            throw new IllegalArgumentException("O valor total não pode ser negativo.");
        }
        this.valorTotal = valorTotal;
    }

    public StatusAluguel getStatus() {
        return status;
    }

    public void setStatus(StatusAluguel status) {
        if (status == null) {
            throw new IllegalArgumentException("O status do aluguel é obrigatório.");
        }
        this.status = status;
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo é obrigatório.");
        }
        if (veiculos.contains(veiculo)) {
            return false;
        }
        veiculos.add(veiculo);
        return true;
    }

    public boolean removerVeiculo(Veiculo veiculo) {
        return veiculos.remove(veiculo);
    }

    public List<Veiculo> getVeiculos() {
        return Collections.unmodifiableList(veiculos);
    }

    public void recalcularValorTotal() {
        double total = 0;
        for (Veiculo veiculo : veiculos) {
            total += veiculo.getValorDiaria() * quantidadeDias;
        }
        this.valorTotal = total;
    }

    public abstract void exibirDetalhes();

    public abstract boolean possuiSeguroIncluso();

    public abstract boolean possuiQuilometragemIlimitada();

    public abstract String getTipo();

    protected void exibirDadosComuns() {
        System.out.println("--------------------------------");
        System.out.println("Tipo: " + getTipo());
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("Quantidade de dias: " + quantidadeDias);
        System.out.printf("Valor total: R$ %.2f%n", valorTotal);
        System.out.println("Status: " + status);
        System.out.println("Seguro incluso: " + (possuiSeguroIncluso() ? "sim" : "não"));
    }

    protected void exibirVeiculosAssociados() {
        System.out.println("Veículos associados:");
        if (veiculos.isEmpty()) {
            System.out.println("  Nenhum veículo associado.");
            return;
        }
        for (Veiculo veiculo : veiculos) {
            System.out.println("  - " + veiculo.getTipo() + " | " + veiculo.getPlaca() + " | " + veiculo.getModelo());
        }
    }
}
