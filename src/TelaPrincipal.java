import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {

    // Campos do formulário
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtQuantidade;

    // Botões de ação
    private JButton btnSalvar;
    private JButton btnLimpar;
    private JButton btnSair;

    private static final int MARGEM_ESQUERDA = 20;
    private static final int LARGURA_LABEL = 90;
    private static final int LARGURA_CAMPO_PADRAO = 200;
    private static final int LARGURA_CAMPO_NOME = 320;
    private static final int ALTURA_CAMPO = 25;
    private static final int Y_INICIAL = 20;
    private static final int ESPACAMENTO_LINHA = 40;
    private static final int X_CAMPO = MARGEM_ESQUERDA + LARGURA_LABEL + 10;


    public TelaPrincipal() {
        setTitle("SysCad - Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 430);
        setResizable(false);
        setLocationRelativeTo(null);
 
        montarTela();
    }

    /**
     * Centraliza a construção da interface em um único método,
     * separando "como a tela é montada" do construtor.
     */
    private void montarTela() {
        setLayout(new BorderLayout());
 
        add(criarTitulo(), BorderLayout.NORTH);
        add(criarPainelFormulario(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);

    }

    private JLabel criarTitulo() {
        return new JLabel("Cadastro de Produtos", SwingConstants.CENTER);
    }

    private JPanel criarPainelFormulario() {
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(null);
 
        txtCodigo = new JTextField();
        txtNome = new JTextField();
        txtPreco = new JTextField();
        txtQuantidade = new JTextField();
 
        int y = Y_INICIAL;
 
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(MARGEM_ESQUERDA, y, LARGURA_LABEL, ALTURA_CAMPO);
        txtCodigo.setBounds(X_CAMPO, y, LARGURA_CAMPO_PADRAO, ALTURA_CAMPO);
        y += ESPACAMENTO_LINHA;
 
        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(MARGEM_ESQUERDA, y, LARGURA_LABEL, ALTURA_CAMPO);
        txtNome.setBounds(X_CAMPO, y, LARGURA_CAMPO_NOME, ALTURA_CAMPO);
        y += ESPACAMENTO_LINHA;
 
        JLabel lblPreco = new JLabel("Preço:");
        lblPreco.setBounds(MARGEM_ESQUERDA, y, LARGURA_LABEL, ALTURA_CAMPO);
        txtPreco.setBounds(X_CAMPO, y, LARGURA_CAMPO_PADRAO, ALTURA_CAMPO);
        y += ESPACAMENTO_LINHA;
 
        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(MARGEM_ESQUERDA, y, LARGURA_LABEL, ALTURA_CAMPO);
        txtQuantidade.setBounds(X_CAMPO, y, LARGURA_CAMPO_PADRAO, ALTURA_CAMPO);
 
        painelFormulario.add(lblCodigo);
        painelFormulario.add(txtCodigo);
        painelFormulario.add(lblNome);
        painelFormulario.add(txtNome);
        painelFormulario.add(lblPreco);
        painelFormulario.add(txtPreco);
        painelFormulario.add(lblQuantidade);
        painelFormulario.add(txtQuantidade);
 
        return painelFormulario;
    }

    private JPanel criarPainelBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout());
 
        btnSalvar = new JButton("Salvar");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");
 
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);
 
        // TODO (próxima aula - Tratamento de Eventos):
        // btnSalvar.addActionListener(...);
        // btnLimpar.addActionListener(...);
        btnSair.addActionListener(
            e -> System.exit(0)
        );

        return painelBotoes;
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}
