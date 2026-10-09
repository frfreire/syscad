package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SysCad - Conexão com o banco de dados
 * ------------------------------------------------------------
 * Ponto único e reutilizável para abrir conexões JDBC com o
 * MySQL. Introduzida na Aula 5 (JDBC - java.sql - Conexão com
 * Banco de Dados).
 *
 * Pré-requisitos para esta classe funcionar:
 *   1. MySQL Server rodando localmente na porta 3306.
 *   2. Banco "syscad_db" e tabela "produtos" criados
 *      (veja o script criar_banco.sql).
 *   3. O driver MySQL Connector/J adicionado às Referenced
 *      Libraries do projeto no VS Code.
 *
 * Ajuste USUARIO e SENHA para as credenciais do seu MySQL local
 * antes de executar a aplicação.
 */
public class ConexaoBD {

    private static final String URL =
            "jdbc:mysql://localhost:3306/syscad_db";
    private static final String USUARIO = "root";
    private static final String SENHA = "Root@123";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /**
     * Teste isolado de conexão, independente da interface gráfica.
     */
    public static void main(String[] args) {
        try (Connection conexao = conectar()) {
            System.out.println("Conexão realizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
    }
}
