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
    private JButton[] gameButton = new JButton[9];
    JLabel nomTour;
    private JLabel titre;
    private JButton finPartie = new JButton("Fin de Partie");

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
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(10,10));
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);
        
        int i = 0;
        while (i<100) {
            panelCentral.add(new JPanel());
            i++;
        }
        
        int j = 0;
        while (j<100) {
        panelCentral.getComponent(j).setBackground(Color.BLUE);
        j++;
        }
        
        panelCentral.getComponent(14).setBackground(Color.yellow);
        panelCentral.getComponent(15).setBackground(Color.yellow);
        panelCentral.getComponent(23).setBackground(Color.yellow);
        panelCentral.getComponent(24).setBackground(Color.yellow);
        panelCentral.getComponent(25).setBackground(Color.yellow);
        panelCentral.getComponent(26).setBackground(Color.yellow);
        panelCentral.getComponent(32).setBackground(Color.yellow);
        panelCentral.getComponent(33).setBackground(Color.yellow);
        panelCentral.getComponent(34).setBackground(Color.yellow);
        panelCentral.getComponent(35).setBackground(Color.yellow);
        panelCentral.getComponent(36).setBackground(Color.yellow);
        panelCentral.getComponent(37).setBackground(Color.yellow);
        panelCentral.getComponent(41).setBackground(Color.yellow);
        panelCentral.getComponent(42).setBackground(Color.yellow);
        panelCentral.getComponent(43).setBackground(Color.yellow);
        panelCentral.getComponent(44).setBackground(Color.yellow);
        panelCentral.getComponent(45).setBackground(Color.yellow);
        panelCentral.getComponent(46).setBackground(Color.yellow);
        panelCentral.getComponent(47).setBackground(Color.yellow);
        panelCentral.getComponent(48).setBackground(Color.yellow);
        panelCentral.getComponent(51).setBackground(Color.yellow);
        panelCentral.getComponent(52).setBackground(Color.yellow);
        panelCentral.getComponent(53).setBackground(Color.yellow);
        panelCentral.getComponent(54).setBackground(Color.yellow);
        panelCentral.getComponent(55).setBackground(Color.yellow);
        panelCentral.getComponent(56).setBackground(Color.yellow);
        panelCentral.getComponent(57).setBackground(Color.yellow);
        panelCentral.getComponent(58).setBackground(Color.yellow);
        panelCentral.getComponent(62).setBackground(Color.yellow);
        panelCentral.getComponent(63).setBackground(Color.yellow);
        panelCentral.getComponent(64).setBackground(Color.yellow);
        panelCentral.getComponent(65).setBackground(Color.yellow);
        panelCentral.getComponent(66).setBackground(Color.yellow);
        panelCentral.getComponent(67).setBackground(Color.yellow);
        panelCentral.getComponent(73).setBackground(Color.yellow);
        panelCentral.getComponent(74).setBackground(Color.yellow);
        panelCentral.getComponent(75).setBackground(Color.yellow);
        panelCentral.getComponent(76).setBackground(Color.yellow);
        panelCentral.getComponent(84).setBackground(Color.yellow);
        panelCentral.getComponent(85).setBackground(Color.yellow);
        

    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
  
public static void main(String[] args) {   
    
        VuePlateau vuePlateau = new VuePlateau();
        vuePlateau.afficher();
        
    } 




}
