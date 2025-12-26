import java.util.ArrayList;
import java.util.List;

public class Servicos {
    static List<String> servicos = List.of("Troca de óleo", "Troca de filtros", "Troca de pastilhas", "Troca de discos", "Troca de fluido de freio", "Troca de amortecedores", "Alinhamento", "Diagnóstico de motor", "Pequenos reparos", "Troca de bateria", "Alternador", "Scanner automotivo", "Recarga de gás", "Higienização", "Troca de pneus", "Balanceamento");

     // Lista de carros registados
    private static final List<Carro> carros = new ArrayList<>();


    public static void newCarro(){
        Carro carro = new Carro("00-AA-00","cliente","marca","modelo",2025,"gasolina",0,"");
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
    public static void adicionarServicoAoCarro(Carro carro, Servico servico) {
        carro.adicionarServico(servico);
    }

    public static double calcularTotal(Carro carro) {
        if (carro.getHistorico() == null || carro.getHistorico().isEmpty()) {
            return 0;
        }

        double total = 0;

        for (String linha : linhas) {
            if (linha.contains("€")) {
                String valor = linha.substring(linha.indexOf("-") + 1)
                        .replace("€", "")
                        .trim();
                total += Double.parseDouble(valor);
            }
        }
        return total;
    }

    // para mostrar os serviços realizados
    public static void listarServicos(Carro carro) {
        System.out.println("Serviços realizados:");
        System.out.println(carro.getHistorico());
    }

    // faturas
    public static void imprimirFatura(Carro carro) {
        System.out.println("----- FATURA -----");
        System.out.println("Dono: " + carro.getDono());
        System.out.println("Matrícula: " + carro.getMatricula());
        listarServicos(carro);
        System.out.println("Total: " + calcularTotal(carro) + "€");
        System.out.println("------------------");
    }


    public static void atribuirServico(Carro carro, Servico servico) {
        carro.adicionarAoHistorico(servico.toString());
    }


    private static final List<Servico> listaServicosDetalhada = new ArrayList<>();

    static {
    listaServicosDetalhada.add(new Servico("Troca de óleo", 40));
    listaServicosDetalhada.add(new Servico("Troca de filtros", 25));
    listaServicosDetalhada.add(new Servico("Troca de pastilhas", 80));
    listaServicosDetalhada.add(new Servico("Troca de discos", 150));
    listaServicosDetalhada.add(new Servico("Troca de fluido de freio", 30));
    listaServicosDetalhada.add(new Servico("Troca de amortecedores", 200));
    listaServicosDetalhada.add(new Servico("Alinhamento", 35));
    listaServicosDetalhada.add(new Servico("Diagnóstico de motor", 25));
    listaServicosDetalhada.add(new Servico("Pequenos reparos", 20));
    listaServicosDetalhada.add(new Servico("Troca de bateria", 90));
    listaServicosDetalhada.add(new Servico("Alternador", 180));
    listaServicosDetalhada.add(new Servico("Scanner automotivo", 30));
    listaServicosDetalhada.add(new Servico("Recarga de gás", 60));
    listaServicosDetalhada.add(new Servico("Higienização", 25));
    listaServicosDetalhada.add(new Servico("Troca de pneus", 50));
    listaServicosDetalhada.add(new Servico("Balanceamento", 20));
    }
}


