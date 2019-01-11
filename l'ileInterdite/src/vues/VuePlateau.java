package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
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
    private ArrayList<VueAventurier> listeeVuesJoueurs;
    
    private JPanel panelPrincipal;
    
    //private JPanel panelGauche;
    //private JPanel panelDroite;
    

    public VuePlateau(VueGrille vueGrille) {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        // Définit la taille de la fenêtre en pixels
        window.setSize(1000, 750);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        
        panelPrincipal = new JPanel(new BorderLayout());
        window.add(panelPrincipal);
        
        
        
        
        this.vueGrille = vueGrille;
        panelPrincipal.add(vueGrille, BorderLayout.CENTER);

        /*
            vueGrille.getVueGrille[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Message message = new Message(num, TypeMessage.typeCase);
                    setChanged();
                    notifyObservers(message);
                    clearChanged();
                    
                    }
                }
            );}
        */
        
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
  



}
