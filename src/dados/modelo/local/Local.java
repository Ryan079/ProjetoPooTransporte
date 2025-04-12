package dados.modelo.local;

import java.io.Serializable;

public class Local implements Serializable {

    private static final long serialVersionUID = 1000L;
    private String endereco;

    public Local(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }
}
