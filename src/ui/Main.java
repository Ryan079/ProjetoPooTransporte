package ui;

import comunicacao.Fachada;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import negocio.excecoes.EntidadeJaExisteException;
import ui.utilitario.Cadastro;

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
                try {
                    Cadastro.cadastrarCliente(fachada, input);
                    System.out.println("Cliente cadastrado com sucesso.");
                } catch(EntidadeJaExisteException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro Inesperado: " + e.getMessage());
                }

                //Motorista
                try {
                    Cadastro.cadastrarMotorista(fachada, input);
                    System.out.println("Motorista cadastrado com sucesso.");
                } catch (EntidadeJaExisteException e) {
                    System.out.println("Erro: " + e.getMessage());
                } catch (Exception e) {
                    System.out.println("Erro Inesperado: " + e.getMessage());
                }
            } else
                break;
        }

        //Sistema com Cliente e Motorista ativos
        while(true) {
            System.out.println("======= MENU DO APLICATIVO =======");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Motorista");
            System.out.println("3. Entrar como Cliente");
            System.out.println("4. Entrar como Motorista");
            System.out.println("0. Sair");

            int opcao = Integer.parseInt(input.nextLine());

            try{
                switch(opcao) {
                    case 1:
                        System.out.println("Nome: ");
                        String nome = input.nextLine();
                        System.out.println("CPF: ");
                        String cpf = input.nextLine();
                        System.out.println("Telefone: ");
                        String telefone = input.nextLine();
                        System.out.println("Idade: ");
                        String idade = input.nextLine();
                        System.out.println("Sexo (M/F): ");
                        char sexo = input.next().charAt(0);
                        input.nextLine();
                        fachada.cadastrarCliente(nome, cpf, telefone, idade, sexo);
                        System.out.println("Cliente cadastrado com sucesso.");
                        break;
                    case 2:
                        System.out.println("Nome: ");
                        String nome2 = input.nextLine();
                        System.out.println("Cpf: ");
                        String cpf2 = input.nextLine();
                        System.out.println("Telefone: ");
                        String telefone2 = input.nextLine();
                        System.out.println("Idade: ");
                        String idade2 = input.nextLine();
                        System.out.println("Sexo (M/F): ");
                        char sexo2 = input.next().charAt(0);
                        input.nextLine();
                        System.out.println("CNH: ");
                        String cnh = input.nextLine();
                        System.out.println("Placa: ");
                        String placa = input.nextLine();
                        TipoVeiculo t = null;
                        while(t == null) {
                            System.out.println("Tipo de Veiculo");
                            for(TipoVeiculo tipo : TipoVeiculo.values())
                                System.out.println(tipo);
                            String tipoEntrada = input.nextLine().toUpperCase();
                            try{
                                t = TipoVeiculo.valueOf(tipoEntrada);
                            } catch (IllegalArgumentException e) {
                                System.out.println("Erro de entrada válida: " + e.getMessage());
                            }
                        }
                        System.out.println("Marca: ");
                        String marca = input.nextLine();
                        System.out.println("Modelo: ");
                        String modelo = input.nextLine();
                        System.out.println("Cor: ");
                        String cor = input.nextLine();
                        fachada.cadastrarMotorista(cpf2, nome2, telefone2, idade2, sexo2, cnh, cor, t, marca, modelo, placa);
                        System.out.println("Motorista cadastrado com sucesso.");
                        break;
                    case 0:
                        return;
                }
            } catch (EntidadeJaExisteException e) {
                System.out.println("Erro de cadastro: "+ e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Erro de execução: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
