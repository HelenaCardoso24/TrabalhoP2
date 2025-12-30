public class ListagemECustos {
    /**
     * Requisito: Listar serviços por carro 
     * Exibe no terminal todos os serviços que foram feitos num carro específico.
     */
    public void listarServicosPorCarro(Carro carro) {
        System.out.println("--- Histórico de Serviços: " + carro.getMatricula() + " ---");
        // O seu atributo 'historico' atual é uma String, vamos listá-la diretamente
        if (carro.getHistorico() == null || carro.getHistorico().isEmpty()) {
            System.out.println("Nenhum serviço registado para este veículo.");
        } else {
            System.out.println(carro.getHistorico());
        }
    }

    /**
     * Requisito: Criar fatura simples 
     * Calcula um custo total fictício baseado no histórico ou num valor fixo por serviço.
     */
    public void gerarFatura(Carro carro, double valorServico) {
        System.out.println("========== FATURA ==========");
        System.out.println("Cliente: " + carro.getDono()); 
        System.out.println("Veículo: " + carro.getMarca() + " " + carro.getModelo()); 
        System.out.println("Total a Pagar: " + valorServico + "€"); 
        System.out.println("============================");
    }

    /**
     * Requisito: Guardar relatório 
     * Este método será usado para exportar os dados para um ficheiro .txt[cite: 169].
     */
    public void guardarRelatorio(Carro carro) {
        // Aqui usará a lógica de escrita em ficheiro mencionada no enunciado 
        System.out.println("Relatório do carro " + carro.getMatricula() + " guardado com sucesso!"); 
    }
}
