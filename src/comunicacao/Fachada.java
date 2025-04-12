package comunicacao;

import dados.modelo.local.Local;
import dados.modelo.pagamento.FormaDePagamento;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.viagem.TipoViagem;
import dados.modelo.viagem.Viagem;
import negocio.gerenciador.GerenciadorCliente;
import negocio.gerenciador.GerenciadorMotorista;
import negocio.gerenciador.GerenciadorViagem;

import java.util.List;

public class Fachada {
    private GerenciadorCliente gerenciadorCliente;
    private GerenciadorMotorista gerenciadorMotorista;
    private GerenciadorViagem gerenciadorViagem;

    public Fachada() {
        this.gerenciadorCliente = new GerenciadorCliente();
        this.gerenciadorMotorista =  new GerenciadorMotorista();
        this.gerenciadorViagem = new GerenciadorViagem();
    }

    // Cliente
    public void cadastrarCliente(String cpf, String nome, String telefone, String idade, char sexo) {
        gerenciadorCliente.cadastrarCliente(cpf, nome, telefone, idade, sexo);
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

    public void avaliarCliente(Cliente c, double nota) {
        gerenciadorCliente.avaliarCliente(c, nota);
    }

    public void adicionarSaldo(String cpf, double saldo) {
        gerenciadorCliente.adicionarSaldo(cpf, saldo);
    }

    public boolean possuiPix(String cpf) {
        return gerenciadorCliente.possuiPix(cpf);
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

    public void avaliarMotorista(Motorista m, double nota) {
        gerenciadorMotorista.avaliarMotorista(m, nota);
    }

    public void alterarDisponibilidade(Motorista m) {
        gerenciadorMotorista.alterarDisponibilidade(m);
    }

    //Viagem
    public void solicitarViagem(Cliente c, Local origem, Local destino, TipoViagem tipo) {
        gerenciadorViagem.solicitarViagem(c, origem, destino, tipo);
    }

    public void aceitarViagem(Viagem v, Motorista m) {
        gerenciadorViagem.aceitarViagem(v, m);
    }

    public void processarPagamento(FormaDePagamento f, Viagem viagem) {
        gerenciadorViagem.processarPagamento(f, viagem);
    }

    public Viagem buscarViagemEmAndamentoPorMotorista(String cpfMotorista) {
        return gerenciadorViagem.buscarViagemEmAndamentoPorMotorista(cpfMotorista);
    }

    public Viagem buscarViagemConcluidaPorCliente(String cpfCliente) {
        return gerenciadorViagem.buscarViagemConcluidaPorCliente(cpfCliente);
    }

    public void finalizarViagem(Viagem viagem) {
        gerenciadorViagem.finalizarViagem(viagem);
    }

    public List<Viagem> listarViagensPendentes() {
        return gerenciadorViagem.listarViagensPendentes();
    }

}
