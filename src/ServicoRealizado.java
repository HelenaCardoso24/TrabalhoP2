import java.time.LocalDate;

public class ServicoRealizado {
    private String nomeDoServico;
    private LocalDate data;
    private double preco;

    public ServicoRealizado(String nomeDoServico, double preco) {
        this.nomeDoServico = nomeDoServico;
        this.data = LocalDate.now(); // Regista a data atual automaticamente
        this.preco = preco;
    }

    // getters
    public String getNomeDoServico() { return nomeDoServico; }
    public LocalDate getData() { return data; }
    public double getPreco() { return preco; }

    @Override
    public String toString() {
        // este é o method que o Java não estava a encontrar
        return String.format("%s (%s) - %.2f€", nomeDoServico, data, preco);
    }
}