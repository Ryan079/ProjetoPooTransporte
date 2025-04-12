package negocio.gerenciador;

import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.veiculo.Veiculo;
import dados.repositorio.IRepositorioPessoa;
import dados.repositorio.IRepositorioVeiculo;
import dados.repositorio.RepositorioMotoristaArquivo;
import dados.repositorio.RepositorioVeiculoArquivo;
import negocio.excecoes.EntidadeJaExisteException;
import negocio.excecoes.EntradaInvalidaException;

import java.util.List;

public class GerenciadorMotorista {
    private IRepositorioPessoa<Motorista> repoMotorista;
    private IRepositorioVeiculo repoVeiculo;

    public GerenciadorMotorista() {
        this.repoMotorista = new RepositorioMotoristaArquivo();
        this.repoVeiculo = new RepositorioVeiculoArquivo();
    }

    public void cadastrarMotorista(String cpf, String nome, String telefone, String idade, char sexo, String cnh, String cor, TipoVeiculo tipo, String marca, String modelo, String placa) throws EntidadeJaExisteException {
        if(repoMotorista.buscarPorIdentificador(cnh) != null)
            throw new EntidadeJaExisteException("Já existe um motorista registrado com essa cnh");

        if(repoVeiculo.buscarPorPlaca(placa) != null)
            throw new EntidadeJaExisteException("Já existe um veículo registrado com essa placa");

        Veiculo veiculo = new Veiculo(cor, tipo, marca, modelo, placa);

        Motorista motorista = new Motorista(cpf, nome, telefone, idade, sexo, cnh, veiculo);
        repoMotorista.adicionar(motorista);
        repoVeiculo.adicionar(veiculo);
    }

    public Motorista buscarMotorista(String id) {
        return repoMotorista.buscarPorIdentificador(id);
    }

    public void avaliarMotorista(Motorista m, double nota) throws EntradaInvalidaException {
        if(nota > 5 || nota < 1)
            throw new EntradaInvalidaException();
        m.setAvaliacao((m.getAvaliacao() + nota) / 2);
        repoMotorista.atualizar(m);
    }

    public void alterarDisponibilidade(Motorista m) {
        if(m.isDisponivel())
            m.setDisponivel(false);
        m.setDisponivel(true);
    }

    public List<Motorista> listar() {
        return repoMotorista.listar();
    }
}
