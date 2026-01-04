public class CarroLigeiro extends Veiculo {
    private int numPortas;

    public CarroLigeiro(String matricula, String dono, String marca, String modelo, int ano, int quilometros, int numPortas) {
        super(matricula, dono, marca, modelo, ano, quilometros); // Chama o construtor do Veiculo
        setNumPortas(numPortas); // Usa o setter para validar logo na criação
    }

    // Getter
    public int getNumPortas() {
        return numPortas;
    }

    // Setter com Validação Real (Requisito 151)
    public void setNumPortas(int numPortas) {
        // Validação: Um carro ligeiro tem normalmente entre 2 a 5 portas
        if (numPortas >= 2 && numPortas <= 5) {
            this.numPortas = numPortas;
        } else {
            System.out.println("Erro: Número de portas inválido para um carro ligeiro!");
            this.numPortas = 5; // Valor padrão em caso de erro
        }
    }
}