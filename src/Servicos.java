import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servicos {
    // Mapa de serviços disponíveis: Chave (Nome) -> Valor (Preço)
    private static final Map<String, Double> tabelaPrecos = new HashMap<>();

    // Bloco estático para inicializar os preços (Faturação)
    static {
        tabelaPrecos.put("Troca de óleo", 60.0);
        tabelaPrecos.put("Troca de filtros", 45.0);
        tabelaPrecos.put("Troca de pastilhas", 80.0);
        tabelaPrecos.put("Troca de discos", 150.0);
        tabelaPrecos.put("Alinhamento", 35.0);
        tabelaPrecos.put("Diagnóstico de motor", 50.0);
        tabelaPrecos.put("Troca de bateria", 120.0);
        tabelaPrecos.put("Recarga de gás AC", 70.0);
        tabelaPrecos.put("Troca de pneus", 100.0);
        tabelaPrecos.put("Balanceamento", 20.0);
    }

    private static final List<Carro> carros = new ArrayList<>();

    // Retorna os nomes dos serviços para preencher menus na Interface Gráfica
    public static List<String> listarNomesServicos() {
        return new ArrayList<>(tabelaPrecos.keySet());
    }

    public static List<Carro> listarCarros() {
        return carros;
    }

    public static void registarCarro(Carro carro) {
        carros.add(carro);
    }

    /**
     * Adiciona um serviço ao histórico do carro com base na tabela de preços.
     */
    public static void realizarServico(Carro carro, String nomeServico) {
        if (tabelaPrecos.containsKey(nomeServico)) {
            double preco = tabelaPrecos.get(nomeServico);
            ServicoRealizado novoServico = new ServicoRealizado(nomeServico, preco);
            carro.adicionarServico(novoServico);
            System.out.println("Serviço '" + nomeServico + "' adicionado com sucesso ao carro " + carro.getMatricula());
        } else {
            System.out.println("Erro: Serviço '" + nomeServico + "' não existe na tabela de preços.");
        }
    }
}