package pm_prova2_m;

/**
 * Van: possui capacidade de passageiros, pode ser avaliada e pode ser inspecionada.
 */
public class Van extends Veiculo implements Avaliavel, Inspecionavel {

    private int capacidadePassageiros;
    private final Avaliacao avaliacao = new Avaliacao();
    private final RegistroInspecao inspecao = new RegistroInspecao();

    public Van(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem,
            StatusVeiculo status, int capacidadePassageiros) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        setCapacidadePassageiros(capacidadePassageiros);
    }

    public int getCapacidadePassageiros() {
        return capacidadePassageiros;
    }

    public void setCapacidadePassageiros(int capacidadePassageiros) {
        if (capacidadePassageiros <= 0) {
            throw new IllegalArgumentException("A capacidade de passageiros deve ser maior que zero.");
        }
        this.capacidadePassageiros = capacidadePassageiros;
    }

    @Override
    public void avaliar(int nota) {
        avaliacao.registrar(nota);
    }

    @Override
    public void realizarInspecao() {
        System.out.println(inspecao.realizar(this));
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Capacidade de passageiros: " + capacidadePassageiros);
        System.out.println("Avaliação: " + avaliacao.getDescricao());
        System.out.println("Inspeção: " + inspecao.getDescricao());
    }

    @Override
    public boolean possuiSeguroEspecial() {
        return true;
    }

    @Override
    public String getTipo() {
        return "Van";
    }
}
