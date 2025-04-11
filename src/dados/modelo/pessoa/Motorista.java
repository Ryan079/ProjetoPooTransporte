package dados.modelo.pessoa;
import dados.modelo.veiculo.Veiculo;

public class Motorista extends Pessoa{
    private String cnh;
    private Veiculo veiculo;

    public Motorista(String cpf, String nome, String telefone, String idade, char sexo, String cnh, Veiculo veiculo) {
        super(cpf, nome, telefone, idade, sexo);
        this.cnh = cnh;
        this.veiculo = veiculo;
    }

    public String getCnh() {
        return cnh;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String toString() {
        return "=== Motorista ===\n" +
                "Nome: " + nome + "\n" +
                "CPF: " + cpf + "\n" +
                "Idade: " + idade + "\n" +
                "Sexo: " + sexo + "\n" +
                "Telefone: " + telefone + "\n" +
                "CNH: " + cnh + "\n" +
                "Avaliação: " + String.format("%.1f", avaliacao) + "/5.0\n" +
                veiculo + "\n";
    }
}
