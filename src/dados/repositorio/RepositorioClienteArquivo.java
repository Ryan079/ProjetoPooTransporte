package dados.repositorio;

import dados.modelo.pessoa.Motorista;
import dados.modelo.pessoa.Pessoa;
import dados.modelo.pessoa.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioClienteArquivo implements IRepositorioPessoa<Cliente>{
    private final String arquivo = "clientes.ser";
    private List<Cliente> clientes;

    public RepositorioClienteArquivo() {
        this.clientes = carregar();
    }

    @Override
    public void adicionar(Cliente pessoa) {
        clientes.add(pessoa);
        salvar();
    }

    @Override
    public Cliente buscarPorIdentificador(String identificador) {
        for(Cliente c : clientes)
            if(c.getCpf().equals(identificador))
                return c;
        return null;
    }

    @Override
    public List<Cliente> listar() {
        return clientes;
    }

    private List<Cliente> carregar() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Cliente>) in.readObject();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }

    private void salvar() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(clientes);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
