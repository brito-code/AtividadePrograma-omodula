package pm_prova2_m;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Menu e operações de gerenciamento de veículos e aluguéis.
 */
public class Sistema {

    private final List<Veiculo> veiculos = new ArrayList<Veiculo>();
    private final List<Aluguel> alugueis = new ArrayList<Aluguel>();
    private final Scanner scanner = new Scanner(System.in);

    public void cadastrarVeiculo(Veiculo veiculo) {
        if (veiculo == null) {
            throw new IllegalArgumentException("O veículo é obrigatório.");
        }
        for (Veiculo existente : veiculos) {
            if (existente.getPlaca().equalsIgnoreCase(veiculo.getPlaca())) {
                throw new IllegalArgumentException("Já existe um veículo com a placa " + veiculo.getPlaca() + ".");
            }
        }
        veiculos.add(veiculo);
    }

    public void cadastrarAluguel(Aluguel aluguel) {
        if (aluguel == null) {
            throw new IllegalArgumentException("O aluguel é obrigatório.");
        }
        alugueis.add(aluguel);
    }

    public void executar() {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Opção: ");
            switch (opcao) {
                case 1:
                    associarVeiculo();
                    break;
                case 2:
                    exibirTodosVeiculos();
                    break;
                case 3:
                    exibirTodosAlugueis();
                    break;
                case 4:
                    exibirVeiculosDisponiveis();
                    break;
                case 5:
                    avaliar();
                    break;
                case 6:
                    realizarInspecao();
                    break;
                case 7:
                    removerVeiculosEmManutencao();
                    break;
                case 0:
                    System.out.println("Sistema encerrado.");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
    }

    private void exibirMenu() {
        System.out.println();
        System.out.println("===== Sistema de Aluguel de Veículos =====");
        System.out.println("1 - Associar veículos a um aluguel");
        System.out.println("2 - Exibir todos os veículos cadastrados");
        System.out.println("3 - Exibir todos os aluguéis cadastrados");
        System.out.println("4 - Exibir apenas veículos disponíveis");
        System.out.println("5 - Avaliar");
        System.out.println("6 - Realizar inspeção");
        System.out.println("7 - Remover veículos em manutenção");
        System.out.println("0 - Sair");
    }

    private void associarVeiculo() {
        System.out.println();
        System.out.println("=== Associar veículo a um aluguel ===");
        if (alugueis.isEmpty()) {
            System.out.println("Não há aluguéis cadastrados.");
            return;
        }

        List<Veiculo> disponiveis = filtrarPorStatus(StatusVeiculo.DISPONIVEL);
        if (disponiveis.isEmpty()) {
            System.out.println("Não há veículos disponíveis para associação.");
            return;
        }

        System.out.println("Aluguéis:");
        for (int i = 0; i < alugueis.size(); i++) {
            Aluguel aluguel = alugueis.get(i);
            System.out.println((i + 1) + " - " + aluguel.getTipo()
                    + " | cliente: " + aluguel.getNomeCliente()
                    + " | " + aluguel.getStatus());
        }
        int indiceAluguel = lerIndice(alugueis.size(), "Número do aluguel: ");
        if (indiceAluguel < 0) {
            return;
        }

        Aluguel aluguel = alugueis.get(indiceAluguel);
        boolean continuar = true;
        while (continuar) {
            disponiveis = filtrarPorStatus(StatusVeiculo.DISPONIVEL);
            if (disponiveis.isEmpty()) {
                System.out.println("Não há mais veículos disponíveis para associação.");
                break;
            }

            System.out.println("Veículos disponíveis:");
            for (int i = 0; i < disponiveis.size(); i++) {
                Veiculo veiculo = disponiveis.get(i);
                System.out.println((i + 1) + " - " + veiculo.getTipo()
                        + " | " + veiculo.getPlaca()
                        + " | " + veiculo.getModelo());
            }
            int indiceVeiculo = lerIndice(disponiveis.size(), "Número do veículo: ");
            if (indiceVeiculo < 0) {
                return;
            }

            Veiculo veiculo = disponiveis.get(indiceVeiculo);
            if (estaAssociado(veiculo)) {
                System.out.println("Este veículo já está associado a um aluguel.");
            } else {
                aluguel.adicionarVeiculo(veiculo);
                veiculo.setStatus(StatusVeiculo.ALUGADO);
                aluguel.recalcularValorTotal();
                System.out.println("Veículo " + veiculo.getPlaca() + " associado ao aluguel de " + aluguel.getNomeCliente() + ".");
                System.out.printf("Valor total atualizado: R$ %.2f%n", aluguel.getValorTotal());
            }

            System.out.print("Associar outro veículo a este aluguel? (s/n): ");
            String resposta = scanner.nextLine().trim();
            continuar = resposta.equalsIgnoreCase("s");
        }
    }

    private void exibirTodosVeiculos() {
        System.out.println();
        System.out.println("=== Veículos cadastrados ===");
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }
        for (Veiculo veiculo : veiculos) {
            veiculo.exibirDetalhes();
        }
    }

