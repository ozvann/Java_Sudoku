/**
 * La classe <code>Direction</code> est utilisée pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 4.4
 * @author Ozvann ABRAHAM Soraya KHADIR
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Classe principale pour l'édition d'une grille de Sudoku.
 */
public class Edit_Sudoko extends JFrame {
    public static JTextField[][] grid;

    /**
     * Récupère la grille de Sudoku.
     * @return la grille de Sudoku
     */
    public static JTextField[][] getGrid(){
        return grid;
    }

    /**
     * Sauvegarde la grille de Sudoku.
     */
    private void SauvegardeGrille() {
        SauvegardeGrille.sauvegarderGrille(grid);
    }

    /**
     * Importe une grille de Sudoku à partir d'un fichier.
     * @param fileToOpen le fichier contenant la grille de Sudoku à importer
     */
    private void importerGrille(File fileToOpen) {
        Import.importerGrille(grid, fileToOpen);
    }

    /**
     * Constructeur de la classe Edit_Sudoko.
     */
    public Edit_Sudoko() {
        super("Sudoku Grid");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(9, 9));
        int[][] sudokuArray = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        grid = new JTextField[9][9];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.PLAIN, 20));
                if (sudokuArray[i][j] != 0) {
                    textField.setText(Integer.toString(sudokuArray[i][j]));
                    textField.setEditable(false);
                }
                grid[i][j] = textField;
                panel.add(textField);
                if ((i + 1) % 3 == 0 && (j + 1) % 3 == 0) {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.BLACK));
                } else if ((i + 1) % 3 == 0) {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.BLACK));
                } else if ((j + 1) % 3 == 0) {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.BLACK));
                } else {
                    textField.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
                }
                textField.addKeyListener(new EcouteurDeTouche(this));
            }
        }

        JButton sauvegarderButton = new JButton("Sauvegarder");
        sauvegarderButton.addActionListener(e -> SauvegardeGrille());

        JButton importerButton = new JButton("Importer");
        importerButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choisir un fichier à importer");
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier grille Sudoku (*.gri)", "gri");
            fileChooser.setFileFilter(filter);

            int userSelection = fileChooser.showOpenDialog(null);

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToOpen = fileChooser.getSelectedFile();
                importerGrille(fileToOpen);
            }
        });

        JPanel buttonPanelLeft = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanelLeft.add(sauvegarderButton);

        JPanel buttonPanelRight = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanelRight.add(importerButton);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(buttonPanelLeft, BorderLayout.WEST);
        buttonPanel.add(buttonPanelRight, BorderLayout.EAST);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setSize(400, 450); 
        setVisible(true);
    }

    /**
     * Point d'entrée de l'application.
     * @param args les arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Edit_Sudoko::new);
    }
}
