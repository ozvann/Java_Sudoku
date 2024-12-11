/**
 * La classe <code>Direction</code> est utilisé mais obligatoire sinon ne veux pas compilere pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 4.4
 * @author Ozvann ABRAHAM Soraya KHADIR
 */
import javax.swing.*;
import java.io.*;

/**
 * Classe permettant d'importer une grille de Sudoku à partir d'un fichier.
 */
public class Import {
    /**
     * Méthode pour importer une grille de Sudoku à partir d'un fichier.
     * @param grid Grille de JTextField représentant la grille de Sudoku.
     * @param fileToOpen Fichier à partir duquel importer la grille.
     */
    public static void importerGrille(JTextField[][] grid, File fileToOpen) {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileToOpen));
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    int value = inputStream.readInt();
                    if (value != 0) {
                        grid[i][j].setText(Integer.toString(value));
                        grid[i][j].setEditable(false); // La case est préremplie et non modifiable
                    } else {
                        grid[i][j].setText(""); // La case est vide et modifiable
                        grid[i][j].setEditable(true);
                    }
                }
            }
            inputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'importation de la grille !");
        }
    }
}
