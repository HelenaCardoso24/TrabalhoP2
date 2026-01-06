public class Motociclo extends Veiculo {
    private int cilindrada;

    public Motociclo(String matricula, String dono, String marca, String modelo, int ano, int quilometros, int cilindrada) {
        super(matricula, dono, marca, modelo, ano, quilometros);
        setCilindrada(cilindrada); // Usa o setter para validar logo na criação [cite: 176]
    }

    // Getter
    public int getCilindrada() {
        return cilindrada;
    }

    // Setter com Validação Real (Requisito 151) [cite: 151]
    public void setCilindrada(int cilindrada) {
        // Validação: A cilindrada de um motociclo deve ser positiva (ex: 50cc a 2500cc)
        if (cilindrada > 0 && cilindrada <= 2500) {
            this.cilindrada = cilindrada;
        } else {
            System.out.println("Erro: Cilindrada inválida!");
            this.cilindrada = 50; // Define um valor mínimo por defeito em caso de erro
        }
    }
}