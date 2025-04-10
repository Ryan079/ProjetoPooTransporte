package negocio.gerenciador;

import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.veiculo.Veiculo;
import dados.repositorio.IRepositorioPessoa;
import dados.repositorio.IRepositorioVeiculo;
import dados.repositorio.RepositorioMotoristaArquivo;
import dados.repositorio.RepositorioVeiculoArquivo;
import negocio.excecoes.EntidadeJaExisteException;
import utilitario.Validador;

import java.util.List;

public class GerenciadorMotorista {
    private IRepositorioPessoa<Motorista> repoMotorista;
    private IRepositorioVeiculo repoVeiculo;

    public GerenciadorMotorista() {
        this.repoMotorista = new RepositorioMotoristaArquivo();
        this.repoVeiculo = new RepositorioVeiculoArquivo();
    }

    public void cadastrarMotorista(String cpf, String nome, String telefone, String idade, char sexo, String cnh, String cor, TipoVeiculo tipo, String marca, String modelo, String placa) throws EntidadeJaExisteException, IllegalArgumentException {
        if(repoMotorista.buscarPorIdentificador(cnh) != null)
            throw new EntidadeJaExisteException("Já existe um motorista registrado com essa cnh");

        if(repoVeiculo.buscarPorPlaca(placa) != null)
            throw new EntidadeJaExisteException("Já existe um veículo registrado com essa placa");

        Veiculo veiculo = new Veiculo(cor, tipo, marca, modelo, placa);

        Motorista motorista = new Motorista(cpf, nome, telefone, idade, sexo, cnh, veiculo);
        repoMotorista.adicionar(motorista);
        repoVeiculo.adicionar(veiculo);
    }

    public Motorista buscarMotorista(String cnh) {
        return repoMotorista.buscarPorIdentificador(cnh);
    }

    public List<Motorista> listar() {
        return repoMotorista.listar();
    }
}
