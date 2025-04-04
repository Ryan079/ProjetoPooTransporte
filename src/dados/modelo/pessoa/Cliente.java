package dados.modelo.pessoa;

import dados.modelo.pagamento.FormaDePagamento;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa{
    private double saldo;
    private List<FormaDePagamento> formaDePagamentos;

    public Cliente(String cpf, String nome, String telefone, char sexo) {
        super(cpf, nome, telefone, sexo);
        this.saldo = 0.0;
        this.formaDePagamentos = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public List<FormaDePagamento> getFormaDePagamentos() {
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
