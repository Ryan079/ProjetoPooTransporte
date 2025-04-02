package dados.repositorio;

import dados.modelo.cidade.Cidade;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioCidadeArquivo implements IRepositorioCidade{
    private final String arquivo = "cidades.ser";

    @Override
    public void adicionar(Cidade cidade) {
        List<Cidade> cidades = listar();
        cidades.add(cidade);
        salvar(cidades);
    }

    @Override
    public Cidade buscarPorNome(String nome) {
        for(Cidade c : listar())
            if(c.getNome().equals(nome))
                return c;
        return null;
    }

    @Override
    public List<Cidade> listar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Cidade>) in.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private void salvar(List<Cidade> cidades) {
        try (ObjectOutput out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(cidades);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
