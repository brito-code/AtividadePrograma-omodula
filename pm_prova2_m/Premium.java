package pm_prova2_m;

/**
 * Aluguel premium: quilometragem ilimitada e seguro incluso.
 */
public class Premium extends Aluguel {

    public Premium(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quilometragem: ilimitada");
        exibirVeiculosAssociados();
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return true;
    }

    @Override
    public boolean possuiQuilometragemIlimitada() {
        return true;
    }

    @Override
    public String getTipo() {
        return "Premium";
    }
}
