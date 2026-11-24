package pm_prova2_m;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Registra a inspeção de um veículo e atualiza o status conforme a quilometragem.
 */
public class RegistroInspecao {

    public static final double QUILOMETRAGEM_MAXIMA_APROVADA = 80000;

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private boolean realizada;
    private String resultado = "ainda não realizada";

    public String realizar(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo é obrigatório.");
        }

        realizada = true;
        String data = LocalDate.now().format(FORMATO_DATA);

        if (veiculo.getQuilometragem() >= QUILOMETRAGEM_MAXIMA_APROVADA) {
            veiculo.setStatus(StatusVeiculo.MANUTENCAO);
            resultado = "reprovada em " + data + " (enviada para manutenção)";
        } else {
            if (veiculo.getStatus() == StatusVeiculo.MANUTENCAO) {
                veiculo.setStatus(StatusVeiculo.DISPONIVEL);
            }
            resultado = "aprovada em " + data;
        }

        return "Inspeção " + resultado + ". Status atual: " + veiculo.getStatus() + ".";
    }

    public boolean isRealizada() {
        return realizada;
    }

    public String getDescricao() {
        return resultado;
    }
}
