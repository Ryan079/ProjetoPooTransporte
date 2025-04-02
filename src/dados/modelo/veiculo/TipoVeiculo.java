package dados.modelo.veiculo;

public enum TipoVeiculo {
    ECONOMICO(4), SUV(4), LUXO(4), MOTOCICLETA(2);

    private final int qtdPassageiro;

    private TipoVeiculo(int qtdPassageiro) {
        this.qtdPassageiro = qtdPassageiro;
    }

    public int getQtdPassageiro() {
        return qtdPassageiro;
    }
}
