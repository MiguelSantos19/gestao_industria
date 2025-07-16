package dao;

import config.ConexaoMYSQL;
import model.Produto;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutoDAO {
    private Connection conn = ConexaoMYSQL.getConnection();

    public ArrayList<Produto> listar() {
        try {
            ArrayList<Produto> produtos = new ArrayList<>();

            String sql = "SELECT * FROM produtos;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_produto");
                String nome = rs.getString("nome_produto");
                String descricao = rs.getString("descricao");

                produtos.add(new Produto(id, nome, descricao));
            }
            return produtos;
        } catch (SQLException e) {
            System.out.println("Erro ao listar os Setores: " + e.getMessage());
        }
        return null;
    }

    public Produto buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM produtos WHERE id_produto = ? ;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next() == true) {
                String nome = rs.getString("nome_produto");
                String descricao = rs.getString("descricao");
                Produto produto = new Produto(id, nome, descricao);
                return produto;
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pelo ID. " + e.getMessage());
        }
        return null;
    }

    public Boolean cadastrar(Produto produto) {
        try {
            String sql = "INSERT INTO produtos (nome_produto, descricao) VALUES (?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());

            int qtdeLinhas = ps.executeUpdate();
            if (qtdeLinhas > 0) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
        return false;
    }

    public Boolean atualizar(Produto produto) {
        try {
            String sql = "UPDATE produtos SET nome_produto = ?, descricao = ? WHERE id_produto = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setInt(3, produto.getIdProduto());

            int qtdeAtualizacao = ps.executeUpdate();

            if (qtdeAtualizacao > 0) {
                return true;
            }
            return false;


        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cadastro: " + e.getMessage());
        }
        return null;
    }

    public Boolean remover(Integer id) {
        try {
            String sql = "DELETE FROM produtos WHERE id_produto = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            Produto produtoRetornado = buscarPorId(id);
            if (produtoRetornado != null) {
                ps.executeUpdate();
                return true;
            }
            return false;

        } catch (SQLException e) {
            System.out.println("Erro ao deletar model.Produto: " + e.getMessage());
        }
        return false;
    }


}