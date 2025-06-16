// Importa a classe TelaPrincipal do pacote de visualização (view)
import view.TelaPrincipal;

// Classe principal do programa Java
public class Main {

    // Método principal que inicia a aplicação
    public static void main(String[] args) {

        // Garante que a interface gráfica (Swing) seja criada na thread correta (EDT - Event Dispatch Thread)
        javax.swing.SwingUtilities.invokeLater(() -> {

            // Cria uma nova instância da interface gráfica e a exibe
            new TelaPrincipal().setVisible(true);
        });
    }
}
