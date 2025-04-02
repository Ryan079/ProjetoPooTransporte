package negocio.gerenciador;

import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.Veiculo;
import dados.repositorio.IRepositorioPessoa;
import dados.repositorio.IRepositorioVeiculo;
import dados.repositorio.RepositorioMotoristaArquivo;
import negocio.excecoes.EntidadeJaExisteException;
import utilitario.Validador;

import java.util.List;

public class GerenciadorMotorista {
    private IRepositorioPessoa<Motorista> repoMotorista;
    private IRepositorioVeiculo repoVeiculo;

    public GerenciadorMotorista() {
        this.repoMotorista = new RepositorioMotoristaArquivo();
    }

    public void cadastrarMotorista(String cpf, String nome, String telefone, char sexo, String cnh, Veiculo veiculo) throws EntidadeJaExisteException, IllegalArgumentException {
        if(repoMotorista.buscarPorIdentificador(cnh) != null)
            throw new EntidadeJaExisteException("Já existe um motorista registrado com essa cnh");

        if(repoVeiculo.buscarPorPlaca(veiculo.getPlaca()) != null)
            throw new EntidadeJaExisteException("Já existe um veículo registrado com essa placa");

        Motorista motorista = new Motorista(cpf, nome, telefone, sexo, cnh, veiculo);
        repoMotorista.adicionar(motorista);
    }

    public Motorista buscarMotorista(String cnh) {
        return repoMotorista.buscarPorIdentificador(cnh);
    }

    public List<Motorista> listar() {
        return repoMotorista.listar();
    }
}
