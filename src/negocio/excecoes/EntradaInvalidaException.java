package negocio.excecoes;

public class EntradaInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 2003L;

    public EntradaInvalidaException() {
        super("Entrada inválida, insira uma entrada válida.");
    }
}
