import java.util.ArrayList;
import java.util.List;

public class Carro {

    private String matricula;
    private int quilometros;
    private String dono;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    private List<ServicoRealizado> historico; // Certifica-te que o nome coincide com a tua classe de serviço

    // Removida a String historico dos parâmetros, pois agora usamos a Lista
    public Carro(String matricula, String dono, String marca, String modelo, int ano, String combustivel, int quilometros) {
        this.matricula = matricula;
        this.dono = dono;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel = combustivel;
        this.quilometros = quilometros;
        this.historico = new ArrayList<>();
    }

    // Getters e Setters (Mantêm-se como tinhas)
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public int getQuilometros() { return quilometros; }
    public void setQuilometros(int quilometros) { this.quilometros = quilometros; }
    public String getDono() { return dono; }
    public void setDono(String dono) { this.dono = dono; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }
    public String getCombustivel() { return combustivel; }
    public void setCombustivel(String combustivel) { this.combustivel = combustivel; }

    // Métodos para o Histórico
    public List<ServicoRealizado> getHistorico() {
        return historico;
    }

    public void adicionarServico(ServicoRealizado servico) {
        this.historico.add(servico);
    }

    // Método útil para a Faturação: calcula o total de gastos do carro
    public double calcularTotalGasto() {
        double total = 0;
        for (ServicoRealizado s : historico) {
            total += s.getPreco();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "matrícula='" + matricula + '\'' +
                ", dono='" + dono + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", totalGasto=" + calcularTotalGasto() + "€" +
                '}';
    }
}