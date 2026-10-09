package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexao.ConexaoBD;
import modelo.Produto;

/**
 * SysCad - Acesso a dados de Produto
 * ------------------------------------------------------------
 * Concentra todo o SQL da aplicação num único lugar: nenhuma
 * outra classe do SysCad monta comandos SQL diretamente. Isso
 * antecipa o padrão DAO (Data Access Object) formalmente
 * estudado mais adiante na disciplina — introduzido aqui de
 * forma simples porque a tela precisa de operações completas
 * de cadastro (criar, listar, atualizar, excluir) para ser uma
 * aplicação utilizável de ponta a ponta.
 *
 * Todos os métodos usam PreparedStatement (proteção contra SQL
 * Injection) e try-with-resources (fechamento automático de
 * Connection, PreparedStatement e ResultSet).
 */
public class ProdutoDAO {

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO produtos (codigo, nome, preco, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getQuantidade());

            stmt.executeUpdate();
        }
    }

    public List<Produto> listarTodos() throws SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade FROM produtos ORDER BY nome";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getString("codigo"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade")
                ));
            }
        }
        return produtos;
    }

    public Produto buscarPorCodigo(String codigo) throws SQLException {
        String sql = "SELECT codigo, nome, preco, quantidade FROM produtos WHERE codigo = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, codigo);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                            rs.getString("codigo"),
                            rs.getString("nome"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade")
                    );
                }
            }
        }
        return null;
    }

    public void atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE produtos SET nome = ?, preco = ?, quantidade = ? WHERE codigo = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidade());
            stmt.setString(4, produto.getCodigo());

            stmt.executeUpdate();
        }
    }

    public void excluir(String codigo) throws SQLException {
        String sql = "DELETE FROM produtos WHERE codigo = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
    }
}
