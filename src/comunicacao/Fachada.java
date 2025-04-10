package comunicacao;

import dados.modelo.pagamento.FormaDePagamento;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import negocio.gerenciador.GerenciadorCliente;
import negocio.gerenciador.GerenciadorMotorista;

import java.util.List;

public class Fachada {
    private GerenciadorCliente gerenciadorCliente;
    private GerenciadorMotorista gerenciadorMotorista;

    public Fachada() {
        this.gerenciadorCliente = new GerenciadorCliente();
        this.gerenciadorMotorista =  new GerenciadorMotorista();
    }

    // Cliente
    public void cadastrarCliente(String nome, String cpf, String telefone, String idade, char sexo) {
        gerenciadorCliente.cadastrarCliente(nome, cpf, telefone, idade, sexo);
    }

    public Cliente buscarCliente(String cpf) {
        return gerenciadorCliente.buscarCliente(cpf);
    }

    public List<Cliente> listarClientes() {
        return gerenciadorCliente.listar();
    }

    public void adicionarFormaDePagamento(String cpf, FormaDePagamento forma) {
        gerenciadorCliente.adicionarFormaDePagamento(cpf, forma);
    }

    public void adicionarSaldo(String cpf, double saldo) {
        gerenciadorCliente.adicionarSaldo(cpf, saldo);
    }

    //Motorista
    public void cadastrarMotorista(String cpf, String nome, String telefone, String idade, char sexo, String cnh, String cor, TipoVeiculo tipo, String marca, String modelo, String placa) {
        gerenciadorMotorista.cadastrarMotorista(cpf, nome, telefone, idade, sexo, cnh, cor, tipo, marca, modelo, placa);
    }

    public Motorista buscarMotorista(String cnh) {
        return gerenciadorMotorista.buscarMotorista(cnh);
    }

    public List<Motorista> listarMotoristas() {
        return gerenciadorMotorista.listar();
    }

}
