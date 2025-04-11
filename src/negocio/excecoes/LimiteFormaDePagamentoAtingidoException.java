package negocio.excecoes;

public class LimiteFormaDePagamentoAtingidoException extends RuntimeException {
    private static final long serialVersionUID = 2004L;

    public LimiteFormaDePagamentoAtingidoException() {
        super("A quantidade máxima de formas de pagamento já foi atingida.");
    }
}
