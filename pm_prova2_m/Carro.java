package pm_prova2_m;

/**
 * Carro de passeio: possui quantidade de portas, pode ser avaliado e não exige inspeção.
 */
public class Carro extends Veiculo implements Avaliavel {

    private int quantidadePortas;
    private final Avaliacao avaliacao = new Avaliacao();

    public Carro(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem,
            StatusVeiculo status, int quantidadePortas) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        setQuantidadePortas(quantidadePortas);
    }

    public int getQuantidadePortas() {
        return quantidadePortas;
    }

    public void setQuantidadePortas(int quantidadePortas) {
        if (quantidadePortas <= 0) {
            throw new IllegalArgumentException("A quantidade de portas deve ser maior que zero.");
        }
        this.quantidadePortas = quantidadePortas;
    }

    @Override
    public void avaliar(int nota) {
        avaliacao.registrar(nota);
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quantidade de portas: " + quantidadePortas);
        System.out.println("Avaliação: " + avaliacao.getDescricao());
    }

    @Override
    public boolean possuiSeguroEspecial() {
        return false;
    }

    @Override
    public String getTipo() {
        return "Carro";
    }
}
