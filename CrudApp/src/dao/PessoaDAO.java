
package dao;

import model.Pessoa;
import util.Conexao;
import java.sql.*;
import java.util.*;

public class PessoaDAO {

    public void inserir(Pessoa p) throws Exception {
        Connection conn = Conexao.conectar();
        String sql = "INSERT INTO pessoas (nome, email) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getNome());
        ps.setString(2, p.getEmail());
        ps.execute();
        conn.close();
    }

    public List<Pessoa> listar() throws Exception {
        Connection conn = Conexao.conectar();
        String sql = "SELECT * FROM pessoas";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Pessoa> lista = new ArrayList<>();
        while (rs.next()) {
            Pessoa p = new Pessoa(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            lista.add(p);
        }

        conn.close();
        return lista;
    }

    public void atualizar(Pessoa p) throws Exception {
        Connection conn = Conexao.conectar();
        String sql = "UPDATE pessoas SET nome=?, email=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, p.getNome());
        ps.setString(2, p.getEmail());
        ps.setInt(3, p.getId());
        ps.execute();
        conn.close();
    }

    public void deletar(int id) throws Exception {
        Connection conn = Conexao.conectar();
        String sql = "DELETE FROM pessoas WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        conn.close();
    }
}
