/**
 * La classe <code>Direction</code> est utilisé mais obligatoire sinon ne veux pas compilere pour signifier une orientation possible
 * parmi les quatre points cardinaux.
 *  
 * @version 4.4
 * @author Ozvann ABRAHAM Soraya KHADIR
 */
import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Classe représentant un écouteur de touches pour la grille de Sudoku.
 */
public class EcouteurDeTouche implements KeyListener {
    private Edit_Sudoko parent;

    /**
     * Constructeur de la classe EcouteurDeTouche.
     * @param parent Instance de la classe Edit_Sudoko à laquelle cet écouteur est attaché.
     */
    public EcouteurDeTouche(Edit_Sudoko parent) {
        this.parent = parent;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Non utilisé mais obligatoire sinon ne veux pas compiler
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Validation de la grille
        if (!ValiderGrille.validerGrille()) {
            return;
        }

        // Vérification des doublons dans les régions
        if (!VerifierRegion.verifierRegion()) {
            JOptionPane.showMessageDialog(null, "Problème de logique. Doublons de chiffres dans une région !");
            return;
        }

        // Vérification des doublons dans les lignes ou les colonnes
        if (!VerifierDoublon.verifierDoublon()) {
            JOptionPane.showMessageDialog(null, "Problème de logique. Doublons de chiffres dans une ligne ou une colonne !");
            return;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Non utilisé mais obligatoire sinon ne veux pas compiler
    }
}
