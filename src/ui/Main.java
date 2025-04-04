package ui;

import comunicacao.Fachada;
import dados.modelo.cidade.Cidade;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.veiculo.Veiculo;
import negocio.excecoes.EntidadeJaExisteException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Fachada fachada = new Fachada();
        Scanner input = new Scanner(System.in);

        //Loop para garantir que há motorista e cliente no sistema
        while(true) {
            List<Motorista> m = fachada.listarMotoristas();
            List<Cliente> c = fachada.listarClientes();
            if(m.isEmpty() && c.isEmpty()) {
                System.out.println("Não há usuários cadastrados no sistema.");
                System.out.println("Cadastre um Cliente e um Motorista.");
                //Cliente
                System.out.println("----Cliente----");
                System.out.println("Nome: ");
                String nome = input.nextLine();
                System.out.println("CPF: ");
                String cpf = input.nextLine();
                System.out.println("Telefone: ");
                String telefone = input.nextLine();
                System.out.println("Sexo (M/F): ");
                char sexo = input.next().charAt(0);
                try {
                    fachada.cadastrarCliente(nome, cpf, telefone, sexo);
                    System.out.println("Cliente cadastrado com sucesso.");
                } catch(EntidadeJaExisteException e) {
                    System.out.println("Erro de Cliente já existente no sistema: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
                //Limpar buffer
                input.nextLine();
                //Motorista
                System.out.println("----Motorista----");
                System.out.println("Nome: ");
                nome = input.nextLine();
                System.out.println("CPF: ");
                cpf = input.nextLine();
                System.out.println("Telefone: ");
                telefone = input.nextLine();
                System.out.println("Sexo (M/F): ");
                sexo = input.next().charAt(0);
                //Limpar buffer
                input.nextLine();
                System.out.println("CNH: ");
                String cnh = input.nextLine();
                //Veículo
                System.out.println("Placa: ");
                String placa = input.nextLine();

                TipoVeiculo t = null;
                while(t == null) {
                    System.out.println("Tipo de Veiculo");
                    for (TipoVeiculo tipo : TipoVeiculo.values())
                        System.out.println(tipo);
                    String tipoEntrada = input.nextLine().toUpperCase();
                    try {
                        t = TipoVeiculo.valueOf(tipoEntrada);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro de entrada inválida: " + e.getMessage());
                    }
                }
                System.out.println("Marca: ");
                String marca = input.nextLine();
                System.out.println("Modelo: ");
                String modelo = input.nextLine();
                System.out.println("Cor: ");
                String cor = input.nextLine();

                try {
                    fachada.cadastrarMotorista(cpf, nome, telefone, sexo, cnh, cor, t, marca, modelo, placa);
                    System.out.println("Motorista cadastrado com sucesso.");
                } catch (EntidadeJaExisteException e) {
                    System.out.println("Erro de Motorista ou Veículo já existente: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro: " + e.getMessage());
                }
            } else
                break;
        }


        Cliente clienteAtual = null;
        Motorista motoristaAtual = null;
        //Sistema com Cliente e Motorista ativos
        while(true) {

            System.out.println("======= MENU DO APLICATIVO =======");

        }

    }
}
