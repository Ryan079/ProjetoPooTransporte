package dados.modelo.viagem;

import dados.modelo.local.Local;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;

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
    private StatusViagem status;

    public Viagem(Cliente cliente, Local destino, double distancia, Local origem, TipoViagem tipo) {
        this.cliente = cliente;
        this.destino = destino;
        this.distancia = distancia;
        this.motorista = null;
        this.origem = origem;
        this.tipo = tipo;
        this.valor = 0.0;
        this.status = StatusViagem.PENDENTE;
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

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public Local getOrigem() {
        return origem;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public StatusViagem getStatus() {
        return status;
    }

    public void setStatus(StatusViagem status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "=== Viagem ===\n" +
                "Cliente: " + cliente.getNome() + "\n" +
                "Motorista: " + (motorista != null ? motorista.getNome() : "Aguardando motorista") + "\n" +
                "Origem: " + origem.getEndereco() + "\n" +
                "Destino: " + destino.getEndereco() + "\n" +
                "Distância: " + String.format("%.2f", distancia / 1000) + " km\n" +
                "Valor: R$" + (valor > 0 ? "R$" + String.format("%.2f", valor) : "A ser definido") + "\n" +
                "Tipo: " + tipo + "\n" +
                "Status: " + status;
    }
}

