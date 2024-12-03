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
        boardPanel = new JPanel(){
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
                
                // Center the grid within the panel
                int startX = (width - (cellSize * columns)) / 2;
                int startY = (height - (cellSize * rows)) / 2;
                
                // Position buttons in a grid
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

        // Disable layout manager to use custom layout
        boardPanel.setLayout(null);

        // constraints for middle panel (for board panel)
        GridBagConstraints mpgbc = new GridBagConstraints();
        mpgbc.insets = new Insets(20, 20, 20, 20);
        mpgbc.fill = GridBagConstraints.BOTH;
        mpgbc.anchor = GridBagConstraints.CENTER;
        mpgbc.weightx = 1;
        mpgbc.weighty = 1;

        // add board panel to middle panel
        middlePanel.add(boardPanel, mpgbc);

        // constraints for board panel

        // the board
        board = new JButton[8][5];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 5; j++) {
                JButton button = new JButton("(" + i + "," + j + ")");
                
                // Make buttons visually distinct and square
                button.setBackground(Color.BLACK);
                button.setBorderPainted(true);
                button.setOpaque(true);

                board[i][j] = button;
                boardPanel.add(button);
            }
        }

        // Add resize listener to maintain square buttons
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
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