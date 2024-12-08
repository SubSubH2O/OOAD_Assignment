import java.awt.*;
import javax.swing.JFrame;

public class ChessGUI extends JFrame {

    public ChessGUI() {
        super("Kwazam Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;

        BoardView boardView = new BoardView();
        add(boardView, gbc);

        setVisible(true); // Call visibility once, after setup
    }

    public static void main(String[] args) {
        new ChessGUI();
    }
}