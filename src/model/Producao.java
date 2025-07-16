package model;

import java.time.LocalDate;

public class Producao {
    private Integer idProducao;
    private Produto produto;
    private Funcionario funcionario;
    private LocalDate dataProducao;
    private int quantidade;

    // Construtor corrigido
    public Producao(Integer idProducao, Produto produto, Funcionario funcionario, LocalDate dataProducao, int quantidade) {
        this.idProducao = idProducao;
        this.produto = produto;
        this.funcionario = funcionario;
        this.dataProducao = dataProducao;
        this.quantidade = quantidade;
    }

    public Producao() {

    }

    // Getters e Setters
    public Integer getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(Integer idProducao) {
        this.idProducao = idProducao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataProducao() {
        return dataProducao;
    }

    public void setDataProducao(LocalDate dataProducao) {
        this.dataProducao = dataProducao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    // toString corrigido
    @Override
    public String toString() {
        return "{"
                + "\"idProducao\":" + idProducao + ","
                + "\"produto\":\"" + produto + "\","
                + "\"funcionario\":\"" + funcionario + "\","
                + "\"dataProducao\":\"" + dataProducao + "\","
                + "\"quantidade\":" + quantidade
                + "}";
    }

    public void setDataProducao(String dataProducao) {
    }
}
