// Pacote que agrupa as classes responsáveis pelo acesso ao banco de dados
package dao;

// Importa a classe Pessoa (modelo de dados)
import model.Pessoa;
// Importa a classe utilitária para conexão com o banco
import util.Conexao;

// Importações para manipulação de banco de dados e listas
import java.sql.*;
import java.util.*;

// Classe responsável pelas operações de banco de dados relacionadas à entidade Pessoa
public class PessoaDAO {

    // Método para inserir uma nova pessoa no banco de dados
    public void inserir(Pessoa p) throws Exception {
        // Abre conexão com o banco
        Connection conn = Conexao.conectar();

        // Define o comando SQL com parâmetros
        String sql = "INSERT INTO pessoas (nome, email) VALUES (?, ?)";

        // Prepara o comando para execução
        PreparedStatement ps = conn.prepareStatement(sql);

        // Define os valores dos parâmetros
        ps.setString(1, p.getNome());
        ps.setString(2, p.getEmail());

        // Executa o comando
        ps.execute();

        // Fecha a conexão
        conn.close();
    }

    // Método para listar todas as pessoas cadastradas
    public List<Pessoa> listar() throws Exception {
        // Abre conexão com o banco
        Connection conn = Conexao.conectar();

        // Define o comando SQL
        String sql = "SELECT * FROM pessoas";

        // Prepara o comando
        PreparedStatement ps = conn.prepareStatement(sql);

        // Executa a consulta
        ResultSet rs = ps.executeQuery();

        // Lista que irá armazenar os resultados
        List<Pessoa> lista = new ArrayList<>();

        // Percorre os resultados e cria objetos Pessoa
        while (rs.next()) {
            Pessoa p = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            lista.add(p);
        }

        // Fecha a conexão
        conn.close();

        // Retorna a lista de pessoas
        return lista;
    }

    // Método para atualizar os dados de uma pessoa
    public void atualizar(Pessoa p) throws Exception {
        // Abre conexão
        Connection conn = Conexao.conectar();

        // Comando SQL com placeholders
        String sql = "UPDATE pessoas SET nome=?, email=? WHERE id=?";

        // Prepara e define os parâmetros
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getNome());
        ps.setString(2, p.getEmail());
        ps.setInt(3, p.getId());

        // Executa atualização
        ps.execute();

        // Fecha conexão
        conn.close();
    }

    // Método para deletar uma pessoa pelo ID
    public void deletar(int id) throws Exception {
        // Abre conexão
        Connection conn = Conexao.conectar();

        // Comando SQL para deletar
        String sql = "DELETE FROM pessoas WHERE id=?";

        // Prepara e define o ID
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        // Executa exclusão
        ps.execute();

        // Fecha conexão
        conn.close();
    }
}
