package dao;

import config.ConexaoMYSQL;
import model.Produto;
import model.Setor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SetorDAO {
    private Connection conn = ConexaoMYSQL.getConnection();

    public ArrayList<Setor> listar() {
        try {
            ArrayList<Setor> setores = new ArrayList<>();
            String sql = "SELECT * FROM setores;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id_setor");
                String nome = rs.getString("nome_setor");
                String responsavel = rs.getString("responsavel");

                Setor setor = new Setor(id, nome, responsavel);

                setores.add(setor);
            }
            return setores;

        } catch (SQLException e) {
            System.out.println("Deu ruim em listar os clientes. " + e.getMessage());
            return null;
        }
    }

    public Setor buscarPorId(Integer id) {
        try {
            String sql = "SELECT * FROM setores WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Integer id_setor = rs.getInt("id_setor");
                String nome = rs.getString("nome_produto");
                String responsavel = rs.getString("responsavel");

                return new Setor(id_setor, nome, responsavel);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar id. " + e.getMessage());
        }

        return null;
    }

    public Boolean cadastrar(Setor setor) {
        try {

            String sql = "INSERT INTO setores (nome_setor,responsavel)VALUES(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,setor.getNome());
            ps.setString(2,setor.getResponsavel());
            int qtdeLinha = ps.executeUpdate();

            if (qtdeLinha > 0){
                return true;
            }else {
                return false;
            }

        }catch (SQLException e){

            System.out.println("Erro ao cadastrar setor. " + e.getMessage());

            return false;
        }

    }

    public Boolean atualizar(Setor setor) {
        try {

            String sql = "UPDATE setores SET nome_setor = ?, responsavel = ? WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,setor.getNome());
            ps.setString(2,setor.getResponsavel());
            ps.setInt(3,setor.getIdSetor());
            int qtdeAtualizacoes = ps.executeUpdate();

            if (qtdeAtualizacoes > 0 ){
                return true;
            }else {
                return false;
            }

        }catch (SQLException e){
            System.out.println("Erro ao atualizar o setor " + e.getMessage());
        }
        return false;

    }

    public Boolean remover(Integer id) {
        try {
            String sql = "DELETE FROM setores WHERE id_setor = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            Setor setorRetornado = buscarPorId(id);
            int qtdeRemovidos = ps.executeUpdate();

            if (qtdeRemovidos > 0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao remover setor. " + e.getMessage());

        }
        return false;
    }

    public static class ProdutoDAO {
        private Connection conn = ConexaoMYSQL.getConnection();

        public ArrayList<Produto> listar() {
            try {
                ArrayList<Produto> produtos = new ArrayList<>();
                String sql = "SELECT * FROM produto;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Integer id = rs.getInt("id_produto");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");

                    Produto produto = new Produto(id, nome, descricao);

                    produtos.add(produto);
                }
                return produtos;

            } catch (SQLException e) {
                System.out.println("Erro ao listar os produtos. " + e.getMessage());
                return null;
            }
        }

        public Produto buscarPorId(Integer id) {
            try {
                String sql = "SELECT * FROM produto WHERE id_produto = ?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Integer idProduto = rs.getInt("id_produto");
                    String nome = rs.getString("nome");
                    String descricao = rs.getString("descricao");
                    Produto produto = new Produto(idProduto, nome, descricao);
                    return produto;
                }
                return null;

            } catch (SQLException e) {
                System.out.println("Erro ao buscar o produto pelo ID. " + e.getMessage());
            }

            return null;
        }

        public Boolean cadastrar(Produto produto) {
            try {
                String sql = "INSERT INTO produto (nome, descricao) VALUES(?,?);";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, produto.getNome());
                ps.setString(2, produto.getDescricao());
                int qtdeLinha = ps.executeUpdate();

                return qtdeLinha > 0;

            } catch (SQLException e) {
                System.out.println("Erro ao cadastrar produto. " + e.getMessage());
            }

            return false;
        }

        public Boolean atualizar(Produto produto) {
            try {
                String sql = "UPDATE produto SET nome = ?, descricao = ? WHERE id_produto = ?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, produto.getNome());
                ps.setString(2, produto.getDescricao());
                ps.setInt(3, produto.getIdProduto());
                int qtdeAtualizacoes = ps.executeUpdate();

                return qtdeAtualizacoes > 0;

            } catch (SQLException e) {
                System.out.println("Erro ao atualizar o produto. " + e.getMessage());
            }
            return false;
        }

        public Boolean remover(Integer id) {
            try {
                String sql = "DELETE FROM produto WHERE id_produto = ?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);

                Produto produtoRetornado = buscarPorId(id);

                if (produtoRetornado != null) {
                    ps.executeUpdate();
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao deletar o produto. " + e.getMessage());
            }

            return false;
        }
    }
}