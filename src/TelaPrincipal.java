
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;

import dao.ProdutoDAO;
import modelo.Produto;

/**
 * SysCad - Sistema de Cadastro de Produtos
 * ------------------------------------------------------------
 * Tela principal da aplicação, construída em Swing.
 *
 * Versão completa, com CRUD funcional contra o MySQL:
 *   - Salvar   -> insere um novo produto (ProdutoDAO.inserir)
 *   - Atualizar -> grava alterações do produto selecionado
 *                  (ProdutoDAO.atualizar)
 *   - Excluir  -> remove o produto selecionado, com confirmação
 *                  (ProdutoDAO.excluir)
 *   - Limpar   -> esvazia os campos e a seleção da tabela
 *   - Sair     -> encerra a aplicação
 *
 * A tabela abaixo do formulário lista todos os produtos
 * cadastrados (ProdutoDAO.listarTodos) e é recarregada a cada
 * operação de escrita. Clicar numa linha da tabela preenche o
 * formulário com os dados daquele produto, pronto para editar
 * ou excluir.
 *
 * Estrutura da tela (BorderLayout):
 *   NORTH  -> lblTitulo
 *   CENTER -> painelCentral (formulário no topo + tabela abaixo)
 *   SOUTH  -> painelBotoes (Salvar, Atualizar, Excluir, Limpar, Sair)
 */
public class TelaPrincipal extends JFrame {

    // ---- Constantes de layout do formulário ----
    private static final int MARGEM_ESQUERDA = 20;
    private static final int LARGURA_LABEL = 90;
    private static final int LARGURA_CAMPO_PADRAO = 200;
    private static final int LARGURA_CAMPO_NOME = 320;
    private static final int ALTURA_CAMPO = 25;
    private static final int Y_INICIAL = 20;
    private static final int ESPACAMENTO_LINHA = 40;
    private static final int X_CAMPO = MARGEM_ESQUERDA + LARGURA_LABEL + 10;

    // Campos do formulário
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField txtQuantidade;

    // Botões de ação
    private JButton btnSalvar;
    private JButton btnAtualizar;
    private JButton btnExcluir;
    private JButton btnLimpar;
    private JButton btnSair;

    // Tabela de listagem
    private JTable tabelaProdutos;
    private DefaultTableModel modeloTabela;

    // Acesso a dados
    private final ProdutoDAO produtoDAO = new ProdutoDAO();

    public TelaPrincipal() {
        setTitle("SysCad - Cadastro de Produtos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 680);
        setLocationRelativeTo(null);

        montarTela();
    }

    private void montarTela() {
        setLayout(new BorderLayout());

        add(criarTitulo(), BorderLayout.NORTH);
        add(criarPainelCentral(), BorderLayout.CENTER);
        add(criarPainelBotoes(), BorderLayout.SOUTH);

        configurarEventos();
        carregarTabela();
    }

    private JLabel criarTitulo() {
        return new JLabel("Cadastro de Produtos", SwingConstants.CENTER);
    }

    /**
     * Une o formulário (topo) e a tabela de listagem (abaixo)
     * numa única área central.
     */
    private JPanel criarPainelCentral() {
        JPanel painelCentral = new JPanel(new BorderLayout());
        painelCentral.add(criarPainelFormulario(), BorderLayout.NORTH);
        painelCentral.add(criarPainelTabela(), BorderLayout.CENTER);
        return painelCentral;
    }

    /**
     * Formulário com posicionamento absoluto (Aula 3).
     */
    private JPanel criarPainelFormulario() {
        JPanel painelFormulario = new JPanel();
        painelFormulario.setLayout(null);
        painelFormulario.setPreferredSize(new Dimension(600, 190));

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

    /**
     * Tabela somente-leitura com os produtos cadastrados.
     * Selecionar uma linha carrega o produto no formulário.
     */
    private JScrollPane criarPainelTabela() {
        String[] colunas = {"Código", "Nome", "Preço", "Quantidade"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaProdutos = new JTable(modeloTabela);
        tabelaProdutos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaProdutos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                preencherCamposComSelecao();
            }
        });

        return new JScrollPane(tabelaProdutos);
    }

