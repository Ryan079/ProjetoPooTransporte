package dados.repositorio;

import dados.modelo.viagem.Viagem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioViagemArquivo implements IRepositorioViagem{
    private final String arquivo = "viagens.sr";
    private List<Viagem> viagens;

    public RepositorioViagemArquivo() {
        this.viagens = carregar();
    }

    @Override
    public void adicionar(Viagem viagem) {
        viagens.add(viagem);
        salvar();
    }

    @Override
    public void atualizar(Viagem viagem) {
        salvar();
    }

    @Override
    public List<Viagem> listar() {
        return viagens;
    }

    private List<Viagem> carregar() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Viagem>) in.readObject();
        } catch(Exception e) {
            return new ArrayList<>();
        }
    }

    private void salvar() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            out.writeObject(viagens);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
