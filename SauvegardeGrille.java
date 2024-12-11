/**
 * La classe <code>Direction</code> est utilisé mais obligatoire sinon ne veux pas compilere pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 4.4
 * @author Ozvann ABRAHAM Soraya KHADIR
 */
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;

/**
 * Classe pour sauvegarder la grille de Sudoku dans un fichier.
 */
public class SauvegardeGrille {

    /**
     * Méthode pour sauvegarder la grille de Sudoku dans un fichier.
     * @param grid Grille de JTextField représentant la grille de Sudoku.
     */
    public static void sauvegarderGrille(JTextField[][] grid) {
        // Vérification de la validité de la grille
        if (!grilleValide(grid)) {
            JOptionPane.showMessageDialog(null, "La grille contient des lettres, il ne faut pas de lettre que des chiffres !");
            return;
        }
        
        // Sélection du fichier de sauvegarde
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choisir l'emplacement de sauvegarde");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Fichier grille Sudoku (*.gri)", "gri");
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("grille_sudoku.gri"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                // Ouverture du flux de sortie vers le fichier
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileToSave));
                // Parcours de la grille pour écrire chaque valeur dans le fichier
                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9; j++) {
                        String text = grid[i][j].getText();
                        int value = text.isEmpty() ? 0 : Integer.parseInt(text);
                        outputStream.writeInt(value);
                    }
                }
                outputStream.close(); // Fermeture du flux
                JOptionPane.showMessageDialog(null, "Grille sauvegardée dans " + fileToSave.getAbsolutePath() + " !");
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la sauvegarde de la grille !");
            }
        }
    }

    /**
     * Méthode pour vérifier si la grille de Sudoku est valide (ne contient que des chiffres).
     * @param grid Grille de JTextField représentant la grille de Sudoku.
     * @return true si la grille est valide, sinon false.
     */
    private static boolean grilleValide(JTextField[][] grid) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String text = grid[i][j].getText();
                if (!text.isEmpty()) {
                    try {
                        Integer.parseInt(text);
                    } catch (NumberFormatException e) {
                        return false; // La grille contient des lettres
                    }
                }
            }
        }
        return true; // La grille est valide
    }
}
