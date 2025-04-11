package negocio.excecoes;

public class AlteracaoInvalidaException extends RuntimeException {
  private static final long serialVersionUID = 2000L;

    public AlteracaoInvalidaException(String message) {
        super(message);
    }
}
