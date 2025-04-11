package dados.modelo.viagem;

import dados.modelo.local.Local;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.Veiculo;

import java.io.Serializable;

public class Viagem implements Serializable {

    private static final long serialVersionUID = 1004L;
    private Motorista motorista;
    private Cliente cliente;
    private Local origem;
    private Local destino;
    private double valor;
    private double distancia;
    private TipoViagem tipo;

    public Viagem(Cliente cliente, Local destino, double distancia, Motorista motorista, Local origem, TipoViagem tipo, double valor) {
        this.cliente = cliente;
        this.destino = destino;
        this.distancia = distancia;
        this.motorista = motorista;
        this.origem = origem;
        this.tipo = tipo;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Local getDestino() {
        return destino;
    }

    public TipoViagem getTipo() {
        return tipo;
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

    @Override
    public String toString() {
        return "=== Viagem ===\n" +
                "Cliente: " + cliente.getNome() + "\n" +
                "Motorista: " + motorista.getNome() + "\n" +
                "Origem: " + origem.getEndereco() + "\n" +
                "Destino: " + destino.getEndereco() + "\n" +
                "Distância: " + String.format("%.2f", distancia / 1000) + " km\n" +
                "Valor: R$" + String.format("%.2f", valor) + "\n" +
                "Tipo: " + tipo + "\n";
    }
}

