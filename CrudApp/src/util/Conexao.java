// Pacote utilitário que contém classes auxiliares, como a de conexão com o banco
package util;

// Importa classes necessárias para conexão com banco de dados
import java.sql.Connection;
import java.sql.DriverManager;

// Classe responsável por fornecer conexões com o banco de dados
public class Conexao {

    // URL de conexão com o banco de dados MySQL (banco chamado 'crud' na porta padrão 3306)
    private static final String URL = "jdbc:mysql://localhost:3306/crud";

    // Nome de usuário do banco de dados
    private static final String USER = "root";

    // Senha do usuário do banco de dados
    private static final String PASSWORD = "Allan@2612";

    // Método estático que retorna uma conexão com o banco de dados
    public static Connection conectar() throws Exception {
        // Usa o DriverManager para obter uma conexão usando os dados acima
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
