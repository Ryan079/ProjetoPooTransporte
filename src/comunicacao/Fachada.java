package comunicacao;

import dados.modelo.cidade.Cidade;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.veiculo.Veiculo;
import negocio.gerenciador.GerenciadorCidade;
import negocio.gerenciador.GerenciadorCliente;
import negocio.gerenciador.GerenciadorMotorista;
import negocio.excecoes.EntidadeJaExisteException;

import java.util.List;

public class Fachada {
    private GerenciadorCidade controladorCidade;
    private GerenciadorCliente controladorCliente;
    private GerenciadorMotorista controladorMotorista;

    public Fachada() {
        this.controladorCidade = new GerenciadorCidade();
        this.controladorCliente = new GerenciadorCliente();
        this.controladorMotorista =  new GerenciadorMotorista();
    }

    // Cliente
    public void cadastrarCliente(String nome, String cpf, String telefone, char sexo) {
        controladorCliente.cadastrarCliente(nome, cpf, telefone, sexo);
    }

    public Cliente buscarCliente(String cpf) {
        return controladorCliente.buscarCliente(cpf);
    }

    public List<Cliente> listarClientes() {
        return controladorCliente.listar();
    }

    //Motorista
    public void cadastrarMotorista(String cpf, String nome, String telefone, char sexo, String cnh, String cor, TipoVeiculo tipo, String marca, String modelo, String placa) {
        controladorMotorista.cadastrarMotorista(cpf, nome, telefone, sexo, cnh, cor, tipo, marca, modelo, placa);
    }

    public Motorista buscarMotorista(String cnh) {
        return controladorMotorista.buscarMotorista(cnh);
    }

    public List<Motorista> listarMotoristas() {
        return controladorMotorista.listar();
    }

    //Cidade
    public void cadastrarCidade(String nome) throws EntidadeJaExisteException {
        controladorCidade.cadastrarCidade(nome);
    }

    public List<Cidade> listarCidades() {
        return controladorCidade.listar();
    }

}
