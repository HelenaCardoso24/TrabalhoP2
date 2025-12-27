import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Criar carro");
            System.out.println("2 - Listar carros");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> Servicos.newCarro();
                case 2 -> Servicos.listarCarros().forEach(System.out::println);
            }
        } while (opcao != 0);

        sc.close();
    }
}
