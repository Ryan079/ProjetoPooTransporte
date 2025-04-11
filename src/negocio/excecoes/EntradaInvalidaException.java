package negocio.excecoes;

public class EntradaInvalidaException extends RuntimeException {
    private static final long serialVersionUID = 2002L;

    public EntradaInvalidaException() {
        super("Entrada inválida, insira uma entrada válida.");
    }
}
