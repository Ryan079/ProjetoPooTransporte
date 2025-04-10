package dados.modelo.pessoa;

import dados.modelo.pagamento.FormaDePagamento;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Cliente extends Pessoa{
    private double saldo;
    private Set<FormaDePagamento> formaDePagamentos; //Utilizado para evitar duplicatas na forma de pagamentos

    public Cliente(String cpf, String nome, String telefone, String idade, char sexo) {
        super(cpf, nome, telefone, idade, sexo);
        this.saldo = 0.0;
        this.formaDePagamentos = new HashSet<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void adicionarFormaDePagamento(FormaDePagamento forma) {
        this.formaDePagamentos.add(forma);
    }

    public Set<FormaDePagamento> getFormaDePagamentos() {
        return formaDePagamentos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", telefone='" + telefone + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
