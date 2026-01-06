import java.time.LocalDate;

public class ServicoRealizado {
    private String nomeDoServico;
    private LocalDate data;
    private double preco;

    public ServicoRealizado(String nomeDoServico, double preco) {
        this.nomeDoServico = nomeDoServico;
        this.data = LocalDate.now(); // Regista a data atual automaticamente [cite: 2]
        this.preco = preco;
    }

    // Getters
    public String getNomeDoServico() { return nomeDoServico; }
    public LocalDate getData() { return data; }
    public double getPreco() { return preco; }

    @Override
    public String toString() {
        // Este é o método que o Java não estava a encontrar [cite: 2]
        return String.format("%s (%s) - %.2f€", nomeDoServico, data, preco);
    }
}