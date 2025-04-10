package negocio.excecoes;

public class EntidadeNaoExisteException extends RuntimeException {
    private static final long serialVersionUID = 2001L;

    public EntidadeNaoExisteException(String message) {
        super(message);
    }
}
