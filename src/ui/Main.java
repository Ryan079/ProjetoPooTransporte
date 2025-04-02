package ui;

import comunicacao.Fachada;
import dados.modelo.cidade.Cidade;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import dados.modelo.veiculo.Veiculo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Fachada fachada = new Fachada();
        Scanner input = new Scanner(System.in);

        //Loop para garantir que há pelo menos um usuário no sistema, seja motorista ou cliente
        while(true) {
            List<Motorista> m = fachada.listarMotoristas();
            List<Cliente> c = fachada.listarClientes();
            if(m.isEmpty() && c.isEmpty()) {
                System.out.println("Não há nenhum usuário cadastrado no sistema");
                int opcao = 0;
                boolean entradaValida = false;
                while(!entradaValida) {
                    try {
                        System.out.println("Escolha se deseja cadastrar usuário ou motorista");
                        System.out.println("1 - Cliente");
                        System.out.println("2 - Motorista");
                        System.out.println("Insira sua escolha:");

                        opcao = input.nextInt();
                        input.nextLine();

                        if(opcao == 1 || opcao == 2)
                            entradaValida = true;
                        else
                            System.out.println("Opção inválida, Insira 1 para cliente e 2 para motorista");
                    } catch(InputMismatchException e) {
                        System.out.println("Entrada inválida");
                        input.nextLine();
                    }
                }

                //Cadastro do Cliente
                if(opcao == 1) {
                    try {
                        System.out.println("Nome do Cliente: ");
                        String nome = input.nextLine();
                        System.out.println("CPF: ");
                        String cpf = input.nextLine();
                        System.out.println("Telefone: ");
                        String telefone = input.nextLine();
                        System.out.println("Sexo (M/F): ");
                        char sexo = input.next().charAt(0);

                        fachada.cadastrarCliente(nome, cpf, telefone, sexo);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch(Exception e) {
                        System.out.println("Erro ao cadastrar cliente" + e.getMessage());
                    }
                //Cadastro do Motorista
                } else if(opcao == 2) {
                    try {
                        //Motorista
                        System.out.println("Nome do Motorista: ");
                        String nome = input.nextLine();
                        System.out.println("CPF: ");
                        String cpf = input.nextLine();
                        System.out.println("Telefone: ");
                        String telefone = input.nextLine();
                        System.out.println("Sexo (M/F): ");
                        char sexo = input.next().charAt(0);
                        System.out.println("CNH: ");
                        String cnh = input.nextLine();
                        //Veículo
                        System.out.println("Placa do veículo: ");
                        String placa = input.nextLine();
                        System.out.println("Qual o tipo de veículo?");
                        for(TipoVeiculo t : TipoVeiculo.values())
                            System.out.println("- " + t.name());
                        System.out.println("Insira: ");
                        String tipoEntrada = input.nextLine();
                        TipoVeiculo tipo = TipoVeiculo.valueOf(tipoEntrada);
                        System.out.println("Marca: ");
                        String marca = input.nextLine();
                        System.out.println("Cor: ");
                        String cor = input.nextLine();
                        //Instância do Veículo
                        Veiculo v = new Veiculo(cor, tipo, marca, placa, tipo.getQtdPassageiro());

                        fachada.cadastrarMotorista(cpf, nome, telefone, sexo, cnh, v);
                        System.out.println("Motorista cadastrado com sucesso!");
                    } catch(Exception e) {
                        System.out.println("Erro ao cadastrar motorista" + e.getMessage());
                    }
                }
            } else
                break;
        }

    }
}
