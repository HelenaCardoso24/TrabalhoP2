import java.util.ArrayList;
import java.util.List;

public class Servicos {
    static List<String> servicos = List.of("Troca de óleo", "Troca de filtros", "Troca de pastilhas", "Troca de discos", "Troca de fluido de freio", "Troca de amortecedores", "Alinhamento", "Diagnóstico de motor", "Pequenos reparos", "Troca de bateria", "Alternador", "Scanner automotivo", "Recarga de gás", "Higienização", "Troca de pneus", "Balanceamento");

     // Lista de carros registados
    private static final List<Carro> carros = new ArrayList<>();


    public static void newCarro(){
        Carro carro = new Carro("","","","",0,"",0,"");
        carros.add(carro);
    }

    // Devolve a lista de serviços disponíveis
    public static List<String> listarServicos() {
        return servicos;
    }

    // Devolve a lista de carros registados
    public static List<Carro> listarCarros() {
        return carros;
    }

    // Adiciona um serviço ao histórico do carro
    public static void adicionarServicoAoCarro(Carro carro, String servico) {
        if (servicos.contains(servico)) {
            String historicoAtual = carro.getHistorico();
            historicoAtual += "- " + servico + "\n";
            // adicionar depois de configurar o historico :carro.setQuilometros(carro.getQuilometros()); // mantém os km atuais
        } else {
            System.out.println("Serviço não encontrado: " + servico);
        }
    }
}


