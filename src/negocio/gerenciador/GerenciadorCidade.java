package negocio.gerenciador;

import dados.modelo.cidade.Cidade;
import dados.repositorio.IRepositorioCidade;
import dados.repositorio.RepositorioCidadeArquivo;
import negocio.excecoes.EntidadeJaExisteException;

import java.util.List;

public class GerenciadorCidade {
    private IRepositorioCidade repo;

    public GerenciadorCidade() {
        this.repo = new RepositorioCidadeArquivo();
    }

    public void cadastrarCidade(String nome) throws EntidadeJaExisteException {
        if(repo.buscarPorNome(nome) != null)
            throw new EntidadeJaExisteException("A cidade de nome " + nome + " já está cadastrada no sistema");
        repo.adicionar(new Cidade(nome));
    }

    public Cidade buscarCidade(String nome) {
        return repo.buscarPorNome(nome);
    }

    public List<Cidade> listar() {
        return repo.listar();
    }
}
