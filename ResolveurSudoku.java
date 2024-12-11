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

/**
 * Classe représentant un solveur de Sudoku.
 */
public class ResolveurSudoku {

    /**
     * Méthode récursive pour résoudre une grille de Sudoku.
     * @param grille Grille de Sudoku à résoudre.
     * @return true si la grille est résolue avec succès, sinon false.
     */
    public static boolean resoudreSudoku(int[][] grille) {
        int[] position = trouverCaseVide(grille);
        if (position == null) {
            return true; 
        }

        int ligne = position[0];
        int colonne = position[1];

        for (int chiffre = 1; chiffre <= 9; chiffre++) {
            if (estChiffreValide(grille, ligne, colonne, chiffre)) {
                grille[ligne][colonne] = chiffre;

                if (resoudreSudoku(grille)) {
                    return true; 
                }

                grille[ligne][colonne] = 0; // Annulation du chiffre s'il ne conduit pas à une solution
            }
        }

        return false; // Aucune solution trouvée pour cette case
    }

    /**
     * Méthode pour trouver une case vide dans la grille.
     * @param grille Grille de Sudoku.
     * @return Position de la première case vide, sinon null si la grille est pleine.
     */
    private static int[] trouverCaseVide(int[][] grille) {
        int[] position = new int[2];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grille[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }

        return null;
    }

    /**
     * Méthode pour vérifier si un chiffre est valide dans une case donnée.
     * @param grille Grille de Sudoku.
     * @param ligne Ligne de la case.
     * @param colonne Colonne de la case.
     * @param chiffre Chiffre à vérifier.
     * @return true si le chiffre est valide dans la case, sinon false.
     */
    private static boolean estChiffreValide(int[][] grille, int ligne, int colonne, int chiffre) {
        return !estPresentSurLigne(grille, ligne, chiffre) &&
               !estPresentSurColonne(grille, colonne, chiffre) &&
               !estPresentDansCarre(grille, ligne - ligne % 3, colonne - colonne % 3, chiffre);
    }

    /**
     * Méthode pour vérifier si un chiffre est présent dans une ligne.
     * @param grille Grille de Sudoku.
     * @param ligne Ligne à vérifier.
     * @param chiffre Chiffre à rechercher.
     * @return true si le chiffre est présent dans la ligne, sinon false.
     */
    private static boolean estPresentSurLigne(int[][] grille, int ligne, int chiffre) {
        for (int j = 0; j < 9; j++) {
            if (grille[ligne][j] == chiffre) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode pour vérifier si un chiffre est présent dans une colonne.
     * @param grille Grille de Sudoku.
     * @param colonne Colonne à vérifier.
     * @param chiffre Chiffre à rechercher.
     * @return true si le chiffre est présent dans la colonne, sinon false.
     */
    private static boolean estPresentSurColonne(int[][] grille, int colonne, int chiffre) {
        for (int i = 0; i < 9; i++) {
            if (grille[i][colonne] == chiffre) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode pour vérifier si un chiffre est présent dans un carré 3x3.
     * @param grille Grille de Sudoku.
     * @param ligneDebut Ligne de début du carré.
     * @param colonneDebut Colonne de début du carré.
     * @param chiffre Chiffre à rechercher.
     * @return true si le chiffre est présent dans le carré, sinon false.
     */
    private static boolean estPresentDansCarre(int[][] grille, int ligneDebut, int colonneDebut, int chiffre) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i + ligneDebut][j + colonneDebut] == chiffre) {
                    return true;
                }
            }
        }
        return false;
    }
}
