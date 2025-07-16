package model;

public class Funcionario {
    private Integer idFuncionario;
    private String nome;
    private String cargo;
    private Setor setor;

    public Funcionario() {
    }

    public Funcionario(Integer idFuncionario, String nome, String cargo, Setor setor) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.cargo = cargo;
        this.setor = setor;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }


    @Override
    public String toString() {
        return "{"
                + "\"idFuncionario\":" + idFuncionario + ","
                + "\"nome\":\"" + nome + "\","
                + "\"cargo\":\"" + cargo + "\","
                + "\"setor\":" + setor
                + "}";
    }


}
