import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Calendar;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class JanelaPrincipal extends JFrame {
    private JTable tabelaVeiculos;
    private DefaultTableModel modeloTabela;

    public JanelaPrincipal() {
        setTitle("Oficina Mecânica - Gestão de Veículos");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Color rosaFundo = new Color(255, 182, 193); // Rosa claro
        Color rosaForte = new Color(255, 105, 180); // Hot Pink

        // painel superior
        JPanel painelTitulo = new JPanel();
        painelTitulo.setBackground(rosaForte);
        JLabel lblTitulo = new JLabel("Sistema de Faturação e Relatórios", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        painelTitulo.add(lblTitulo);
        add(painelTitulo, BorderLayout.NORTH);

        // tabela onde tem todos os veículos
        String[] colunas = {"Tipo", "Matrícula", "Dono", "Marca/Modelo", "Info Extra", "Total Gasto"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaVeiculos = new JTable(modeloTabela);
        tabelaVeiculos.setBackground(new Color(255, 240, 245)); // Rosa "Lavender Blush" para as linhas
        tabelaVeiculos.setSelectionBackground(rosaForte); // Cor quando selecionas uma linha
        tabelaVeiculos.getTableHeader().setBackground(rosaForte); // Cabeçalho rosa
        tabelaVeiculos.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(tabelaVeiculos);
        scrollPane.getViewport().setBackground(rosaFundo); // Fundo da área vazia da tabela
        add(scrollPane, BorderLayout.CENTER);

        // painel lateral
        JPanel painelBotoes = new JPanel(new GridLayout(7, 1, 10, 10));


        painelBotoes.setBackground(rosaFundo);
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // inicialização dos botoes
        JButton btnNovo = new JButton("Novo Veículo");
        JButton btnAddServico = new JButton("Registar Serviço");
        JButton btnRelatorio = new JButton("Fatura / Relatório");
        JButton btnPesquisa = new JButton("Pesquisa");
        JButton btnRemover = new JButton("Remover Veículo");
        JButton btnSair = new JButton("Sair");

        // estilo dos botões
        JButton[] todosBotoes = {btnNovo, btnAddServico, btnRelatorio,btnPesquisa, btnRemover, btnSair};
        for (JButton btn : todosBotoes) {
            btn.setBackground(Color.WHITE);      // Fundo do botão branco
            btn.setForeground(rosaForte);       // Texto em rosa forte
            btn.setFocusPainted(false);          // Retira aquele quadrado de seleção feio
            btn.setFont(new Font("Arial", Font.BOLD, 13));
            btn.setBorder(BorderFactory.createLineBorder(rosaForte, 2)); // Borda rosa
        }

        // ações
        btnNovo.addActionListener(e -> abrirFormularioNovoVeiculo());
        btnRelatorio.addActionListener(e -> verRelatorio());
        btnAddServico.addActionListener(e -> adicionarServico());
        btnPesquisa.addActionListener(e -> abrirPesquisa());
        btnRemover.addActionListener(e -> removerVeiculo());
        btnSair.addActionListener(e -> {
            Servicos.guardarDados();
            System.exit(0);
        });

        // adiciona ao painel
        painelBotoes.add(btnNovo);
        painelBotoes.add(btnAddServico);
        painelBotoes.add(btnRelatorio);
        painelBotoes.add(btnPesquisa);
        painelBotoes.add(btnRemover);

        // Separador invisível ou rosa
        JSeparator sep = new JSeparator();
        sep.setForeground(rosaFundo);
        painelBotoes.add(sep);

        painelBotoes.add(btnSair);

        add(painelBotoes, BorderLayout.EAST);
        atualizarTabela();
    }

    private void abrirFormularioNovoVeiculo() {
        String[] tipos = {"Carro Ligeiro", "Motociclo"};
        JComboBox<String> comboTipo = new JComboBox<>(tipos);

        JTextField txtMatricula = new JTextField();
        // Formatação automática da matrícula
        txtMatricula.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String val = txtMatricula.getText().replace("-", "").toUpperCase();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < val.length(); i++) {
                    if (i > 0 && i % 2 == 0 && i < 6) sb.append("-");
                    sb.append(val.charAt(i));
                }
                if (sb.length() <= 8) txtMatricula.setText(sb.toString());
            }
        });

        JTextField txtDono = new JTextField();
        txtDono.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String val = txtDono.getText().toLowerCase();
                StringBuilder sb = new StringBuilder();
                boolean capitalize = true;

                for (int i = 0; i < val.length(); i++) {
                    char c = val.charAt(i);
                    if (capitalize && Character.isLetter(c)) {
                        sb.append(Character.toUpperCase(c));
                        capitalize = false;
                    } else {
                        sb.append(c);
                    }
                    if (c == ' ') capitalize = true;
                }

                txtDono.setText(sb.toString());
                txtDono.setCaretPosition(txtDono.getText().length());
            }
        });

        JTextField txtMarca = new JTextField();
        JTextField txtModelo = new JTextField();
        JTextField txtAno = new JTextField();
        JTextField txtKM = new JTextField();
        JTextField txtExtra = new JTextField();
        JLabel lblExtra = new JLabel("Número de Portas:");

        comboTipo.addActionListener(e -> {
            if (comboTipo.getSelectedIndex() == 0) {
                lblExtra.setText("Número de Portas (2–5):");
            } else {
                lblExtra.setText("Cilindrada (cc):");
            }
        });

        Object[] form = {
                "Tipo:", comboTipo,
                "Matrícula (AA-00-AA):", txtMatricula,
                "Dono:", txtDono,
                "Marca:", txtMarca,
                "Modelo:", txtModelo,
                "Ano:", txtAno,
                "KM:", txtKM,
                lblExtra, txtExtra
        };

        int res = JOptionPane.showConfirmDialog(this, form, "Novo Registo", JOptionPane.OK_CANCEL_OPTION);
        if (res == JOptionPane.OK_OPTION) {
            try {
                String matricula = txtMatricula.getText();
                String regex = "^([A-Z0-9]{2}-){2}[A-Z0-9]{2}$";
                Matcher matcher = Pattern.compile(regex).matcher(matricula);

                if (!matcher.matches()) {
                    JOptionPane.showMessageDialog(this, "Dados inválidos!\nEste formato de matrícula não é válido");
                    return;
                }

                int ano_atual = Calendar.getInstance().get(Calendar.YEAR);
                String anoAtual = String.valueOf(ano_atual);

                int ano = Integer.parseInt(txtAno.getText());
                if (ano < 1920 || ano > ano_atual) {
                    JOptionPane.showMessageDialog(this, "Dados inválidos!\nAno tem que estar entre 1920 e " + anoAtual);
                    return;
                }

                int km = Integer.parseInt(txtKM.getText());
                if (km < 0) {
                    JOptionPane.showMessageDialog(this, "Dados de quilometros inválidos!\n");
                    return;
                }

                Veiculo v;

                if (comboTipo.getSelectedIndex() == 0) {
                    int portas = Integer.parseInt(txtExtra.getText());
                    if (portas < 2 || portas > 5) {
                        JOptionPane.showMessageDialog(this, "Número de portas deve estar entre 2 e 5!");
                        return;
                    }

                    v = new CarroLigeiro(matricula, txtDono.getText(), txtMarca.getText(),
                            txtModelo.getText(), ano, km, portas);

                } else {
                    int cilindrada = Integer.parseInt(txtExtra.getText());
                    if (cilindrada < 50 || cilindrada > 2500) {
                        JOptionPane.showMessageDialog(this, "Cilindrada deve estar entre 50 e 2500 cc!");
                        return;
                    }

                    v = new Motociclo(matricula, txtDono.getText(), txtMarca.getText(),
                            txtModelo.getText(), ano, km, cilindrada);
                }


                Servicos.registarVeiculo(v);
                atualizarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Dados inválidos!\n" + ex.getMessage());
            }
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        for (Veiculo v : Servicos.listarVeiculos()) {
            String infoExtra = (v instanceof CarroLigeiro) ?
                    ((CarroLigeiro) v).getNumPortas() + " Portas" :
                    ((Motociclo) v).getCilindrada() + "cc";

            Object[] row = {
                    v.getClass().getSimpleName(),
                    v.getMatricula(),
                    v.getDono(),
                    v.getMarca() + " " + v.getModelo(),
                    infoExtra,
                    String.format("%.2f€", v.calcularTotalGasto())
            };
            modeloTabela.addRow(row);
        }
    }

    private void verRelatorio() {
        int index = tabelaVeiculos.getSelectedRow();
        if (index == -1) return;
        String mat = (String) modeloTabela.getValueAt(index, 1);
        Veiculo v = Servicos.listarVeiculos().stream().filter(x -> x.getMatricula().equals(mat)).findFirst().orElse(null);

        if (v != null) {
            StringBuilder sb = new StringBuilder("=== RELATÓRIO / FATURA ===\n");
            sb.append("Veículo: ").append(v.toString()).append("\n");
            sb.append("Dono: ").append(v.getDono()).append("\n");
            sb.append("---------------------------\n");
            v.getHistorico().forEach(s -> sb.append(s.toString()).append("\n"));
            sb.append("---------------------------\n");
            sb.append("TOTAL A PAGAR: ").append(String.format("%.2f€", v.calcularTotalGasto()));

            JTextArea area = new JTextArea(sb.toString());
            JOptionPane.showMessageDialog(this, new JScrollPane(area), "Fatura", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void adicionarServico() {
        int index = tabelaVeiculos.getSelectedRow();
        if (index == -1) return;
        String mat = (String) modeloTabela.getValueAt(index, 1);
        Veiculo v = Servicos.listarVeiculos().stream().filter(x -> x.getMatricula().equals(mat)).findFirst().orElse(null);

        List<String> opcoes = Servicos.listarNomesServicos();
        String s = (String) JOptionPane.showInputDialog(this, "Serviço:", "Oficina", 0, null, opcoes.toArray(), opcoes.getFirst());

        if (s != null) {
            Servicos.realizarServico(v, s);
            atualizarTabela();
        }
    }

    private void removerVeiculo() {
        int index = tabelaVeiculos.getSelectedRow();
        if (index == -1) return;
        String mat = (String) modeloTabela.getValueAt(index, 1);
        if (Servicos.removerVeiculo(mat)) {
            atualizarTabela();
            JOptionPane.showMessageDialog(this, "Removido!");
        }
    }

    private void abrirPesquisa() {
        String[] opcoes = {"Por Matrícula", "Por Dono", "Por Marca", "Nenhum"};

        String filtro = (String) JOptionPane.showInputDialog(
                this,
                "Escolha o filtro:",
                "Pesquisa",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (filtro == null) return;

        switch (filtro) {
            case "Por Matrícula":
                JTextField txtMatricula = new JTextField();

                // KeyListener para transformar em maiúsculas e adicionar traços automaticamente
                txtMatricula.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String val = txtMatricula.getText().replace("-", "").toUpperCase();
                        if (val.length() > 6) val = val.substring(0, 6); // Limitar a 6 caracteres

                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < val.length(); i++) {
                            if (i == 2 || i == 4) sb.append("-");
                            sb.append(val.charAt(i));
                        }

                        txtMatricula.setText(sb.toString());
                        txtMatricula.setCaretPosition(txtMatricula.getText().length()); // Cursor no final
                    }
                });

                int resMat = JOptionPane.showConfirmDialog(
                        this,
                        txtMatricula,
                        "Matrícula:",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (resMat == JOptionPane.OK_OPTION) {
                    filtrarPorMatricula(txtMatricula.getText());
                }
                break;

            case "Por Dono":
                JTextField txtDono = new JTextField();

                // KeyListener para capitalizar primeira letra de cada palavra
                txtDono.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent e) {
                        String val = txtDono.getText().toLowerCase();
                        StringBuilder sb = new StringBuilder();
                        boolean capitalize = true;

                        for (int i = 0; i < val.length(); i++) {
                            char c = val.charAt(i);
                            if (capitalize && Character.isLetter(c)) {
                                sb.append(Character.toUpperCase(c));
                                capitalize = false;
                            } else {
                                sb.append(c);
                            }
                            if (c == ' ') capitalize = true; // Próxima letra depois de espaço é maiúscula
                        }

                        txtDono.setText(sb.toString());
                        txtDono.setCaretPosition(txtDono.getText().length()); // Cursor no final
                    }
                });

                int resDono = JOptionPane.showConfirmDialog(
                        this,
                        txtDono,
                        "Nome do Dono:",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (resDono == JOptionPane.OK_OPTION) {
                    filtrarPorDono(txtDono.getText());
                }
                break;

            case "Por Marca":
                String marca = JOptionPane.showInputDialog("Marca:");
                if (marca != null) filtrarPorMarca(marca);
                break;

            case "Nenhum":
                atualizarTabela();
                JOptionPane.showMessageDialog(this, "Filtro removido. Mostrando todos os veículos.");
                break;
        }
    }

    private void filtrarPorMatricula(String matricula) {
        modeloTabela.setRowCount(0);

        try {
            String val = matricula.replace("-", "").toUpperCase();
            if (val.length() > 6) val = val.substring(0, 6);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < val.length(); i++) {
                if (i == 2 || i == 4) sb.append("-");
                sb.append(val.charAt(i));
            }
            String matriculaFormatada = sb.toString();

            String regex = "^([A-Z0-9]{2}-){2}[A-Z0-9]{2}$";
            Matcher matcher = Pattern.compile(regex).matcher(matriculaFormatada);
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(this, "Matrícula inválida! Use formato AA-00-AA ou 00-AA-00");
                return;
            }

            for (Veiculo v : Servicos.listarVeiculos()) {
                if (v.getMatricula().equalsIgnoreCase(matriculaFormatada)) {
                    String infoExtra = (v instanceof CarroLigeiro) ?
                            ((CarroLigeiro) v).getNumPortas() + " Portas" :
                            ((Motociclo) v).getCilindrada() + "cc";

                    Object[] row = {
                            v.getClass().getSimpleName(),
                            v.getMatricula(),
                            v.getDono(),
                            v.getMarca() + " " + v.getModelo(),
                            infoExtra,
                            String.format("%.2f€", v.calcularTotalGasto())
                    };
                    modeloTabela.addRow(row);
                }
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao filtrar matrícula:\n" + ex.getMessage());
        }
    }

    private void filtrarPorDono(String dono) {
        modeloTabela.setRowCount(0);

        for (Veiculo v : Servicos.listarVeiculos()) {
            if (v.getDono().equalsIgnoreCase(dono)) {
                String infoExtra = (v instanceof CarroLigeiro) ?
                        ((CarroLigeiro) v).getNumPortas() + " Portas" :
                        ((Motociclo) v).getCilindrada() + "cc";

                Object[] row = {
                        v.getClass().getSimpleName(),
                        v.getMatricula(),
                        v.getDono(),
                        v.getMarca() + " " + v.getModelo(),
                        infoExtra,
                        String.format("%.2f€", v.calcularTotalGasto())
                };
                modeloTabela.addRow(row);
            }
        }
    }

    private void filtrarPorMarca(String marca) {
        modeloTabela.setRowCount(0);

        for (Veiculo v : Servicos.listarVeiculos()) {
            if (v.getMarca().equalsIgnoreCase(marca)) {
                String infoExtra = (v instanceof CarroLigeiro) ?
                        ((CarroLigeiro) v).getNumPortas() + " Portas" :
                        ((Motociclo) v).getCilindrada() + "cc";

                Object[] row = {
                        v.getClass().getSimpleName(),
                        v.getMatricula(),
                        v.getDono(),
                        v.getMarca() + " " + v.getModelo(),
                        infoExtra,
                        String.format("%.2f€", v.calcularTotalGasto())
                };
                modeloTabela.addRow(row);
            }
        }
    }
}