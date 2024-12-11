/**
 * La classe <code>Direction</code> est utilisée pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 4.4
 * @author Ozvann ABRAHAM Soraya KHADIR
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * Classe représentant une interface graphique pour résoudre des grilles de Sudoku.
 */
public class Executeur_Sudoku extends JFrame {
    private JTextField[][] grid;
    private boolean grilleImportee = false;

    /**
     * Constructeur de la classe Executeur_Sudoku.
     * Initialise l'interface graphique pour le Sudoku.
     */
    public Executeur_Sudoku() {
        super("Sudoku Grid"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        JPanel panel = new JPanel(new GridLayout(9, 9)); 
        int[][] sudokuArray = new int[][] { 
                {0, 0, 0, 0, 9, 5, 0, 0, 4},
                {5, 3, 0, 4, 0, 8, 7, 0, 2},
                {0, 0, 0, 7, 0, 0, 6, 0, 3},
                {9, 0, 0, 0, 3, 4, 0, 8, 0},
                {0, 4, 0, 0, 1, 0, 0, 7, 0},
                {0, 2, 0, 5, 7, 0, 0, 0, 6},
                {4, 0, 9, 0, 0, 0, 0, 0, 0},
                {6, 0, 7, 9, 0, 3, 0, 2, 1},
                {2, 0, 0, 6, 5, 0, 0, 0, 0}
        };

        grid = new JTextField[9][9]; 

        /**
         * Boucles pour créer et configurer chaque case de la grille
         */
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Arial", Font.PLAIN, 20));
                if (sudokuArray[i][j] != 0) {

                    /**
                     * Si la case contient une valeur initiale, l'afficher et la rendre non modifiable
                     */
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
            }
        }

        /**
         * Bouton pour importer une grille
         */
        JButton importerButton = new JButton("Importer");
        importerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choisir un fichier à importer");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier grille Sudoku (*.gri)", "gri");
                fileChooser.setFileFilter(filter);

                int userSelection = fileChooser.showOpenDialog(null);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToOpen = fileChooser.getSelectedFile();
                    Import.importerGrille(grid, fileToOpen);
                    grilleImportee = true;
                }
            }
        });

        /**
         * Bouton pour afficher la solution de la grille
         */
        JButton solutionButton = new JButton("Solution");
        solutionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] sudokuArray = new int[9][9];
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String text = grid[i][j].getText();
                        sudokuArray[i][j] = text.isEmpty() ? 0 : Integer.parseInt(text);
                    }
                }
                ResolveurSudoku.resoudreSudoku(sudokuArray);
                afficherGrille(sudokuArray);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(importerButton);
        buttonPanel.add(solutionButton);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel); 
        setSize(400, 450); 
        setVisible(true); 
    }

    /**
     * Méthode pour afficher une grille de Sudoku dans l'interface graphique.
     * @param sudokuArray Grille de Sudoku à afficher.
     */
    public void afficherGrille(int[][] sudokuArray) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j].setText(Integer.toString(sudokuArray[i][j]));
            }
        }
    }

    /**
     * Méthode principale pour exécuter l'application.
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Executeur_Sudoku::new);
    }
}
