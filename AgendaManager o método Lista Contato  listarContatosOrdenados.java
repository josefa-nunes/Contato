import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AgendaManager implements GerenciadorContatos {

    private List<Contato> contatos;

    public AgendaManager() {
        contatos = new ArrayList<>();
    }

    @Override
    public void adicionarContato(Contato contato) throws ContatoExistenteException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(contato.getNome())) {
                throw new ContatoExistenteException("Contato já existe: " + contato.getNome());
            }
        }
        contatos.add(contato);
    }

    @Override
    public Contato buscarContato(String nome) throws ContatoNaoEncontradoException {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        throw new ContatoNaoEncontradoException("Contato não encontrado: " + nome);
    }

    @Override
    public void removerContato(String nome) throws ContatoNaoEncontradoException {
        Contato contato = buscarContato(nome);
        contatos.remove(contato);
    }

    @Override
    public List<Contato> listarTodosContatos() {
        return contatos;
    }

    // MÉTODO 1: listarContatosOrdenados() – ordena alfabeticamente
    
    public List<Contato> listarContatosOrdenados() {
        return contatos.stream()
                .sorted(Comparator.comparing(Contato::getNome, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    // MÉTODO 2: buscarPorDominioEmail(String dominio)

    public List<Contato> buscarPorDominioEmail(String dominio) {
        if (dominio == null || dominio.trim().isEmpty()) {
            return new ArrayList<>();
        }

        String dominioLower = dominio.toLowerCase();
        return contatos.stream()
                .filter(c -> c.getEmail().toLowerCase().endsWith(dominioLower))
                .collect(Collectors.toList());
    }

    // MÉTODOS DE CSV (salvar e carregar)

    @Override
    public void salvarContatosCSV(String nomeArquivo) {
        try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(nomeArquivo))) {
            for (Contato c : contatos) {
                writer.write(c.getNome() + ";" + c.getTelefone() + ";" + c.getEmail());
                writer.newLine();
            }
            System.out.println("Contatos salvos com sucesso em " + nomeArquivo);
        } catch (java.io.IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    @Override
    public void carregarContatosCSV(String nomeArquivo) {
        java.io.File arquivo = new java.io.File(nomeArquivo);
        if (!arquivo.exists()) {
            System.out.println("Arquivo " + nomeArquivo + " não encontrado.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(arquivo))) {
            contatos.clear();
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length == 3) {
                    contatos.add(new Contato(dados[0], dados[1], dados[2]));
                }
            }
            System.out.println("Contatos carregados com sucesso de " + nomeArquivo);
        } catch (java.io.IOException e) {
            System.out.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }

}
