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

    public TelaPrincipal() {
        setTitle("SysCad - Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
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
        JLabel lblTitulo = new JLabel("Cadastro de Produtos", SwingConstants.CENTER);
        return lblTitulo;
    }

    private JPanel criarPainelFormulario() {
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));

        txtCodigo = new JTextField();
        txtNome = new JTextField();
        txtPreco = new JTextField();
        txtQuantidade = new JTextField();

        painelFormulario.add(new JLabel("Código:"));
        painelFormulario.add(txtCodigo);

        painelFormulario.add(new JLabel("Nome:"));
        painelFormulario.add(txtNome);

        painelFormulario.add(new JLabel("Preço:"));
        painelFormulario.add(txtPreco);

        painelFormulario.add(new JLabel("Quantidade:"));
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
        // btnSair.addActionListener(...);

        return painelBotoes;
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}
