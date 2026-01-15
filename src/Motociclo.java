public class Motociclo extends Veiculo {
    private int cilindrada;

    public Motociclo(String matricula, String dono, String marca, String modelo, int ano, int quilometros, int cilindrada) {
        super(matricula, dono, marca, modelo, ano, quilometros);
        setCilindrada(cilindrada); // Usa o setter para validar logo na criação
    }

    // Getter
    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }
}