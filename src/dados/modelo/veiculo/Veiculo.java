package dados.modelo.veiculo;

import java.io.Serializable;

public  class Veiculo implements Serializable {
    private static final long serialVersionUID = 1004L;
    protected String placa;
    protected TipoVeiculo tipo;
    protected String marca;
    protected String cor;
    protected int qtdPassageiro;

    public Veiculo(String cor, TipoVeiculo tipo, String marca, String placa, int qtdPassageiro) {
        this.cor = cor;
        this.tipo = tipo;
        this.marca = marca;
        this.placa = placa;
        this.qtdPassageiro = qtdPassageiro;
    }

    public String getCor() {
        return cor;
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public int getQtdPassageiro() {
        return qtdPassageiro;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "cor='" + cor + '\'' +
                ", placa='" + placa + '\'' +
                ", tipo=" + tipo +
                ", marca='" + marca + '\'' +
                ", qtdPassageiro=" + qtdPassageiro +
                '}';
    }
}
