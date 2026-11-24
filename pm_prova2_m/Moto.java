package pm_prova2_m;

/**
 * Moto: possui cilindrada, não recebe avaliação e pode ser inspecionada.
 */
public class Moto extends Veiculo implements Inspecionavel {

    private int cilindrada;
    private final RegistroInspecao inspecao = new RegistroInspecao();

    public Moto(String placa, String modelo, int anoFabricacao, double valorDiaria, double quilometragem,
            StatusVeiculo status, int cilindrada) {
        super(placa, modelo, anoFabricacao, valorDiaria, quilometragem, status);
        setCilindrada(cilindrada);
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        if (cilindrada <= 0) {
            throw new IllegalArgumentException("A cilindrada deve ser maior que zero.");
        }
        this.cilindrada = cilindrada;
    }

    @Override
    public void realizarInspecao() {
        System.out.println(inspecao.realizar(this));
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Cilindrada: " + cilindrada + " cc");
        System.out.println("Inspeção: " + inspecao.getDescricao());
    }

    @Override
    public boolean possuiSeguroEspecial() {
        return true;
    }

    @Override
    public String getTipo() {
        return "Moto";
    }
}
