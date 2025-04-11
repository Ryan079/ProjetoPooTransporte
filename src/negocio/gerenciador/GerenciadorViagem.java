package negocio.gerenciador;

import dados.modelo.local.Local;
import dados.modelo.pagamento.CartaoCredito;
import dados.modelo.pagamento.Dinheiro;
import dados.modelo.pagamento.FormaDePagamento;
import dados.modelo.pagamento.Pix;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.viagem.TipoViagem;
import dados.modelo.viagem.Viagem;
import dados.repositorio.IRepositorioViagem;
import dados.repositorio.RepositorioViagemArquivo;
import negocio.excecoes.EntradaInvalidaException;
import negocio.excecoes.SaldoInsuficienteException;

public class GerenciadorViagem {
    private IRepositorioViagem repoViagem;
    private GerenciadorCliente gerenciadorCliente; //Gerenciador adicionado para poder salvar no arquivo as alterações no saldo do cliente

    public GerenciadorViagem() {
        this.repoViagem = new RepositorioViagemArquivo();
        this.gerenciadorCliente = new GerenciadorCliente();
    }

    public void criarViagem(Cliente c, Motorista m, Local origem, Local destino, TipoViagem tipo) throws EntradaInvalidaException {

        TipoVeiculo tipoVeiculo = m.getVeiculo().getTipo();

        double distancia = gerarDistanciaAleatoria();
        double valor = calcularValorCorrida(distancia, tipoVeiculo);

        //Não torna possível criar uma viagem de entrega usando uma motocicleta
        if(tipoVeiculo == TipoVeiculo.MOTOCICLETA && tipo == TipoViagem.ENTREGA)
            throw new EntradaInvalidaException();

        Viagem viagem = new Viagem(c, destino, distancia, m, origem, tipo, valor);
        repoViagem.adicionar(viagem);
    }

    public void processarPagamento(FormaDePagamento forma, Viagem viagem) throws SaldoInsuficienteException, EntradaInvalidaException {
        Cliente c = viagem.getCliente();
        double valor = viagem.getValor();

        if(forma instanceof Dinheiro)
            System.out.println("Pagamento realizado com sucesso.");

        else if(forma instanceof Pix) {
            if(c.getSaldo() < valor)
                throw new SaldoInsuficienteException();

            c.debitarSaldo(valor);
            gerenciadorCliente.atualizarCliente(c);

        } else if(forma instanceof CartaoCredito) {
            CartaoCredito cartao = (CartaoCredito) forma;
            if(cartao.getLimiteDisponivel() < valor)
                throw new SaldoInsuficienteException();

            cartao.debitar(valor);
            gerenciadorCliente.atualizarCliente(c);

        } else {
            throw new EntradaInvalidaException();
        }

    }

    public double gerarDistanciaAleatoria() {
        return (Math.random() * (3000 -  500 + 1)) + 500;//gera uma distância mínima de 500m a 3km
    }

    public double calcularValorCorrida(double distancia, TipoVeiculo tipoVeiculo) {
        double valorBase = 4.0;

        double tarifaPorKm = 0.40 + Math.random() * (2-00 - 0.40);
        double valor = valorBase + tarifaPorKm * (distancia/1000.0);

        switch(tipoVeiculo) {
            case MOTOCICLETA -> valor *= 0.70;
            case LUXO -> valor *= 1.25;
            case ECONOMICO -> valor *= 0.85;
        }
        return valor;
    }


}
