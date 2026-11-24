package pm_prova2_m;

/**
 * Aluguel básico: até 100 km por dia e sem seguro incluso.
 */
public class Basico extends Aluguel {

    public static final int LIMITE_KM_POR_DIA = 100;

    public Basico(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quilometragem: até " + LIMITE_KM_POR_DIA + " km por dia");
        exibirVeiculosAssociados();
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public boolean possuiQuilometragemIlimitada() {
        return false;
    }

    @Override
    public String getTipo() {
        return "Básico";
    }
}
