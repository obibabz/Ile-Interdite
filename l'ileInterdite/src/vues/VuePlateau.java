package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author IUT2-Dept Info
 */
public class VuePlateau extends Observable {

    private JFrame window;
    private VueGrille vueGrille;
    private JLabel titre;
    

    public VuePlateau() {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(750, 500);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        window.add(panelPrincipal);
        
        panelPrincipal.add(titre = new JLabel("L'Ile interdite"), BorderLayout.NORTH);
        this.titre.setHorizontalAlignment(JLabel.CENTER);
        
        vueGrille = new VueGrille();
        panelPrincipal.add(vueGrille, BorderLayout.CENTER);


    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
  
public static void main(String[] args) {   
    
        VuePlateau vuePlateau = new VuePlateau();
        vuePlateau.afficher();
        
    } 


}