    private void exibirTodosAlugueis() {
        System.out.println();
        System.out.println("=== Aluguéis cadastrados ===");
        if (alugueis.isEmpty()) {
            System.out.println("Nenhum aluguel cadastrado.");
            return;
        }
        for (Aluguel aluguel : alugueis) {
            aluguel.exibirDetalhes();
        }
    }

    private void exibirVeiculosDisponiveis() {
        System.out.println();
        System.out.println("=== Veículos disponíveis ===");
        List<Veiculo> disponiveis = filtrarPorStatus(StatusVeiculo.DISPONIVEL);
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhum veículo disponível.");
            return;
        }
        for (Veiculo veiculo : disponiveis) {
            veiculo.exibirDetalhes();
        }
    }

    private void avaliar() {
        System.out.println();
        System.out.println("=== Avaliar ===");
        List<Avaliavel> avaliaveis = listarAvaliaveis();
        if (avaliaveis.isEmpty()) {
            System.out.println("Não há itens que possam ser avaliados.");
            return;
        }

        System.out.println("Somente carros, vans e aluguéis executivos podem ser avaliados.");
        for (int i = 0; i < avaliaveis.size(); i++) {
            System.out.println((i + 1) + " - " + descreverAvaliavel(avaliaveis.get(i)));
        }

        int indice = lerIndice(avaliaveis.size(), "Número do item: ");
        if (indice < 0) {
            return;
        }

        int nota = lerInteiro("Nota (0 a 10): ");
        try {
            avaliaveis.get(indice).avaliar(nota);
            System.out.println("Avaliação registrada.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void realizarInspecao() {
        System.out.println();
        System.out.println("=== Realizar inspeção ===");
        List<Veiculo> inspecionaveis = listarInspecionaveis();
        if (inspecionaveis.isEmpty()) {
            System.out.println("Não há veículos que possam ser inspecionados.");
            return;
        }

        System.out.println("Somente motos e vans podem ser inspecionadas.");
        for (int i = 0; i < inspecionaveis.size(); i++) {
            Veiculo veiculo = inspecionaveis.get(i);
            System.out.println((i + 1) + " - " + veiculo.getTipo()
                    + " | " + veiculo.getPlaca()
                    + " | " + veiculo.getModelo()
                    + " | " + veiculo.getStatus());
        }

        int indice = lerIndice(inspecionaveis.size(), "Número do veículo: ");
        if (indice < 0) {
            return;
        }

        Veiculo veiculo = inspecionaveis.get(indice);
        ((Inspecionavel) veiculo).realizarInspecao();
    }

    private void removerVeiculosEmManutencao() {
        System.out.println();
        System.out.println("=== Remover veículos em manutenção ===");
        List<Veiculo> remover = filtrarPorStatus(StatusVeiculo.MANUTENCAO);
        if (remover.isEmpty()) {
            System.out.println("Não há veículos em manutenção.");
            return;
        }

        for (Veiculo veiculo : remover) {
            for (Aluguel aluguel : alugueis) {
                if (aluguel.removerVeiculo(veiculo)) {
                    aluguel.recalcularValorTotal();
                }
            }
            veiculos.remove(veiculo);
            System.out.println("Removido: " + veiculo.getTipo() + " | " + veiculo.getPlaca() + " | " + veiculo.getModelo());
        }
        System.out.println(remover.size() + " veículo(s) removido(s).");
    }

    private List<Veiculo> filtrarPorStatus(StatusVeiculo status) {
        List<Veiculo> filtrados = new ArrayList<Veiculo>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getStatus() == status) {
                filtrados.add(veiculo);
            }
        }
        return filtrados;
    }

    private boolean estaAssociado(Veiculo veiculo) {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getVeiculos().contains(veiculo)) {
                return true;
            }
        }
        return false;
    }

    private List<Avaliavel> listarAvaliaveis() {
        List<Avaliavel> avaliaveis = new ArrayList<Avaliavel>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) veiculo);
            }
        }
        for (Aluguel aluguel : alugueis) {
            if (aluguel instanceof Avaliavel) {
                avaliaveis.add((Avaliavel) aluguel);
            }
        }
        return avaliaveis;
    }

    private List<Veiculo> listarInspecionaveis() {
        List<Veiculo> inspecionaveis = new ArrayList<Veiculo>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo instanceof Inspecionavel) {
                inspecionaveis.add(veiculo);
            }
        }
        return inspecionaveis;
    }

    private String descreverAvaliavel(Avaliavel avaliavel) {
        if (avaliavel instanceof Veiculo) {
            Veiculo veiculo = (Veiculo) avaliavel;
            return veiculo.getTipo() + " | " + veiculo.getPlaca() + " | " + veiculo.getModelo();
        }
        if (avaliavel instanceof Aluguel) {
            Aluguel aluguel = (Aluguel) avaliavel;
            return aluguel.getTipo() + " | cliente: " + aluguel.getNomeCliente();
        }
        return avaliavel.toString();
    }

    private int lerIndice(int tamanho, String prompt) {
        int numero = lerInteiro(prompt);
        if (numero < 1 || numero > tamanho) {
            System.out.println("Número fora da lista.");
            return -1;
        }
        return numero - 1;
    }

    private int lerInteiro(String prompt) {
        while (true) {
            System.out.print(prompt);
            String linha = scanner.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Informe um número inteiro válido.");
            }
        }
    }
}
