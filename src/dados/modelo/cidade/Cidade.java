package dados.modelo.cidade;

import java.io.Serializable;

public class Cidade implements Serializable {
    private static final long serialVersionUID = 1000L;
    private String nome;

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
