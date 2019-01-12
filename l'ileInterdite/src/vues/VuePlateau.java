package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Message;
import util.Utils;
import util.Utils.Commandes;

/**
 *
 * @author IUT2-Dept Info
 */
public class VuePlateau extends Observable {

    private final JFrame window;
    private final VueGrille vueGrille;
    private JLabel titre;
    private final ArrayList<VueAventurier> listeVuesJoueurs;
    private final JPanel panelGauche;
    private final JPanel panelDroite;
    
    private final JPanel panelPrincipal;
    
    //private JPanel panelGauche;
    //private JPanel panelDroite;
    

    public VuePlateau(VueGrille vueGrille, ArrayList<VueAventurier> listeVuesJoueurs) {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("L'ile Interdite");
        // Définit la taille de la fenêtre en pixels
        //window.setSize(1000, 750);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setSize(dim.width, dim.height);
        panelPrincipal = new JPanel(new BorderLayout());
        window.add(panelPrincipal);
        
        
        
        
        this.vueGrille = vueGrille;
        panelPrincipal.add(vueGrille, BorderLayout.CENTER);
        
        //ACTION LISTENER CASES DE JEU
        int i =0;
        while(i<=23){
            vueGrille.getListeTuiles().get(i).getBtnTuile().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers();
                    clearChanged();
                }
            });
            i++;
        }this.listeVuesJoueurs=listeVuesJoueurs;
        
        
        // ACTION LISTENER BOUTONS AVENTURIERS
        int j = 0;
        
        while(j<= listeVuesJoueurs.size()-1){
            int idJoueur = listeVuesJoueurs.get(j).getIdVueAventurier();
            
            listeVuesJoueurs.get(j).getBtnAssecher().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.ASSECHER, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(j).getBtnBouger().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.BOUGER, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(j).getBtnAutreAction().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.POUVOIR, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(j).getBtnDonnerCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.DONNER, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(j).getBtnUtiliserCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.CHOISIR_CARTE, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(j).getBtnTerminerTour().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.TERMINER, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(j).getBtnRecupTresor().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.RECUPERER_TRESOR, idJoueur, null, null, null));
                    clearChanged();
                }
            });
            
            j++;
        }
        
        //PANNEAUX AVENTURIERS DE GAUCHE
        panelGauche = new JPanel(new GridLayout(2,1));
        panelPrincipal.add(panelGauche, BorderLayout.WEST);
        if(listeVuesJoueurs.size()>=1){
            panelGauche.add(listeVuesJoueurs.get(0));
        }
        if(listeVuesJoueurs.size()>=2){
            panelGauche.add(listeVuesJoueurs.get(1));
        }
        
        //PANNEAUX AVENTURIERS DE DROITE
        panelDroite = new JPanel(new GridLayout(2,1));
        panelPrincipal.add(panelDroite, BorderLayout.EAST);
        if(listeVuesJoueurs.size()>=1){
            panelDroite.add(listeVuesJoueurs.get(2));
        }
        if(listeVuesJoueurs.size()>=2){
            panelDroite.add(listeVuesJoueurs.get(3));
        }
    }

    public JFrame getWindow() {
        return window;
    }

    public VueGrille getVueGrille() {
        return vueGrille;
    }

    public JLabel getTitre() {
        return titre;
    }

    public ArrayList<VueAventurier> getListeVuesJoueurs() {
        return listeVuesJoueurs;
    }

    public JPanel getPanelGauche() {
        return panelGauche;
    }

    public JPanel getPanelDroite() {
        return panelDroite;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    public VueAventurier getVueJoueur(Integer id){
        for(VueAventurier va : this.listeVuesJoueurs){
            if(Objects.equals(va.getIdVueAventurier(), id)){
                return va;
            }
        }
        return this.listeVuesJoueurs.get(0);
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
  



}
