-- SysCad - Script de criação do banco de dados
-- ------------------------------------------------------------
-- Encontro 5 (JDBC - java.sql - Conexão com Banco de Dados)
-- Execute este script no MySQL Workbench ou no cliente de
-- linha de comando (mysql -u root -p < criar_banco.sql).

CREATE DATABASE IF NOT EXISTS syscad_db;
USE syscad_db;

CREATE TABLE IF NOT EXISTS produtos (
    codigo      VARCHAR(20) PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    preco       DECIMAL(10,2) NOT NULL,
    quantidade  INT NOT NULL
);
