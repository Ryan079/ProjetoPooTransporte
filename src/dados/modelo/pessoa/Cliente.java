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

    public void debitarSaldo(double valor) {
            this.saldo -= valor;
    }

    public void creditarSaldo(double valor) {
            this.saldo += valor;
    }

    public void adicionarFormaDePagamento(FormaDePagamento forma) {
        this.formaDePagamentos.add(forma);
    }

    public Set<FormaDePagamento> getFormaDePagamentos() {
        return formaDePagamentos;
    }

    @Override
    public String toString() {
        return "=== Cliente ===\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Idade: " + idade + "\n" +
                "Sexo: " + sexo + "\n" +
                "Telefone: " + telefone + "\n" +
                "Forma de Pagamentos: " + formaDePagamentos + "\n" +
                "Saldo: R$ " + String.format("%.2f", saldo) + "\n" +
                "Avaliação: " + String.format("%.1f", avaliacao) + "/5.0";
    }
}
