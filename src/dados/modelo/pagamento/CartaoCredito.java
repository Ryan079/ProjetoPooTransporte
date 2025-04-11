package dados.modelo.pagamento;

public class CartaoCredito extends FormaDePagamento{

    private String numeroCartao;
    private String nomeTitular;
    private String ccv;
    private double limiteMaximo;
    private double limiteDisponivel;

    public CartaoCredito(String ccv, double limiteMaximo, String nomeTitular, String numeroCartao) {
        this.ccv = ccv;
        this.limiteDisponivel = limiteMaximo;
        this.limiteMaximo = limiteMaximo;
        this.nomeTitular = nomeTitular;
        this.numeroCartao = numeroCartao;
    }

    public void debitar(double valor) {
        this.limiteDisponivel -= valor;
    }

    public double getLimiteDisponivel() {
        return limiteDisponivel;
    }

    public double getLimiteMaximo() {
        return limiteMaximo;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "=== Cartão de Crédito ===\n" +
                "Número: " + numeroCartao + "\n" +
                "CCV: " + ccv + "\n" +
                "Nome do Titular: " + nomeTitular + "\n" +
                "Limite Máximo: R$ " + String.format("%.2f", limiteMaximo) + "\n" +
                "Limite Disponível: R$ " + String.format("%.2f", limiteDisponivel);
    }
}
