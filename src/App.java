import java.util.Scanner;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Criar um carro de teste
        // Nota: O construtor agora não recebe a String de histórico, pois criamos a lista internamente
        Carro meuCarro = new Carro("AA-00-BB", "João Silva", "Toyota", "Corolla", 2020, "Híbrido", 50000);

        // Registar o carro no sistema
        Servicos.registarCarro(meuCarro);

        System.out.println("--- Sistema de Gestão de Oficina ---");
        System.out.println("Carro registado: " + meuCarro.getMarca() + " " + meuCarro.getModelo());

        // 2. Simular a adição de serviços (Faturação)
        System.out.println("\nAdicionando serviços...");
        Servicos.realizarServico(meuCarro, "Troca de óleo");
        Servicos.realizarServico(meuCarro, "Alinhamento");
        Servicos.realizarServico(meuCarro, "Troca de filtros");

        // 3. Gerar Relatório de Estado e Fatura
        System.out.println("\n========================================");
        System.out.println("      RELATÓRIO DE ESTADO DO VEÍCULO    ");
        System.out.println("========================================");
        System.out.println("Proprietário: " + meuCarro.getDono());
        System.out.println("Matrícula:    " + meuCarro.getMatricula());
        System.out.println("Quilómetros:  " + meuCarro.getQuilometros() + " km");
        System.out.println("----------------------------------------");
        System.out.println("Histórico de Trabalhos:");

        List<ServicoRealizado> historico = meuCarro.getHistorico();
        if (historico.isEmpty()) {
            System.out.println("Nenhum serviço registado.");
        } else {
            for (ServicoRealizado s : historico) {
                System.out.println("- " + s.toString());
            }
        }

        System.out.println("----------------------------------------");
        System.out.printf("TOTAL A PAGAR (Fatura): %.2f€\n", meuCarro.calcularTotalGasto());
        System.out.println("========================================");

        scanner.close();
    }
}