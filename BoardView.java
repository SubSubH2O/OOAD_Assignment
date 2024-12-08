import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardView extends JPanel {

    public BoardView() {
        setLayout(new BorderLayout()); // Set layout for BoardView

        // Panel to center the board
        JPanel outsidePanel = new JPanel(new GridBagLayout());

        // Constraints for the board panel
        GridBagConstraints mpgbc = new GridBagConstraints();
        mpgbc.insets = new Insets(20, 20, 20, 20);
        mpgbc.fill = GridBagConstraints.BOTH;
        mpgbc.anchor = GridBagConstraints.CENTER;
        mpgbc.weightx = 1;
        mpgbc.weighty = 1;

        // Create the board panel
        JPanel boardPanel = new JPanel() {
            @Override
            public void doLayout() {
                int width = getWidth();
                int height = getHeight();

                int columns = 5;
                int rows = 8;

                int cellWidth = width / columns;
                int cellHeight = height / rows;

                // Make cells square
                int cellSize = Math.min(cellWidth, cellHeight);

                // Center the grid
                int startX = (width - (cellSize * columns)) / 2;
                int startY = (height - (cellSize * rows)) / 2;

                // Arrange buttons
                for (Component comp : getComponents()) {
                    int index = getComponentZOrder(comp);
                    int row = index / columns;
                    int col = index % columns;

                    comp.setBounds(
                        startX + col * cellSize,
                        startY + row * cellSize,
                        cellSize,
                        cellSize
                    );
                }
            }

            @Override
            public boolean isOptimizedDrawingEnabled() {
                return false;
            }
        };

        boardPanel.setLayout(null); // Disable layout manager

        // Add buttons to the board
        JButton[][] board = new JButton[8][5];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                JButton button = new JButton();
                button.setBackground(Color.BLACK);
                button.setOpaque(true);

                board[i][j] = button;
                boardPanel.add(button);
            }
        }

        // Resize listener
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                boardPanel.revalidate();
                boardPanel.repaint();
            }
        });

        // Add board to the outside panel
        outsidePanel.add(boardPanel, mpgbc);

        // Add outside panel to BoardView
        this.add(outsidePanel, BorderLayout.CENTER);
    }
}