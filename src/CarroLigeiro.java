public class CarroLigeiro extends Veiculo {
    private int numPortas;

    public CarroLigeiro(String matricula, String dono, String marca, String modelo, int ano, int quilometros, int numPortas) {
        super(matricula, dono, marca, modelo, ano, quilometros);
        this.numPortas = numPortas;
    }

    public int getNumPortas() {
        return numPortas;
    }
}