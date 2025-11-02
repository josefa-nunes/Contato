Josefa Rodrigues Nunes
Josiana Rodrigues da Silva
Joseane Marli Moreira Coelho
Tiago da Silva Brito
Vitor Gabriel de Sousa Oliveira Sales
    
package agenda;

public class App {
    public static void main(String[] args) {
        AgendaEletronica agenda = new AgendaEletronica();

        try {
            // Criando e adicionando contatos
            Contato contato1 = new Contato("João", "87 98889-7000", "joao.eudes@gmail.com");
            Contato contato2 = new Contato("Maria", "87 99999-8888", "maria.silva@gmail.com");

            agenda.adicionarContato(contato1);
            agenda.adicionarContato(contato2);

            // Listando contatos
            System.out.println(" Contatos cadastrados:");
            for (Contato c : agenda.listarTodosContatos()) {
                System.out.println(c);
            }

            // Buscando contato
            System.out.println("\n Buscando contato 'João':");
            System.out.println(agenda.buscarContato("João"));

            // Removendo contato
            agenda.removerContato("Maria");
            System.out.println("\n Contato 'Maria' removido!");

        } catch (ContatoExistenteException | ContatoNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}


