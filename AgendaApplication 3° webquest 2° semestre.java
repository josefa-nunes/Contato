Josefa Rodrigues Nunes
Josiana Rodrigues da Silva
Joseane Marli Moreira Coelho
Tiago da Silva Brito
Vitor Gabriel de Sousa Oliveira Sales

import java.util.Scanner;

public class AgendaApplication {
    public static void main(String[] args) {
        AgendaManager agenda = new AgendaManager();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            System.out.println("\n===== MENU DA AGENDA =====");
            System.out.println("1. Adicionar Contato");
            System.out.println("2. Buscar Contato");
            System.out.println("3. Remover Contato");
            System.out.println("4. Listar Todos os Contatos");
            System.out.println("5. Salvar em CSV");
            System.out.println("6. Carregar de CSV");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        System.out.print("Nome: ");
                        String nome = scanner.nextLine().trim();
                        System.out.print("Telefone: ");
                        String telefone = scanner.nextLine().trim();
                        System.out.print("Email: ");
                        String email = scanner.nextLine().trim();

                        if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                            System.out.println("Erro: todos os campos devem ser preenchidos!");
                            break;
                        }

                        try {
                            agenda.adicionarContato(new Contato(nome, telefone, email));
                            System.out.println("Contato adicionado com sucesso!");
                        } catch (ContatoExistenteException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 2:
                        System.out.print("Digite o nome do contato: ");
                        String nomeBusca = scanner.nextLine().trim();
                        if (nomeBusca.isEmpty()) {
                            System.out.println("Erro: nome não pode estar vazio!");
                            break;
                        }
                        try {
                            Contato contato = agenda.buscarContato(nomeBusca);
                            System.out.println("Contato encontrado: " + contato);
                        } catch (ContatoNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Digite o nome do contato a remover: ");
                        String nomeRemover = scanner.nextLine().trim();
                        if (nomeRemover.isEmpty()) {
                            System.out.println("Erro: nome não pode estar vazio!");
                            break;
                        }

                        System.out.print("Tem certeza que deseja remover este contato? (S/N): ");
                        String confirm = scanner.nextLine().trim().toUpperCase();
                        if (!confirm.equals("S")) {
                            System.out.println("Remoção cancelada!");
                            break;
                        }

                        try {
                            agenda.removerContato(nomeRemover);
                            System.out.println("Contato removido com sucesso!");
                        } catch (ContatoNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.println("\nLista de todos os contatos:");
                        if (agenda.listarTodosContatos().isEmpty()) {
                            System.out.println("Nenhum contato cadastrado.");
                        } else {
                            for (Contato c : agenda.listarTodosContatos()) {
                                System.out.println(c);
                            }
                        }
                        break;

                    case 5:
                        System.out.print("Digite o nome do arquivo CSV para salvar: ");
                        String arquivoSalvar = scanner.nextLine().trim();
                        if (arquivoSalvar.isEmpty()) {
                            System.out.println("Erro: nome do arquivo não pode estar vazio!");
                            break;
                        }
                        agenda.salvarContatosCSV(arquivoSalvar);
                        break;

                    case 6:
                        System.out.print("Digite o nome do arquivo CSV para carregar: ");
                        String arquivoCarregar = scanner.nextLine().trim();
                        if (arquivoCarregar.isEmpty()) {
                            System.out.println("Erro: nome do arquivo não pode estar vazio!");
                            break;
                        }
                        agenda.carregarContatosCSV(arquivoCarregar);
                        break;

                    case 7:
                        System.out.println("Saindo do programa...");
                        break;

                    default:
                        System.out.println("Opção inválida! Digite um número entre 1 e 7.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: digite apenas números inteiros!");
            }

        } while (opcao != 7);

        scanner.close();
    }
}



