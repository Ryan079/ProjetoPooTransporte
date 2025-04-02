package dados.repositorio;

import dados.modelo.veiculo.Veiculo;

import java.util.List;

//Repositório criado para verificar e evitar veículos repetidos
public interface IRepositorioVeiculo {
    void adicionar(Veiculo veiculo);
    Veiculo buscarPorPlaca(String placa);
    List<Veiculo> listar();
}
