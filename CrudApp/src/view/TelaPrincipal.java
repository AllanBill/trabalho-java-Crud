// Pacote que contém as classes de interface gráfica (view)
package view;

// Importa as classes necessárias para comunicação com o banco e modelo de dados
import dao.PessoaDAO;
import model.Pessoa;

// Importações da biblioteca Swing e AWT para construção da interface gráfica
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// Classe principal da interface gráfica, herda de JFrame
public class TelaPrincipal extends JFrame {

    // Componentes da tela
    private JTable tabela;
    private JTextField txtNome, txtEmail;

    // Objeto responsável por acessar o banco de dados
    private PessoaDAO dao = new PessoaDAO();

    // Modelo de dados da tabela
    private DefaultTableModel modelo;

    // Construtor da interface
    public TelaPrincipal() {
        // Título da janela
        setTitle("Sistema CRUD - Java");

        // Tamanho da janela
        setSize(600, 400);

        // Encerramento do programa ao fechar a janela
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Centraliza a janela na tela
        setLocationRelativeTo(null);

        // Define o layout principal como BorderLayout
        setLayout(new BorderLayout());

        // Cria o modelo da tabela com colunas ID, Nome e Email
        modelo = new DefaultTableModel(new Object[]{"ID", "Nome", "Email"}, 0);

        // Cria a tabela com o modelo
        tabela = new JTable(modelo);

        // Adiciona a tabela a um painel de rolagem
        JScrollPane scroll = new JScrollPane(tabela);
        add(scroll, BorderLayout.CENTER); // Coloca no centro da tela

        // Painel com campos de texto e botões (parte superior)
        JPanel painel = new JPanel(new GridLayout(2, 3));

        // Campos de texto
        txtNome = new JTextField();
        txtEmail = new JTextField();

        // Botões
        JButton btnSalvar = new JButton("Salvar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        // Adiciona os elementos ao painel de entrada
        painel.add(new JLabel("Nome:"));
        painel.add(txtNome);
        painel.add(btnSalvar);
        painel.add(new JLabel("Email:"));
        painel.add(txtEmail);
        painel.add(btnAtualizar);

        // Adiciona o painel na parte de cima da janela
        add(painel, BorderLayout.NORTH);

        // Adiciona o botão de excluir na parte inferior
        add(btnExcluir, BorderLayout.SOUTH);

        // Carrega os dados da tabela ao iniciar
        carregarTabela();

        // Ação do botão Salvar
        btnSalvar.addActionListener(e -> {
            try {
                // Cria uma nova pessoa e define os dados digitados
                Pessoa p = new Pessoa();
                p.setNome(txtNome.getText());
                p.setEmail(txtEmail.getText());

                // Insere no banco
                dao.inserir(p);

                // Limpa os campos e atualiza a tabela
                limparCampos();
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao inserir: " + ex.getMessage());
            }
        });

        // Ação do botão Atualizar
        btnAtualizar.addActionListener(e -> {
            try {
                int row = tabela.getSelectedRow();
                if (row == -1) return; // Se nada selecionado, não faz nada

                // Obtém o ID da linha selecionada
                int id = (int) modelo.getValueAt(row, 0);

                // Cria o objeto com os novos dados
                Pessoa p = new Pessoa(id, txtNome.getText(), txtEmail.getText());

                // Atualiza no banco
                dao.atualizar(p);

                // Limpa os campos e atualiza a tabela
                limparCampos();
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao atualizar: " + ex.getMessage());
            }
        });

        // Ação do botão Excluir
        btnExcluir.addActionListener(e -> {
            try {
                int row = tabela.getSelectedRow();
                if (row == -1) return; // Se nenhuma linha está selecionada

                // Pega o ID da pessoa selecionada
                int id = (int) modelo.getValueAt(row, 0);

                // Deleta do banco
                dao.deletar(id);

                // Atualiza a tabela
                carregarTabela();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage());
            }
        });

        // Evento de clique na tabela: preenche os campos com os dados selecionados
        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                txtNome.setText(modelo.getValueAt(row, 1).toString());
                txtEmail.setText(modelo.getValueAt(row, 2).toString());
            }
        });
    }

    // Método para carregar os dados da tabela a partir do banco de dados
    private void carregarTabela() {
        try {
            modelo.setRowCount(0); // Limpa a tabela
            List<Pessoa> lista = dao.listar(); // Busca as pessoas no banco

            // Adiciona cada pessoa como uma linha na tabela
            for (Pessoa p : lista) {
                modelo.addRow(new Object[]{p.getId(), p.getNome(), p.getEmail()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados: " + ex.getMessage());
        }
    }

    // Método auxiliar para limpar os campos de texto
    private void limparCampos() {
        txtNome.setText("");
        txtEmail.setText("");
    }
}
