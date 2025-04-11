package ui;

import comunicacao.Fachada;
import dados.modelo.pagamento.CartaoCredito;
import dados.modelo.pagamento.Pix;
import dados.modelo.pessoa.Cliente;
import dados.modelo.pessoa.Motorista;
import negocio.excecoes.EntidadeJaExisteException;
import negocio.excecoes.EntidadeNaoExisteException;
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
                                 System.out.println("2 - Adicionar Forma de Pagamento");
                                 System.out.println("3 - Adicionar Saldo");
                                 System.out.println("4 - Solicitar Corrida");
                                 System.out.println("0 - Retornar ao Menu Principal");

                                 int opcaoCliente = Integer.parseInt(input.nextLine());
                                 switch(opcaoCliente) {
                                     case 1:
                                         System.out.println(atual);
                                         break;
                                     case 2:
                                         System.out.println("Escolha a forma de pagamento para adicionar");
                                         System.out.println("1 - Pix");
                                         System.out.println("2 - Cartão de Crédito");

                                         int escolhaPagamento = Integer.parseInt(input.nextLine());

                                         switch(escolhaPagamento) {
                                             case 1:
                                                 fachada.adicionarFormaDePagamento(atual.getCpf(), new Pix());
                                                 System.out.println("Pix adicionado com sucesso na conta");
                                                 break;
                                             case 2:
                                                 System.out.println("Número do cartão.");
                                                 String numero = input.nextLine();
                                                 System.out.println("CCV: ");
                                                 String ccv = input.nextLine();
                                                 String nomeTitular = atual.getNome();
                                                 System.out.println("Limite máximo: ");
                                                 double limite = Double.parseDouble(input.nextLine());
                                                 fachada.adicionarFormaDePagamento(atual.getCpf(), new CartaoCredito(ccv, limite, nomeTitular, numero));
                                                 System.out.println("Cartão de crédito adiciona com sucesso.");
                                                 break;
                                         }
                                         break;
                                     case 3:
                                         if(fachada.possuiPix(atual.getCpf())) {
                                             System.out.println("Insira o valor que deseja depositar na conta.");
                                             double valor = Double.parseDouble(input.nextLine());
                                             fachada.adicionarSaldo(atual.getCpf(), valor);
                                             System.out.println("Saldo atualizado com sucesso.");
                                         } else {
                                             System.out.println("É necessário ter o pix cadastrado para adicionar saldo.");
                                         }
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
