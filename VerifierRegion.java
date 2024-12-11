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
 * Classe pour vérifier les régions dans une grille de Sudoku.
 */
public class VerifierRegion extends Edit_Sudoko {

    /**
     * Méthode pour vérifier les régions dans une grille de Sudoku.
     * @return true si la grille ne contient pas de doublons dans les régions, sinon false.
     */
    public static boolean verifierRegion() {
        for (int regionRow = 0; regionRow < 3; regionRow++) {
            for (int regionCol = 0; regionCol < 3; regionCol++) {
                boolean[] valeursPresentes = new boolean[10]; // Tableau pour stocker les valeurs présentes
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        String text = grid[regionRow * 3 + i][regionCol * 3 + j].getText();
                        if (!text.isEmpty()) {
                            int chiffre = Integer.parseInt(text);
                            if (valeursPresentes[chiffre]) {
                                return false; // Doublon détecté dans la région
                            }
                            valeursPresentes[chiffre] = true;
                        }
                    }
                }
            }
        }
        return true; // Aucun doublon détecté dans les régions
    }
}
