package negocio.gerenciador;

import dados.modelo.pagamento.Dinheiro;
import dados.modelo.pagamento.FormaDePagamento;
import dados.modelo.pessoa.Cliente;
import dados.repositorio.IRepositorioPessoa;
import dados.repositorio.RepositorioClienteArquivo;
import negocio.excecoes.EntidadeJaExisteException;
import negocio.excecoes.EntidadeNaoExisteException;
import negocio.excecoes.EntradaInvalidaException;
import negocio.excecoes.LimiteFormaDePagamentoAtingidoException;
import negocio.gerenciador.utilitario.Validador;

import java.util.List;

public class GerenciadorCliente {
    private IRepositorioPessoa<Cliente> repoCliente;

    public GerenciadorCliente() {
        this.repoCliente = new RepositorioClienteArquivo();
    }

    public void cadastrarCliente(String cpf, String nome, String telefone, String idade, char sexo) throws EntidadeJaExisteException, IllegalArgumentException {
        if(repoCliente.buscarPorIdentificador(cpf) != null)
            throw new EntidadeJaExisteException("Já existe um cliente com esse cpf");
        Cliente c = new Cliente(cpf, nome, telefone, idade, sexo);

        //O cliente já é criado no sistema com a forma de pagamento dinheiro
        c.adicionarFormaDePagamento(new Dinheiro());

        repoCliente.adicionar(c);
    }

    public Cliente buscarCliente(String cpf) throws EntidadeNaoExisteException{
        Cliente c = repoCliente.buscarPorIdentificador(cpf);
        if(c == null)
            throw new EntidadeNaoExisteException("Não foi encontrado um cliente com esse cpf.");
        return c;
    }

    public List<Cliente> listar() {
        return repoCliente.listar();
    }

    //criado para ser chamado em outros gerenciadores
    public void atualizarCliente(Cliente clienteAtualizado) throws EntidadeNaoExisteException {
        Cliente c = repoCliente.buscarPorIdentificador(clienteAtualizado.getCpf());
        if(c == null)
            throw new EntidadeNaoExisteException("Cliente com o CPF " + clienteAtualizado.getCpf() + " não encontrado." );

        repoCliente.atualizar(clienteAtualizado);
    }

    public void avaliarCliente(Cliente c, double nota) throws EntradaInvalidaException{
        if(nota > 5 || nota < 1)
            throw new EntradaInvalidaException();
        c.setAvaliacao((c.getAvaliacao() + nota)/2);
        repoCliente.atualizar(c);
    }

    public boolean possuiPix(String cpf) {
        Cliente c = repoCliente.buscarPorIdentificador(cpf);
        return Validador.possuiPix(c);
    }

    public void adicionarFormaDePagamento(String cpf, FormaDePagamento forma) throws EntidadeNaoExisteException, LimiteFormaDePagamentoAtingidoException {
        Cliente c = repoCliente.buscarPorIdentificador(cpf);
        if(c == null)
            throw new EntidadeNaoExisteException("Cliente com o CPF " + cpf + " não encontrado.");
        if(c.getFormaDePagamentos().size() >= 3)
            throw new LimiteFormaDePagamentoAtingidoException();

        c.adicionarFormaDePagamento(forma);
        repoCliente.atualizar(c);
    }

    public void adicionarSaldo(String cpf, double valor) throws EntidadeNaoExisteException, EntradaInvalidaException{
        Cliente c = repoCliente.buscarPorIdentificador(cpf);
        if(c == null)
            throw new EntidadeNaoExisteException("Cliente com o CPF " + cpf + " não encontrado.");
        if(valor <= 0)
            throw new EntradaInvalidaException();
        c.creditarSaldo(valor);
        repoCliente.atualizar(c);

    }

}
