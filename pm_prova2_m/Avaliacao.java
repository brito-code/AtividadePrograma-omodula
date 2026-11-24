package pm_prova2_m;

/**
 * Guarda as notas recebidas por um objeto avaliável.
 */
public class Avaliacao {

    private int somaNotas;
    private int quantidade;
    private int ultimaNota;

    public void registrar(int nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("A nota deve estar entre 0 e 10.");
        }
        somaNotas += nota;
        quantidade++;
        ultimaNota = nota;
    }

    public boolean possuiAvaliacao() {
        return quantidade > 0;
    }

    public double getMedia() {
        if (quantidade == 0) {
            return 0;
        }
        return (double) somaNotas / quantidade;
    }

    public String getDescricao() {
        if (quantidade == 0) {
            return "sem avaliação";
        }
        if (quantidade == 1) {
            return String.format("última nota %d | média %.1f (1 avaliação)", ultimaNota, getMedia());
        }
        return String.format("última nota %d | média %.1f (%d avaliações)", ultimaNota, getMedia(), quantidade);
    }
}
