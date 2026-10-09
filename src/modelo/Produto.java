package modelo;

/**
 * SysCad - Modelo de dados
 * ------------------------------------------------------------
 * Representa um produto cadastrado no sistema. É um POJO (Plain
 * Old Java Object): só guarda dados e expõe getters, sem lógica
 * de tela ou de persistência.
 *
 * Introduzida na Aula 4 (Swing Parte 03 - Tratamento de Eventos)
 * e reaproveitada a partir da Aula 5 (JDBC) como a representação
 * de cada linha da tabela "produtos" no MySQL.
 */
public class Produto {

    private String codigo;
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String codigo, String nome, double preco, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }
}
