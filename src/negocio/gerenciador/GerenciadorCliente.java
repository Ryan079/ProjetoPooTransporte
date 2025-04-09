package negocio.gerenciador;

import dados.modelo.pessoa.Cliente;
import dados.repositorio.IRepositorioPessoa;
import dados.repositorio.RepositorioClienteArquivo;
import negocio.excecoes.EntidadeJaExisteException;

import java.util.List;

public class GerenciadorCliente {
    private IRepositorioPessoa<Cliente> repoCliente;

    public GerenciadorCliente() {
        this.repoCliente = new RepositorioClienteArquivo();
    }

    public void cadastrarCliente(String nome, String cpf, String telefone, String idade, char sexo) throws EntidadeJaExisteException, IllegalArgumentException {
        if(repoCliente.buscarPorIdentificador(cpf) != null)
            throw new EntidadeJaExisteException("Já existe um cliente com esse cpf");
        Cliente c = new Cliente(nome, cpf, telefone, idade, sexo);
        repoCliente.adicionar(c);
    }

    public Cliente buscarCliente(String cpf) {
        return repoCliente.buscarPorIdentificador(cpf);
    }

    public List<Cliente> listar() {
        return repoCliente.listar();
    }

}
