package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
    private final LinkedHashMap<Integer, VueAventurier> listeVuesJoueurs;
    private final JPanel panelGauche;
    private final JPanel panelDroite;
    
    private final JPanel panelPrincipal;
    
    //private JPanel panelGauche;
    //private JPanel panelDroite;
    

    public VuePlateau(VueGrille vueGrille, LinkedHashMap<Integer, VueAventurier> listeVuesJoueurs) {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("L'ile Interdite");
        // Définit la taille de la fenêtre en pixels
        //window.setSize(1000, 750);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //window.setLocation(0,0);
        //window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setSize(dim.width, dim.height);
        panelPrincipal = new JPanel(new BorderLayout());
        window.add(panelPrincipal);
        
        
        
        
        this.vueGrille = vueGrille;
        panelPrincipal.add(vueGrille, BorderLayout.CENTER);
        
        //ACTION LISTENER CASES DE JEU
        
         for(Integer key : vueGrille.getListeTuiles().keySet()){
            vueGrille.getListeTuiles().get(key).getBtnTuile().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers();
                    clearChanged();
                }
            });
            
        }this.listeVuesJoueurs=listeVuesJoueurs;
        
        
        // ACTION LISTENER BOUTONS AVENTURIERS
        
        
        for(Integer key : listeVuesJoueurs.keySet()){
            
            
            listeVuesJoueurs.get(key).getBtnAssecher().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.ASSECHER, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnBouger().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.BOUGER, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnAutreAction().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.POUVOIR, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnDonnerCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.DONNER, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnUtiliserCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.CHOISIR_CARTE, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnTerminerTour().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.TERMINER, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnRecupTresor().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.RECUPERER_TRESOR, key, null, null, null));
                    clearChanged();
                }
            });
            
           
        }
        ArrayList<Integer> listeId = new ArrayList<>();
        for(Integer key : listeVuesJoueurs.keySet()){
            listeId.add(key);
        }
        //PANNEAUX AVENTURIERS DE GAUCHE
        panelGauche = new JPanel(new GridLayout(2,1));
        panelPrincipal.add(panelGauche, BorderLayout.WEST);
        if(listeVuesJoueurs.size()>=1){
            panelGauche.add(listeVuesJoueurs.get(listeId.get(0)));
        }
        if(listeVuesJoueurs.size()>=2){
            panelGauche.add(listeVuesJoueurs.get(listeId.get(1)));
        }
        
        //PANNEAUX AVENTURIERS DE DROITE
        panelDroite = new JPanel(new GridLayout(2,1));
        panelPrincipal.add(panelDroite, BorderLayout.EAST);
        if(listeVuesJoueurs.size()>=1){
            panelDroite.add(listeVuesJoueurs.get(listeId.get(2)));
        }
        if(listeVuesJoueurs.size()>=2){
            panelDroite.add(listeVuesJoueurs.get(listeId.get(3)));
        }
    }
    
    public void setTuilesDeplacement(ArrayList<Integer> listeIdTuiles, Integer idJoueur, Color couleur, Color couleur2){
        for(Integer idTuile : listeIdTuiles){
            this.vueGrille.getListeTuiles().get(idTuile).getBtnTuile().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.CHOISIR_TUILE, idJoueur, null, null, idTuile));
                    clearChanged();
                }
            });
            this.vueGrille.getListeTuiles().get(idTuile).setCouleurCliquable(couleur, couleur2);
        }
    }
    public void setTuilesDefaut(ArrayList<Integer> listeIdTuiles){
        for(Integer idTuile : listeIdTuiles){
            JButton btnTuile = this.vueGrille.getListeTuiles().get(idTuile).getBtnTuile();
 
            btnTuile.removeActionListener(btnTuile.getActionListeners()[0]);
            this.vueGrille.getListeTuiles().get(idTuile).setCouleurDefaut();
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

    public LinkedHashMap<Integer, VueAventurier> getListeVuesJoueurs() {
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
    
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
  



}
