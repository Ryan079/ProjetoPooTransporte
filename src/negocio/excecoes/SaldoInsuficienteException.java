package negocio.excecoes;

public class SaldoInsuficienteException extends RuntimeException {
  private static final long serialVersionUID = 2004L;

    public SaldoInsuficienteException() {
        super("Seu saldo é insuficiente para realizar o pagamento.");
    }
}
