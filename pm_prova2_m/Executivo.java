package pm_prova2_m;

/**
 * Aluguel executivo: quilometragem ilimitada, motorista particular e avaliação.
 */
public class Executivo extends Aluguel implements Avaliavel {

    public static final double DIARIA_MOTORISTA = 200.0;

    private String nomeMotorista;
    private final Avaliacao avaliacao = new Avaliacao();

    public Executivo(String nomeCliente, int quantidadeDias, double valorTotal, StatusAluguel status, String nomeMotorista) {
        super(nomeCliente, quantidadeDias, valorTotal, status);
        setNomeMotorista(nomeMotorista);
    }

    public String getNomeMotorista() {
        return nomeMotorista;
    }

    public void setNomeMotorista(String nomeMotorista) {
        if (nomeMotorista == null || nomeMotorista.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do motorista é obrigatório.");
        }
        this.nomeMotorista = nomeMotorista.trim();
    }

    @Override
    public void avaliar(int nota) {
        avaliacao.registrar(nota);
    }

    @Override
    public void recalcularValorTotal() {
        super.recalcularValorTotal();
        if (!getVeiculos().isEmpty()) {
            setValorTotal(getValorTotal() + (DIARIA_MOTORISTA * getQuantidadeDias()));
        }
    }

    @Override
    public void exibirDetalhes() {
        exibirDadosComuns();
        System.out.println("Quilometragem: ilimitada");
        System.out.println("Motorista particular: " + nomeMotorista);
        System.out.println("Avaliação: " + avaliacao.getDescricao());
        exibirVeiculosAssociados();
    }

    @Override
    public boolean possuiSeguroIncluso() {
        return false;
    }

    @Override
    public boolean possuiQuilometragemIlimitada() {
        return true;
    }

    @Override
    public String getTipo() {
        return "Executivo";
    }
}
