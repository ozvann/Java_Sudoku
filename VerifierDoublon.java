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
 * Classe pour vérifier les doublons dans une grille de Sudoku.
 */
public class VerifierDoublon extends Edit_Sudoko {

    /**
     * Méthode pour vérifier les doublons dans une grille de Sudoku.
     * @return true si la grille ne contient pas de doublons, sinon false.
     */
    public static boolean verifierDoublon() {
        // Vérification des lignes
        for (int i = 0; i < 9; i++) {
            boolean[] valeursPresentes = new boolean[10]; // Tableau pour stocker les valeurs présentes
            for (int j = 0; j < 9; j++) {
                String text = grid[i][j].getText();
                if (!text.isEmpty()) {
                    int chiffre = Integer.parseInt(text);
                    if (valeursPresentes[chiffre]) {
                        return false; // Doublon détecté dans la ligne
                    }
                    valeursPresentes[chiffre] = true;
                }
            }
        }
        // Vérification des colonnes
        for (int j = 0; j < 9; j++) {
            boolean[] valeursPresentes = new boolean[10]; // Tableau pour stocker les valeurs présentes
            for (int i = 0; i < 9; i++) {
                String text = grid[i][j].getText();
                if (!text.isEmpty()) {
                    int chiffre = Integer.parseInt(text);
                    if (valeursPresentes[chiffre]) {
                        return false; // Doublon détecté dans la colonne
                    }
                    valeursPresentes[chiffre] = true;
                }
            }
        }
        return true; // Aucun doublon détecté
    }
}
