import java.util.*;
import java.io.*;

public class Servicos {
    private static final Map<String, Double> tabelaPrecos = new HashMap<>();
    private static final List<Veiculo> veiculos = new ArrayList<>();
    private static final String FICHEIRO = "oficina_dados.csv";

    static {
        tabelaPrecos.put("Troca de óleo (60€)", 60.0);
        tabelaPrecos.put("Troca de filtros (45€)", 45.0);
        tabelaPrecos.put("Troca de pastilhas (80€)", 80.0);
        tabelaPrecos.put("Alinhamento (35€)", 35.0);
        tabelaPrecos.put("Revisão Geral (150€)", 150.0);
    }

    public static void registarVeiculo(Veiculo v) {
        veiculos.add(v);
    }

    // sobrecarga 1: Method padrão que usa a tabela de preços
    public static void realizarServico(Veiculo v, String nomeServico) {
        if (tabelaPrecos.containsKey(nomeServico)) {
            realizarServico(v, nomeServico, tabelaPrecos.get(nomeServico));
        }
    }

    // sobrecarga 2: Permite inserir um preço manual
    public static void realizarServico(Veiculo v, String nomeServico, double preco) {
        ServicoRealizado novo = new ServicoRealizado(nomeServico, preco);
        v.adicionarServico(novo);
    }

    public static List<Veiculo> listarVeiculos() { return veiculos; }

    public static List<String> listarNomesServicos() { return new ArrayList<>(tabelaPrecos.keySet()); }

    public static boolean removerVeiculo(String matricula) {
        return veiculos.removeIf(v -> v.getMatricula().equalsIgnoreCase(matricula));
    }

    public static void guardarDados() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FICHEIRO))) {
            for (Veiculo v : veiculos) {
                String tipo = (v instanceof CarroLigeiro) ? "CARRO" : "MOTA";
                int extra = (v instanceof CarroLigeiro) ? ((CarroLigeiro)v).getNumPortas() : ((Motociclo)v).getCilindrada();

                pw.println(tipo + ";" + v.getMatricula() + ";" + v.getDono() + ";" +
                        v.getMarca() + ";" + v.getModelo() + ";" + v.getAno() + ";" +
                        v.getQuilometros() + ";" + extra);
            }
        } catch (IOException e) {
            System.err.println("Erro ao guardar: " + e.getMessage());
        }
    }

    public static void carregarDados() {
        File f = new File(FICHEIRO);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] d = linha.split(";");
                if (d[0].equals("CARRO")) {
                    registarVeiculo(new CarroLigeiro(d[1], d[2], d[3], d[4], Integer.parseInt(d[5]), Integer.parseInt(d[6]), Integer.parseInt(d[7])));
                } else {
                    registarVeiculo(new Motociclo(d[1], d[2], d[3], d[4], Integer.parseInt(d[5]), Integer.parseInt(d[6]), Integer.parseInt(d[7])));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar: " + e.getMessage());
        }
    }
}