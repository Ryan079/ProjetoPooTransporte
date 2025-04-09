package dados.modelo.viagem;

import dados.modelo.local.Local;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.Veiculo;

import java.io.Serializable;

public abstract class Viagem implements Serializable {

    private static final long serialVersionUID = 1005L;
    private Motorista motorista;
    private Veiculo veiculo;
    private Cliente cliente;
    private Local origem;
    private Local destino;
    private double valor;
    private double distancia;
    private TipoViagem tipo;

    public Viagem(Cliente cliente, Local destino, double distancia, Motorista motorista, Local origem, TipoViagem tipo, double valor, Veiculo veiculo) {
        this.cliente = cliente;
        this.destino = destino;
        this.distancia = (Math.random() * (3000 -  500 + 1)) + 500; //gera uma distância mínima de 500m a 3km
        this.motorista = motorista;
        this.origem = origem;
        this.tipo = tipo;
        this.valor = 4 + (0.40 + Math.random() * (2.00 - 0.40)) * (this.distancia / 1000); //valor de corrida mínimo de 4 reais com a soma de algum valor aleatório de 40 centavos a 2 reais por km
        this.veiculo = veiculo;
    }

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

