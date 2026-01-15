import java.util.ArrayList;
import java.util.List;

public abstract class Veiculo { // abstract porque não existe um "veiculo" genérico, ou é carro ou é mota
    private String matricula;
    private String dono;
    private String marca;
    private String modelo;
    private int ano;
    private int quilometros;
    private List<ServicoRealizado> historico;

    public Veiculo(String matricula, String dono, String marca, String modelo, int ano, int quilometros) {
        this.matricula = matricula;
        this.dono = dono;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometros = quilometros;
        this.historico = new ArrayList<>();
    }

    public void adicionarServico(ServicoRealizado s) { this.historico.add(s); }

    public double calcularTotalGasto() {
        return historico.stream().mapToDouble(ServicoRealizado::getPreco).sum();
    }

    // Getters e Setters
    public String getMatricula() { return matricula; }
    public String getDono() { return dono; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public int getAno() { return ano; }
    public int getQuilometros() { return quilometros; }
    public List<ServicoRealizado> getHistorico() { return historico; }

    @Override
    public String toString() {
        return String.format("%s %s (%s)", marca, modelo, matricula);
    }
}