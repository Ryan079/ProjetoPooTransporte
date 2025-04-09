package dados.repositorio;

import dados.modelo.viagem.Viagem;

import java.util.List;

public interface IRepositorioViagem {
    void adicionar(Viagem viagem);
    List<Viagem> listar();
}
