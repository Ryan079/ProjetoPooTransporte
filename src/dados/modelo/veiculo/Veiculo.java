package dados.modelo.veiculo;

import java.io.Serializable;

public  class Veiculo implements Serializable {
    private static final long serialVersionUID = 1004L;
    private String placa;
    private TipoVeiculo tipo;
    private String marca;
    private String modelo;
    private String cor;
    private int qtdPassageiro;

    public Veiculo(String cor, TipoVeiculo tipo, String marca, String modelo, String placa) {
        this.cor = cor;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.qtdPassageiro = tipo.getQtdPassageiro();
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
