package src;
import java.util.List;

public class Servicos {
    List<String> servicos = List.of("Troca de óleo", "Troca de filtros", "Troca de pastilhas", "Troca de discos", "Troca de fluido de freio", "Troca de amortecedores", "Alinhamento", "Diagnóstico de motor", "Pequenos reparos", "Troca de bateria", "Alternador", "Scanner automotivo", "Recarga de gás", "Higienização", "Troca de pneus", "Balanceamento");


    public static void newCarro(){
        Carro carro = new Carro(null, null, null, null, null, 0, null);
    }
}
