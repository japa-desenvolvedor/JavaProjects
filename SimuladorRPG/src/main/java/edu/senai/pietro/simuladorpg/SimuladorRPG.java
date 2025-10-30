package edu.senai.pietro.simuladorpg;

import edu.senai.pietro.simuladorrpg.classes.Jogador;
import edu.senai.pietro.simuladorrpg.classes.Monstro;
import edu.senai.pietro.simuladorrpg.model.Equipamento;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

public class SimuladorRPG extends JFrame {

    private static final Logger logger
            = java.util.logging.Logger.getLogger(SimuladorRPG.class.getName());

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // Telas
    private JPanel telaCriacao;
    private JPanel telaCombate;
    private JPanel telaResultado;

    // Dados do jogo
    private Jogador jogador;
    private Monstro monstroEscolhido;

    // Componentes da tela de criação
    private JTextField campoNome;
    private JComboBox<Equipamento> comboArma;
    private JComboBox<Equipamento> comboArmadura;
    private JSpinner spinnerNivel;

    // Componentes da tela de combate
    private JComboBox<Monstro> comboMonstro;
    private JTextArea areaCombate;

    // Botão de atacar
    private JButton btnLutar;

    // Área de resultado (campo da classe para facilitar acesso)
    private JTextArea resultadoArea;

    public SimuladorRPG() {
        initComponents();
        criarTelas();
        mostrarTela("criacao");
    }

    private void initComponents() {
        setTitle("Simulador RPG");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        add(mainPanel);
    }

    private void criarTelas() {
        // ===== TELA DE CRIAÇÃO DO JOGADOR =====
        telaCriacao = new JPanel(new BorderLayout());
        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        form.add(new JLabel("Nome do Herói:"));
        campoNome = new JTextField("Guerreiro");
        form.add(campoNome);

        form.add(new JLabel("Nível (1-10):"));
        spinnerNivel = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
        form.add(spinnerNivel);

        form.add(new JLabel("Arma:"));
        comboArma = new JComboBox<>(Equipamento.values());
        form.add(comboArma);

        form.add(new JLabel("Armadura:"));
        comboArmadura = new JComboBox<>(Equipamento.values());
        form.add(comboArmadura);

        JButton btnCriar = new JButton("Criar Personagem e Entrar na Masmorra!");
        btnCriar.addActionListener(e -> criarJogador());

        telaCriacao.add(new JLabel("⚔️ CRIE SEU HERÓI ⚔️", JLabel.CENTER), BorderLayout.NORTH);
        telaCriacao.add(form, BorderLayout.CENTER);
        telaCriacao.add(btnCriar, BorderLayout.SOUTH);

        // ===== TELA DE COMBATE =====
        telaCombate = new JPanel(new BorderLayout());
        JPanel painelMonstro = new JPanel(new FlowLayout());
        painelMonstro.add(new JLabel("Escolha seu inimigo:"));
        comboMonstro = new JComboBox<>(Monstro.values());
        painelMonstro.add(comboMonstro);

        // botão de atacar (corrigido: variável criada e listener adicionada)
        btnLutar = new JButton("Atacar!");
        btnLutar.addActionListener(e -> iniciarCombate());

        areaCombate = new JTextArea();
        areaCombate.setEditable(false);
        areaCombate.setFont(new Font("Monospaced", Font.PLAIN, 13));

        telaCombate.add(painelMonstro, BorderLayout.NORTH);
        telaCombate.add(new JScrollPane(areaCombate), BorderLayout.CENTER);
        telaCombate.add(btnLutar, BorderLayout.SOUTH);

        // ===== TELA DE RESULTADO =====
        telaResultado = new JPanel(new BorderLayout());
        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setFont(new Font("Monospaced", Font.BOLD, 14));
        resultadoArea.setWrapStyleWord(true);
        resultadoArea.setLineWrap(true);

        JButton btnNovoJogo = new JButton(  "Jogar Novamente");
        btnNovoJogo.addActionListener(e -> {
            // limpa dados e volta para criação
            jogador = null;
            monstroEscolhido = null;
            campoNome.setText("Guerreiro");
            spinnerNivel.setValue(5);
            mostrarTela("criacao");
        });

        telaResultado.add(new JLabel("🏆 RESULTADO 🏆", JLabel.CENTER), BorderLayout.NORTH);
        telaResultado.add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
        telaResultado.add(btnNovoJogo, BorderLayout.SOUTH);

        // Adicionar telas ao CardLayout
        mainPanel.add(telaCriacao, "criacao");
        mainPanel.add(telaCombate, "combate");
        mainPanel.add(telaResultado, "resultado");
    }

    private void criarJogador() {
        String nome = campoNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, digite um nome!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int nivel = (Integer) spinnerNivel.getValue();
        Equipamento arma = (Equipamento) comboArma.getSelectedItem();
        Equipamento armadura = (Equipamento) comboArmadura.getSelectedItem();

        // Aqui você usa sua classe Jogador de verdade!
        jogador = new Jogador(nome, nivel, arma, armadura);

        mostrarTela("combate");
        areaCombate.setText("Herói " + jogador.getNome() + " (Nível " + jogador.getNivel() + ") está pronto para lutar!\n"
                + "Escolha um monstro e ataque!");
    }

    private void iniciarCombate() {
        monstroEscolhido = (Monstro) comboMonstro.getSelectedItem();
        if (jogador == null) {
            JOptionPane.showMessageDialog(this, "Crie seu herói antes de lutar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (monstroEscolhido == null) {
            JOptionPane.showMessageDialog(this, "Escolha um monstro antes de atacar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Consulte os métodos reais das suas classes Jogador e Monstro:
        int forcaJogador;
        try {
            forcaJogador = jogador.getForcaTotal(); // implemente / verifique este método em Jogador
        } catch (Exception ex) {
            // fallback simples caso o método não exista
            forcaJogador = jogador.getNivel();
        }

        int forcaMonstro;
        try {
            // aqui eu assumi que Monstro tem getNivel(), se for outro método adapte
            forcaMonstro = monstroEscolhido.getNivel();
        } catch (Exception ex) {
            forcaMonstro = 1;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("⚔️ COMBATE INICIADO! ⚔️\n\n");
        sb.append("Herói: ").append(jogador.getNome()).append(" (Nível ").append(jogador.getNivel()).append(")\n");
        sb.append("Monstro: ").append(monstroEscolhido.getNome()).append(" (Nível ").append(forcaMonstro).append(")\n\n");
        sb.append("Sua força total: ").append(forcaJogador).append("\n");
        sb.append("Força do monstro: ").append(forcaMonstro).append("\n\n");

        if (forcaJogador > forcaMonstro) {
            sb.append("✅ VITÓRIA! Você saqueou ").append(monstroEscolhido.getNome()).append(" e ganhou ouro!");
        } else if (forcaJogador < forcaMonstro) {
            sb.append("❌ DERROTA! Você foi humilhado por um ").append(monstroEscolhido.getNome()).append("!");
        } else {
            sb.append("🤝 EMPATE! Ambos recuam, exaustos...");
        }

        // Mostrar resultado
        resultadoArea.setText(sb.toString());
        mostrarTela("resultado");
    }

    private void mostrarTela(String nome) {
        cardLayout.show(mainPanel, nome);
    }

    // ===== MAIN =====
    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        EventQueue.invokeLater(() -> new SimuladorRPG().setVisible(true));
    }
}
