import javax.swing.SwingUtilities;

public class App {

    final static boolean DEBUG = false; // nao bastaria meter false em vez de apagar (ivo)

    public static void main(String[] args) {
        // Tenta carregar os dados guardados em "oficina_dados.csv"
        Servicos.carregarDados();

        // Se a lista estiver vazia (primeira vez que corre), cria dados de exemplo
        if (Servicos.listarVeiculos().isEmpty() && DEBUG) {
            System.out.println("Nenhum dado encontrado. Criando veículos de teste...");

            // Exemplo de criar um Carro e um Motociclo
            CarroLigeiro c1 = new CarroLigeiro("AA-11-BB", "Helena Cardoso", "VW", "Golf", 2022, 15000, 5);
            Motociclo m1 = new Motociclo("99-ZZ-11", "Ricardo Jorge", "Yamaha", "MT-07", 2021, 5000, 689);

            Servicos.registarVeiculo(c1);
            Servicos.registarVeiculo(m1);

            // Adicionar serviços iniciais usando a Sobrecarga de Métodos
            Servicos.realizarServico(c1, "Troca de óleo");
            Servicos.realizarServico(m1, "Revisão Geral", 120.0); // Exemplo de preço manual

        }


        // LANÇAR A INTERFACE GRÁFICA (Swing)
        SwingUtilities.invokeLater(() -> {
            try {
                // Define o aspeto visual do sistema operativo (opcional, mas fica mais bonito)
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            JanelaPrincipal gui = new JanelaPrincipal();
            gui.setVisible(true);
        });
    }
}