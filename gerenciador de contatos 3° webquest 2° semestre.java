public class Contato {
    // Atributos privados
    private String nome;
    private String telefone;
    private String email;

    // Construtor padrão (sem parâmetros)
    public Contato() {
        System.out.println("Contato criado com sucesso!");
    }

    // Construtor com parâmetros
    public Contato(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString()
    @Override
    public String toString() {
        return "Contato {" +
                "Nome='" + nome + '\'' +
                ", Telefone='" + telefone + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }
}