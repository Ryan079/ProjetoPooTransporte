package negocio.gerenciador;

import dados.modelo.local.Local;
import dados.modelo.pagamento.CartaoCredito;
import dados.modelo.pagamento.Dinheiro;
import dados.modelo.pagamento.FormaDePagamento;
import dados.modelo.pagamento.Pix;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.viagem.StatusViagem;
import dados.modelo.viagem.TipoViagem;
import dados.modelo.viagem.Viagem;
import dados.repositorio.IRepositorioViagem;
import dados.repositorio.RepositorioViagemArquivo;
import negocio.excecoes.AlteracaoInvalidaException;
import negocio.excecoes.EntradaInvalidaException;
import negocio.excecoes.SaldoInsuficienteException;

import java.util.List;

public class GerenciadorViagem {
    private IRepositorioViagem repoViagem;
    private GerenciadorCliente gerenciadorCliente; //Gerenciador adicionado para poder salvar no arquivo as alterações no saldo do cliente

    public GerenciadorViagem() {
        this.repoViagem = new RepositorioViagemArquivo();
        this.gerenciadorCliente = new GerenciadorCliente();
    }

    public void solicitarViagem(Cliente c, Local origem, Local destino, TipoViagem tipo) {

        double distancia = gerarDistanciaAleatoria();

        Viagem viagem = new Viagem(c, destino, distancia, origem, tipo);
        repoViagem.adicionar(viagem);
    }

    public void aceitarViagem(Viagem viagem, Motorista m) throws AlteracaoInvalidaException, EntradaInvalidaException {
        if(viagem.getStatus() != StatusViagem.PENDENTE)
            throw new AlteracaoInvalidaException("A viagem não pode ser aceita nesse estado");

        TipoVeiculo tipo = m.getVeiculo().getTipo();

        if (tipo == TipoVeiculo.MOTOCICLETA && viagem.getTipo() == TipoViagem.ENTREGA)
            throw new EntradaInvalidaException();

        double valor = calcularValorCorrida(viagem.getDistancia(), tipo);

        viagem.setMotorista(m);
        viagem.setValor(valor);

        confirmarViagem(viagem);
        repoViagem.atualizar(viagem);
    }

    public List<Viagem> listarViagensPendentes() {
        return repoViagem.listarPendentes();
    }

    public Viagem buscarViagemEmAndamentoPorMotorista(String cpfMotorista) {
        for (Viagem v : this.repoViagem.listar()) {
            if (v.getMotorista() != null &&
                    v.getMotorista().getCpf().equals(cpfMotorista) &&
                    v.getStatus() == StatusViagem.EM_ANDAMENTO) {
                return v;
            }
        }
        return null;
    }

    public Viagem buscarViagemEmAndamentoPorCliente(String cpfCliente) {
        for (Viagem v : this.repoViagem.listar()) {
            if (v.getCliente().getCpf().equals(cpfCliente) &&
                    v.getStatus() == StatusViagem.EM_ANDAMENTO) {
                return v;
            }
        }
        return null;
    }

    public void processarPagamento(FormaDePagamento forma, Viagem viagem) throws SaldoInsuficienteException, EntradaInvalidaException {
        Cliente c = viagem.getCliente();
        double valor = viagem.getValor();

        if(forma instanceof Dinheiro) {
            pagarViagem(viagem);
            gerenciadorCliente.atualizarCliente(c);
        }
        else if(forma instanceof Pix) {
            if(c.getSaldo() < valor)
                throw new SaldoInsuficienteException();

            c.debitarSaldo(valor);
            pagarViagem(viagem);
            gerenciadorCliente.atualizarCliente(c);

        } else if(forma instanceof CartaoCredito) {
            CartaoCredito cartao = (CartaoCredito) forma;
            if(cartao.getLimiteDisponivel() < valor)
                throw new SaldoInsuficienteException();

            cartao.debitar(valor);
            pagarViagem(viagem);
            gerenciadorCliente.atualizarCliente(c);

        } else
            throw new EntradaInvalidaException();
    }

    public void confirmarViagem(Viagem viagem) throws AlteracaoInvalidaException {
        if(viagem.getStatus() != StatusViagem.PENDENTE)
            throw new AlteracaoInvalidaException("A viagem não pode ser aceita nesse estado.");

        viagem.setStatus(StatusViagem.EM_ANDAMENTO);
    }

    public void finalizarViagem(Viagem viagem) throws AlteracaoInvalidaException {
        if(viagem.getStatus() != StatusViagem.EM_ANDAMENTO)
            throw new AlteracaoInvalidaException("A viagem precisa estar em andamento para ser finalizada.");

        viagem.setStatus(StatusViagem.CONCLUIDA);
        repoViagem.atualizar(viagem);
    }

    public void pagarViagem(Viagem viagem) throws AlteracaoInvalidaException {
        if(viagem.getStatus() != StatusViagem.CONCLUIDA)
            throw new AlteracaoInvalidaException("A viagem não pode ser paga enquanto não estiver concluída.");

        viagem.setStatus(StatusViagem.PAGA);
    }

    public double gerarDistanciaAleatoria() {
        return (Math.random() * (3000 -  500 + 1)) + 500;//gera uma distância mínima de 500m a 3km
    }

    public double calcularValorCorrida(double distancia, TipoVeiculo tipoVeiculo) {
        double valorBase = 4.0;

        double tarifaPorKm = 0.40 + Math.random() * (2.00 - 0.40);
        double valor = valorBase + tarifaPorKm * (distancia/1000.0);

        switch(tipoVeiculo) {
            case MOTOCICLETA -> valor *= 0.70;
            case LUXO -> valor *= 1.25;
            case ECONOMICO -> valor *= 0.85;
        }
        return valor;
    }


}
