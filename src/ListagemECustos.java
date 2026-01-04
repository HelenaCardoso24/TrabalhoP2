public class ListagemECustos {

    // Agora aceita qualquer Veiculo (Carro ou Mota)
    public void listarServicosPorCarro(Veiculo v) {
        System.out.println("--- Histórico de Serviços: " + v.getMatricula() + " ---");

        // Agora o histórico é uma List<ServicoRealizado>, precisamos de a percorrer
        if (v.getHistorico() == null || v.getHistorico().isEmpty()) {
            System.out.println("Nenhum serviço registado.");
        } else {
            for (ServicoRealizado s : v.getHistorico()) {
                System.out.println(s.toString()); // Usa o toString() que criámos
            }
        }
    }

    public void gerarFatura(Veiculo v) {
        System.out.println("========== FATURA ==========");
        System.out.println("Cliente: " + v.getDono());
        System.out.println("Veículo: " + v.getMarca() + " " + v.getModelo());
        // Usa o método de cálculo que está na superclasse
        System.out.println(String.format("Total a Pagar: %.2f€", v.calcularTotalGasto()));
        System.out.println("============================");
    }
}