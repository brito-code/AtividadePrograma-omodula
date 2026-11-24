/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pm_prova2_m;

/**
 *
 * @author glbra
 */
public class PM_Prova2_M {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        criarDadosIniciais(sistema);
        sistema.executar();
    }

    /**
     * Cria 3 veículos e 2 aluguéis assim que o programa é executado.
     */
    public static void criarDadosIniciais(Sistema sistema) {
        sistema.cadastrarVeiculo(new Carro(
                "ABC1D23", "Chevrolet Onix", 2023, 150.0, 18500, StatusVeiculo.DISPONIVEL, 4));
        sistema.cadastrarVeiculo(new Moto(
                "MNO4P56", "Honda CG 160", 2018, 70.0, 92000, StatusVeiculo.DISPONIVEL, 160));
        sistema.cadastrarVeiculo(new Van(
                "QRS7T89", "Mercedes Sprinter", 2020, 350.0, 64000, StatusVeiculo.MANUTENCAO, 15));

        sistema.cadastrarAluguel(new Basico(
                "Ana Souza", 5, 0, StatusAluguel.ATIVO));
        sistema.cadastrarAluguel(new Executivo(
                "Carlos Lima", 3, 0, StatusAluguel.ATIVO, "Roberto Alves"));

        System.out.println("Dados iniciais carregados: 3 veículos e 2 aluguéis.");
    }
}
