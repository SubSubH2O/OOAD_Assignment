import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame{

    //the panel on the top
    private JPanel topPanel;
    private JLabel lbl1;
    private JLabel lbl2;
    private JButton start;

    //the panel on the middle
    private JPanel middlePanel;
    private JButton[][] board;
    private JPanel boardPanel;

    //still dont know
    private JButton newGame;


    public GUI() {
        super("Kwazam Chess");

        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        //gbc constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        /* --------------------------------------------- first panel ----------------------------------------------- */
        topPanel = new JPanel(new GridBagLayout());
        GridBagConstraints tpgbc = new GridBagConstraints();

        // constraints for top panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(topPanel, gbc);


        // labels for stating the player
        lbl1 = new JLabel("Player 1");
        lbl1.setOpaque(true);
        lbl1.setForeground(new Color(51, 82, 119));
        lbl1.setFont(new Font("Arial", Font.BOLD, 25));
        lbl1.setPreferredSize(new Dimension(100, 35));
        tpgbc.gridx = 0;
        tpgbc.weightx = 1;
        topPanel.add(lbl1, tpgbc);

        // start button
        start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 35));
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //start game;
            }
        });
        tpgbc.gridx = 1;
        tpgbc.weightx = 1;
        topPanel.add(start, tpgbc);

        lbl2 = new JLabel("Player 2");
        lbl2.setOpaque(true);
        lbl2.setForeground(new Color(160, 2, 2));
        lbl2.setPreferredSize(new Dimension(100, 35));
        lbl2.setFont(new Font("Arial", Font.BOLD, 25));
        tpgbc.gridx = 2;
        tpgbc.weightx = 1;
        topPanel.add(lbl2, tpgbc);


        /* ---------------------------------------------- middle panel -------------------------------------------------- */ 
        middlePanel = new JPanel(new GridBagLayout());
        boardPanel = new JPanel(new GridBagLayout());

        // constraints for middle panel (for board panel)
        GridBagConstraints mpgbc = new GridBagConstraints();
        mpgbc.insets = new Insets(20, 20, 20, 20);
        mpgbc.fill = GridBagConstraints.BOTH;
        mpgbc.weightx = 1;
        mpgbc.weighty = 1;

        // add board panel to middle panel
        middlePanel.add(boardPanel, mpgbc);

        // constraints for board panel
        GridBagConstraints boardgbc = new GridBagConstraints();
        boardgbc.fill = GridBagConstraints.BOTH;
        boardgbc.weightx = 1;
        boardgbc.weighty = 1;

        // the board
        board = new JButton[8][5];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                board[i][j] = new JButton();
                boardgbc.gridx = j;
                boardgbc.gridy = i;
                boardPanel.add(board[i][j], boardgbc);
            }
        }

        // Add resize logic to maintain square buttons
        boardPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = boardPanel.getWidth();
                int panelHeight = boardPanel.getHeight();
                int buttonWidth = panelWidth / 5;
                int buttonHeight = panelHeight / 8;
                int buttonSize = Math.min(buttonWidth, buttonHeight);

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 5; j++) {
                        board[i][j].setPreferredSize(new Dimension(buttonSize, buttonSize));
                    }
                }
                boardPanel.revalidate();
                boardPanel.repaint();
            }
        });


        // this is for middle panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 60;
        add(middlePanel, gbc);
        
        setVisible(true);
    }
}


//this is what i want to create square buttons

/* import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame {
    private JButton[][] board;
    private JPanel boardPanel;

    public GUI() {
        // Basic frame setup
        super("Kwazam Chess");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        // Use BorderLayout
        setLayout(new BorderLayout());

        // Create board panel with custom layout
        boardPanel = new JPanel() {
            @Override
            public void doLayout() {
                int width = getWidth();
                int height = getHeight();
                
                // Calculate the size of each cell based on the panel's dimensions
                int columns = 5;
                int rows = 8;
                
                int cellWidth = width / columns;
                int cellHeight = height / rows;
                
                // Use the smaller dimension to make cells square
                int cellSize = Math.min(cellWidth, cellHeight);
                
                // Position buttons in a grid
                for (Component comp : getComponents()) {
                    int index = getComponentZOrder(comp);
                    int row = index / columns;
                    int col = index % columns;
                    
                    comp.setBounds(
                        col * cellSize, 
                        row * cellSize, 
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
        
        // Disable layout manager to use custom layout
        boardPanel.setLayout(null);

        // Create buttons
        board = new JButton[8][5];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                JButton button = new JButton("(" + i + "," + j + ")");
                
                // Make buttons visually distinct and square
                button.setBackground(i % 2 == j % 2 ? Color.WHITE : Color.LIGHT_GRAY);
                button.setBorderPainted(true);
                button.setOpaque(true);
                
                board[i][j] = button;
                boardPanel.add(button);
            }
        }

        // Add board to the center of the frame
        add(boardPanel, BorderLayout.CENTER);

        // Add resize listener to maintain square buttons
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                boardPanel.revalidate();
                boardPanel.repaint();
            }
        });

        // Ensure visibility
        setVisible(true);
    }

    public static void main(String[] args) {
        // Ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new GUI();
        });
    }
} */
