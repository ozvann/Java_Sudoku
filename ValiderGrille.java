/**
 * La classe <code>Direction</code> est utilisé mais obligatoire sinon ne veux pas compilere pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 4.4
 * @author Ozvann ABRAHAM Soraya KHADIR
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Classe pour valider la grille de Sudoku.
 */
public class ValiderGrille extends Edit_Sudoko {

    /**
     * Méthode pour valider la grille de Sudoku.
     * @return true si la grille est valide, sinon false.
     */
    public static boolean validerGrille() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = grid[i][j].getText();
                if (!text.isEmpty()) {
                    try {
                        int value = Integer.parseInt(text);
                        if (value < 1 || value > 9) {
                            JOptionPane.showMessageDialog(null, "La grille contient des valeurs invalides !");
                            return false; // Valeur invalide
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "La grille contient des valeurs non numériques !");
                        return false; // Valeur non numérique
                    }
                }
            }
        }
        return true; // La grille est valide
    }
}
