package dados.repositorio;

import java.util.List;
import dados.modelo.cidade.Cidade;

public interface IRepositorioCidade {
    void adicionar(Cidade cidade);
    Cidade buscarPorNome(String nome);
    List<Cidade> listar();

}
