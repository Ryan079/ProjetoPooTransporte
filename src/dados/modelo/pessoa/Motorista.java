package dados.modelo.pessoa;
import dados.modelo.veiculo.Veiculo;

public class Motorista extends Pessoa{
    private String cnh;
    private Veiculo veiculo;

    public Motorista(String cpf, String nome, String telefone, char sexo, String cnh, Veiculo veiculo) {
        super(cpf, nome, telefone, sexo);
        this.cnh = cnh;
        this.veiculo = veiculo;
    }

    public String getCnh() {
        return cnh;
    }





}
