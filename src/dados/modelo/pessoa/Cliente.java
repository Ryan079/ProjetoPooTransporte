package dados.modelo.pessoa;

public class Cliente extends Pessoa{
    private double saldo;
    public Cliente(String cpf, String nome, String telefone, char sexo) {
        super(cpf, nome, telefone, sexo);
        this.saldo = 0.0;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
