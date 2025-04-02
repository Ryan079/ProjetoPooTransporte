package dados.repositorio;

import dados.modelo.pessoa.Pessoa;

import java.util.List;

public interface IRepositorioPessoa<T extends Pessoa>{
    void adicionar(T pessoa);
    T buscarPorIdentificador(String identificador);
    List<T> listar();
}
