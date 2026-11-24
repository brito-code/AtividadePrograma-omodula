/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pm_prova2_m;

/**
 *
 * @author glbra
 */
public abstract class Veiculo {

    private String placa;
    private String modelo;
    private int anoFabricacao;
    private double valorDiaria;
    private double quilometragem;
    private StatusVeiculo status;

    public Veiculo(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem, StatusVeiculo status) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória.");
        }
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo é obrigatório.");
        }
        if (anoFabricacao <= 0) {
            throw new IllegalArgumentException("O ano de fabricação é inválido.");
        }
        if (valorDiaria < 0) {
            throw new IllegalArgumentException("O valor da diária não pode ser negativo.");
        }
        if (quilometragem < 0) {
            throw new IllegalArgumentException("A quilometragem não pode ser negativa.");
        }
        if (status == null) {
            throw new IllegalArgumentException("O status do veículo é obrigatório.");
        }

        this.placa = placa.trim();
        this.modelo = modelo.trim();
        this.anoFabricacao = anoFabricacao;
        this.valorDiaria = valorDiaria;
        this.quilometragem = quilometragem;
        this.status = status;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("A placa é obrigatória.");
        }
        this.placa = placa.trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("O modelo é obrigatório.");
        }
        this.modelo = modelo.trim();
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        if (anoFabricacao <= 0) {
            throw new IllegalArgumentException("O ano de fabricação é inválido.");
        }
        this.anoFabricacao = anoFabricacao;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        if (valorDiaria < 0) {
            throw new IllegalArgumentException("O valor da diária não pode ser negativo.");
        }
        this.valorDiaria = valorDiaria;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(double quilometragem) {
        if (quilometragem < 0) {
            throw new IllegalArgumentException("A quilometragem não pode ser negativa.");
        }
        this.quilometragem = quilometragem;
    }

    public StatusVeiculo getStatus() {
        return status;
    }

    public void setStatus(StatusVeiculo status) {
        if (status == null) {
            throw new IllegalArgumentException("O status do veículo é obrigatório.");
        }
        this.status = status;
    }

    public abstract void exibirDetalhes();

    public abstract boolean possuiSeguroEspecial();

    public abstract String getTipo();

    protected void exibirDadosComuns() {
        System.out.println("--------------------------------");
        System.out.println("Tipo: " + getTipo());
        System.out.println("Placa: " + placa);
        System.out.println("Modelo: " + modelo);
        System.out.println("Ano de fabricação: " + anoFabricacao);
        System.out.printf("Valor da diária: R$ %.2f%n", valorDiaria);
        System.out.printf("Quilometragem: %.0f km%n", quilometragem);
        System.out.println("Status: " + status);
        System.out.println("Seguro especial: " + (possuiSeguroEspecial() ? "sim" : "não"));
    }
}
