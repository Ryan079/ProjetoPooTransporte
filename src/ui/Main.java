package ui;

import comunicacao.Fachada;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import dados.modelo.veiculo.TipoVeiculo;
import negocio.excecoes.EntidadeJaExisteException;
import negocio.excecoes.EntidadeNaoExisteException;
import negocio.excecoes.EntradaInvalidaException;
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
            if(m.isEmpty()) {
                System.out.println("Não há motoristas cadastrados no sistema.");
                System.out.println("Cadastre um para que o sistema possa funcionar adequadamente.");
                try {
                    Cadastro.cadastrarMotorista(fachada, input);
                    System.out.println("Motorista cadastrado com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            } else if(c.isEmpty()) {
                System.out.println("Não há clientes cadastrados no sistema.");
                System.out.println("Cadastre um para que o sistema possa funcionar adequadamente.");
                try {
                    Cadastro.cadastrarCliente(fachada, input);
                    System.out.println("Cliente cadastrado com sucesso.");
                } catch (Exception e) {
                    System.out.println("Erro inesperado: " + e.getMessage());
                }
            } else
                break;
        }

        //Sistema com Cliente e Motorista ativos
        while(true) {
            System.out.println("======= MENU DO APLICATIVO =======");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Cadastrar Motorista");
            //Opções de listar no menu para facilitar na hora de visualizar o funcionamento
            System.out.println("3. Listar Clientes");
            System.out.println("4. Listar Motoristas");
            System.out.println("5. Entrar como Cliente");
            System.out.println("6. Entrar como Motorista");
            System.out.println("0. Sair");

            int opcao = Integer.parseInt(input.nextLine());

            try{
                switch(opcao) {
                    case 1:

                        Cadastro.cadastrarCliente(fachada, input);
                        System.out.println("Cliente cadastrado com sucesso.");
                        break;
                    case 2:
                        Cadastro.cadastrarMotorista(fachada, input);
                        System.out.println("Motorista cadastrado com sucesso.");
                        break;
                    case 3:
                        List<Cliente> clientes = fachada.listarClientes();
                        System.out.println("------- Lista de Clientes -------");
                        for(Cliente c : clientes)
                            System.out.println(c);
                        break;
                    case 4:
                        List<Motorista> motoristas = fachada.listarMotoristas();
                        System.out.println("------- Lista de Motoristas -------");
                        for(Motorista m : motoristas)
                            System.out.println(m);
                        break;
                    case 5:
                        System.out.println("Insira o CPF: ");
                         String cpf = input.nextLine();
                         try {
                             Cliente atual = fachada.buscarCliente(cpf);

                             boolean menuCliente = true;
                             while(menuCliente) {
                                 System.out.println("------- Menu do Cliente -------");
                                 System.out.println("Bem vindo, " + atual.getNome());
                                 System.out.println("1 - Exibir Informações");
                                 System.out.println("2 - Solicitar Corrida");
                                 System.out.println("0 - Retornar ao Menu Principal");

                                 int opcaoCliente = Integer.parseInt(input.nextLine());
                                 switch(opcaoCliente) {
                                     case 1:
                                         System.out.println(atual);
                                         break;
                                     case 0:
                                         menuCliente = false;
                                         break;
                                 }
                             }
                         } catch (EntidadeNaoExisteException e) {
                             System.out.println("Erro de busca: " + e.getMessage());
                         }
                         break;
                    case 6:
                        System.out.println("Insira o CPF ou CNH: ");
                        String id = input.nextLine();
                        try {
                            Motorista atual = fachada.buscarMotorista(id);

                            boolean menuMotorista = true;
                            while(menuMotorista) {
                                System.out.println("------- Menu do Motorista -------");
                                System.out.println("Bem vindo, " + atual.getNome());
                                System.out.println("1 - Exibir informações.");
                                System.out.println("0 - Retornar ao Menu Principal");

                                int opcaoMotorista = Integer.parseInt(input.nextLine());
                                switch(opcaoMotorista) {
                                    case 1:
                                        System.out.println(atual);
                                        break;
                                    case 0:
                                        menuMotorista = false;
                                        break;
                                }
                            }
                        } catch (EntidadeNaoExisteException e) {
                            System.out.println("Erro de busca: " + e.getMessage());
                        }
                        break;
                    case 0:
                        System.exit(0);
                        break;
                }
            } catch (EntidadeJaExisteException e) {
                System.out.println("Erro de cadastro: " + e.getMessage());
            } catch (EntidadeNaoExisteException e) {
                System.out.println("Erro de busca: " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println("Erro de execução: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        }
    }
}
