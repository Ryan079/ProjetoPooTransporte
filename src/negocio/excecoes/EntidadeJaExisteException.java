package negocio.excecoes;

public class EntidadeJaExisteException extends RuntimeException {
  private static final long serialVersionUID = 2000L;

    public EntidadeJaExisteException(String message) {
        super(message);
    }
}
