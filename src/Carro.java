public class Carro {

    private String matricula;
    private String historico; // TODO: transformar em lista
    private int quilometros;
    private String dono;
    private String marca;
    private String modelo;
    private int ano;
    private String combustivel;
    
    public Carro(String matricula, String dono, String marca, String modelo, int ano, String combustivel, int quilometros, String historico){
        this.matricula=matricula;
        this.dono = dono;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.combustivel = combustivel;
        this.quilometros = quilometros;
        this.historico = historico;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getQuilometros() {
        return quilometros;
    }

    public void setQuilometros(int quilometros) {
        this.quilometros = quilometros;
    }

    public String getDono() {
        return dono;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public String getHistorico() {
        return historico;
    }

    @Override //altera o comportamento quando faço print do objeto
    public String toString() {
        return "Carro{" +
                "matrícula='" + matricula + '\'' +
                ", dono='" + dono + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", combustível='" + combustivel + '\'' +
                ", quilómetros=" + quilometros +
                '}';
    }
    public void setHistorico(String historico) {
        this.historico = historico;
    }

}


