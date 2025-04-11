package ui.utilitario;

import comunicacao.Fachada;
import dados.modelo.veiculo.TipoVeiculo;
import negocio.excecoes.EntidadeJaExisteException;
import negocio.excecoes.EntradaInvalidaException;

import java.util.Scanner;

public class Cadastro {
    public static void cadastrarCliente(Fachada fachada, Scanner input) throws EntidadeJaExisteException {
        System.out.println("Nome: ");
        String nome = input.nextLine().trim();
        System.out.println("CPF: ");
        String cpf = input.nextLine().trim();
        System.out.println("Telefone: ");
        String telefone = input.nextLine().trim();
        System.out.println("Idade: ");
        String idade = input.nextLine().trim();
        System.out.println("Sexo (M/F): ");
        char sexo = input.next().charAt(0);
        input.nextLine(); //Limpa buffer

        fachada.cadastrarCliente(cpf, nome, telefone, idade, sexo);
    }

    public static void cadastrarMotorista(Fachada fachada, Scanner input) throws EntidadeJaExisteException {
        System.out.println("Nome: ");
        String nome = input.nextLine();
        System.out.println("CPF: ");
        String cpf = input.nextLine().trim();
        System.out.println("Telefone: ");
        String telefone = input.nextLine();
        System.out.println("Idade: ");
        String idade = input.nextLine();
        System.out.println("Sexo (M/F): ");
        char sexo = input.next().charAt(0);
        input.nextLine(); // limpar buffer

        System.out.println("CNH: ");
        String cnh = input.nextLine().trim();
        System.out.println("Placa: ");
        String placa = input.nextLine().toUpperCase();

        TipoVeiculo tipo = null;
        while (tipo == null) {
            System.out.println("Tipo de Veiculo:");
            for (TipoVeiculo t : TipoVeiculo.values()) {
                System.out.println(t);
            }
            String tipoEntrada = input.nextLine().toUpperCase();
            try {
                tipo = TipoVeiculo.valueOf(tipoEntrada);
            } catch (EntradaInvalidaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }

        System.out.println("Marca: ");
        String marca = input.nextLine();
        System.out.println("Modelo: ");
        String modelo = input.nextLine();
        System.out.println("Cor: ");
        String cor = input.nextLine();

        fachada.cadastrarMotorista(cpf, nome, telefone, idade, sexo, cnh, cor, tipo, marca, modelo, placa);
    }
}
