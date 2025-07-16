package model;

public class Produto {
    private Integer idProduto;
    private String nome;
    private String descricao;

    public Produto(){

    }

    public Produto(Integer idProduto, String nome, String descricao) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "{"
                + "\"idProduto\":" + idProduto + ","
                + "\"nome\":\"" + nome + "\","
                + "\"descricao\":\"" + descricao + "\""
                + "}";
    }
}

