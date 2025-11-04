Josefa Rodrigues Nunes
Josiana Rodrigues da Silva
Joseane Marli Moreira Coelho
Tiago da Silva Brito
Vitor Gabriel de Sousa Oliveira Sales
    
    public class App {
    public static void main(String[] args) {
        AgendaManager agenda = new AgendaManager();

        try {
            Contato c1 = new Contato("João", "87 98889-7000", "joao.eudes@gmail.com");
            Contato c2 = new Contato("Maria", "87 99999-1234", "maria.silva@gmail.com");

            agenda.adicionarContato(c1);
            agenda.adicionarContato(c2);

            System.out.println("Lista de contatos:");
            for (Contato c : agenda.listarTodosContatos()) {
                System.out.println(c);
            }

            agenda.salvarContatosCSV("agenda.csv");

            agenda.carregarContatosCSV("agenda.csv");

            System.out.println("\nContatos carregados do arquivo:");
            for (Contato c : agenda.listarTodosContatos()) {
                System.out.println(c);
            }

        } catch (ContatoExistenteException | ContatoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }
}


