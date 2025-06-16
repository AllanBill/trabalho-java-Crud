// Pacote que contém as classes do modelo de dados
package model;

// Classe que representa uma pessoa, usada como modelo no sistema
public class Pessoa {

    // Atributo que representa o identificador único da pessoa no banco
    private int id;

    // Atributo que armazena o nome da pessoa
    private String nome;

    // Atributo que armazena o e-mail da pessoa
    private String email;

    // Construtor padrão (sem parâmetros)
    public Pessoa() {}

    // Construtor que permite inicializar todos os atributos da pessoa
    public Pessoa(int id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    // Getter para o ID (permite acessar o valor de 'id')
    public int getId() { return id; }

    // Setter para o ID (permite modificar o valor de 'id')
    public void setId(int id) { this.id = id; }

    // Getter para o nome
    public String getNome() { return nome; }

    // Setter para o nome
    public void setNome(String nome) { this.nome = nome; }

    // Getter para o email
    public String getEmail() { return email; }

    // Setter para o email
    public void setEmail(String email) { this.email = email; }
}
