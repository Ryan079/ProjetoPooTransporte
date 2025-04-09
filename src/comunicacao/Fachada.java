package comunicacao;

import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import negocio.gerenciador.GerenciadorCliente;
import negocio.gerenciador.GerenciadorMotorista;

import java.util.List;

public class Fachada {
    private GerenciadorCliente controladorCliente;
    private GerenciadorMotorista controladorMotorista;

    public Fachada() {
        this.controladorCliente = new GerenciadorCliente();
        this.controladorMotorista =  new GerenciadorMotorista();
    }

    // Cliente
    public void cadastrarCliente(String nome, String cpf, String telefone, String idade, char sexo) {
        controladorCliente.cadastrarCliente(nome, cpf, telefone, idade, sexo);
    }

    public Cliente buscarCliente(String cpf) {
        return controladorCliente.buscarCliente(cpf);
    }

    public List<Cliente> listarClientes() {
        return controladorCliente.listar();
    }

    //Motorista
    public void cadastrarMotorista(String cpf, String nome, String telefone, String idade, char sexo, String cnh, String cor, TipoVeiculo tipo, String marca, String modelo, String placa) {
        controladorMotorista.cadastrarMotorista(cpf, nome, telefone, idade, sexo, cnh, cor, tipo, marca, modelo, placa);
    }

    public Motorista buscarMotorista(String cnh) {
        return controladorMotorista.buscarMotorista(cnh);
    }

    public List<Motorista> listarMotoristas() {
        return controladorMotorista.listar();
    }

}
