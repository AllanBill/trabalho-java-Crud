
package view;

import dao.PessoaDAO;
import model.Pessoa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private JTable tabela;
    private JTextField txtNome, txtEmail;
    private PessoaDAO dao = new PessoaDAO();
    private DefaultTableModel modelo;

    public TelaPrincipal() {
        setTitle("Sistema CRUD - Java");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Email"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER);

        JPanel painel = new JPanel(new GridLayout(2, 3));
        txtNome = new JTextField();
        txtEmail = new JTextField();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(btnSalvar);
        painel.add(new JLabel("Email:"));
        painel.add(txtEmail);
        painel.add(btnAtualizar);

        add(painel, BorderLayout.NORTH);
        add(btnExcluir, BorderLayout.SOUTH);

        carregarTabela();

        btnSalvar.addActionListener(e -> {
            try {
                Pessoa p = new Pessoa();
                p.setNome(txtNome.getText());
                p.setEmail(txtEmail.getText());
                dao.inserir(p);
                limparCampos();
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
            }
        });

        btnAtualizar.addActionListener(e -> {
            try {
                int row = tabela.getSelectedRow();
                if (row == -1) return;

                int id = (int) modelo.getValueAt(row, 0);
                Pessoa p = new Pessoa(id, txtNome.getText(), txtEmail.getText());
                dao.atualizar(p);
                limparCampos();
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
            }
        });

        btnExcluir.addActionListener(e -> {
            try {
                int row = tabela.getSelectedRow();
                if (row == -1) return;

                int id = (int) modelo.getValueAt(row, 0);
                dao.deletar(id);
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        });

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                txtNome.setText(modelo.getValueAt(row, 1).toString());
                txtEmail.setText(modelo.getValueAt(row, 2).toString());
            }
        });
    }

    private void carregarTabela() {
        try {
            modelo.setRowCount(0);
            List<Pessoa> lista = dao.listar();
            for (Pessoa p : lista) {
                modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getEmail()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
    }
}
