package dados.repositorio;

import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioMotoristaArquivo implements IRepositorioPessoa<Motorista>{
    private final String arquivo = "motoristas.ser";
    private List<Motorista> motoristas;

    public RepositorioMotoristaArquivo() {
        this.motoristas = carregar();
    }
    @Override
    public void adicionar(Motorista motorista) {
        motoristas.add(motorista);
        salvar();
    }

    @Override
    public void atualizar(Motorista pessoa) {
        salvar();
    }

    //O método aceita tanto o cpf quanto a cnh do motorista
    @Override
    public Motorista buscarPorIdentificador(String identificador) {
        for(Motorista m : motoristas)
            if(m.getCpf().equals(identificador) || m.getCnh().equals(identificador))
                return m;
        return null;
    }

    @Override
    public List<Motorista> listar() {
        return motoristas;
    }

    private List<Motorista> carregar() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Motorista>) in.readObject();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }

    private void salvar() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(motoristas);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