    private JPanel criarPainelBotoes() {
        JPanel painelBotoes = new JPanel(new FlowLayout());

        btnSalvar = new JButton("Salvar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");
        btnLimpar = new JButton("Limpar");
        btnSair = new JButton("Sair");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnAtualizar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnLimpar);
        painelBotoes.add(btnSair);

        return painelBotoes;
    }

    private void configurarEventos() {
        btnSalvar.addActionListener(e -> salvarProduto());
        btnAtualizar.addActionListener(e -> atualizarProduto());
        btnExcluir.addActionListener(e -> excluirProduto());
        btnLimpar.addActionListener(e -> limparCampos());
        btnSair.addActionListener(e -> System.exit(0));
    }

    private void limparCampos() {
        txtCodigo.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        tabelaProdutos.clearSelection();
    }

    /**
     * Preenche o formulário com os dados da linha selecionada na
     * tabela, deixando o produto pronto para ser editado ou
     * excluído.
     */
    private void preencherCamposComSelecao() {
        int linha = tabelaProdutos.getSelectedRow();
        if (linha == -1) {
            return;
        }
        txtCodigo.setText(String.valueOf(modeloTabela.getValueAt(linha, 0)));
        txtNome.setText(String.valueOf(modeloTabela.getValueAt(linha, 1)));
        txtPreco.setText(String.valueOf(modeloTabela.getValueAt(linha, 2)));
        txtQuantidade.setText(String.valueOf(modeloTabela.getValueAt(linha, 3)));
    }

    /**
     * Recarrega a tabela a partir do banco. Chamado no início e
     * após qualquer operação que altere os dados (salvar,
     * atualizar, excluir).
     */
    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        try {
            for (Produto produto : produtoDAO.listarTodos()) {
                modeloTabela.addRow(new Object[]{
                        produto.getCodigo(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao carregar produtos: " + e.getMessage(),
                    "Erro de banco de dados",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Lê os campos, valida, converte os valores numéricos e
     * insere um novo Produto no banco de dados.
     */
    private void salvarProduto() {
        String codigo = txtCodigo.getText().trim();
        String nome = txtNome.getText().trim();
        String textoPreco = txtPreco.getText().trim();
        String textoQuantidade = txtQuantidade.getText().trim();

        if (codigo.isEmpty() || nome.isEmpty() || textoPreco.isEmpty() || textoQuantidade.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double preco = Double.parseDouble(textoPreco);
            int quantidade = Integer.parseInt(textoQuantidade);

            produtoDAO.inserir(new Produto(codigo, nome, preco, quantidade));

            JOptionPane.showMessageDialog(this,
                    "Produto cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarTabela();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Preço e quantidade devem ser números válidos.",
                    "Erro de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao salvar: " + ex.getMessage(),
                    "Erro de banco de dados",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Atualiza o produto cujo código está no formulário (normalmente
     * preenchido ao selecionar uma linha da tabela).
     */
    private void atualizarProduto() {
        String codigo = txtCodigo.getText().trim();
        String nome = txtNome.getText().trim();
        String textoPreco = txtPreco.getText().trim();
        String textoQuantidade = txtQuantidade.getText().trim();

        if (codigo.isEmpty() || nome.isEmpty() || textoPreco.isEmpty() || textoQuantidade.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um produto na tabela e preencha todos os campos.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double preco = Double.parseDouble(textoPreco);
            int quantidade = Integer.parseInt(textoQuantidade);

            produtoDAO.atualizar(new Produto(codigo, nome, preco, quantidade));

            JOptionPane.showMessageDialog(this,
                    "Produto atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarTabela();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Preço e quantidade devem ser números válidos.",
                    "Erro de formato",
                    JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao atualizar: " + ex.getMessage(),
                    "Erro de banco de dados",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Exclui o produto selecionado, após confirmação do usuário.
     */
    private void excluirProduto() {
        String codigo = txtCodigo.getText().trim();

        if (codigo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Selecione um produto na tabela para excluir.",
                    "Nenhum produto selecionado",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacao = JOptionPane.showConfirmDialog(this,
                "Deseja realmente excluir o produto " + codigo + "?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirmacao != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            produtoDAO.excluir(codigo);
            JOptionPane.showMessageDialog(this,
                    "Produto excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            limparCampos();
            carregarTabela();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao excluir: " + ex.getMessage(),
                    "Erro de banco de dados",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelaPrincipal().setVisible(true);
    }
}
