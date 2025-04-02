package dados.repositorio;

import dados.modelo.pessoa.Cliente;
import dados.modelo.veiculo.Veiculo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioVeiculoArquivo implements IRepositorioVeiculo {
    private final String arquivo = "veiculos.ser";
    private List<Veiculo> veiculos;

    public RepositorioVeiculoArquivo() {
        this.veiculos = carregar();
    }

    @Override
    public void adicionar(Veiculo veiculo) {
        veiculos.add(veiculo);
        salvar();
    }

    @Override
    public Veiculo buscarPorPlaca(String placa) {
        for(Veiculo v : veiculos)
            if(v.getPlaca().equals(placa))
                return v;
        return null;
    }

    @Override
    public List<Veiculo> listar() {
        return veiculos;
    }

    private List<Veiculo> carregar() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Veiculo>) in.readObject();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }

    private void salvar() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(veiculos);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
