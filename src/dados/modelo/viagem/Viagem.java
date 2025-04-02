package dados.modelo.viagem;

import dados.modelo.cidade.Cidade;
import dados.modelo.local.Local;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.Veiculo;

import java.io.Serializable;

public abstract class Viagem implements Serializable {

    private static final long serialVersionUID = 1005L;
    protected Motorista motorista;
    protected Veiculo veiculo;
    protected Local origem;
    protected Local destino;
    protected double valor;
    protected double distancia;

    public Local getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Local getOrigem() {
        return origem;
    }

    public double getValor() {
        return valor;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
}

