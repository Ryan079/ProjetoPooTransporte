package dados.modelo.pessoa;

import java.io.Serializable;

public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1002L;
    protected String nome;
    protected String cpf; //CPF é um dado imutável utilizado para pesquisas no repositório
    protected String telefone;
    protected String idade;
    protected char sexo;
    protected double avaliacao;

    public Pessoa(String cpf, String nome, String telefone, String idade, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.sexo = sexo;
        this.avaliacao = 5.0;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", sexo=" + sexo +
                '}';
    }
}
