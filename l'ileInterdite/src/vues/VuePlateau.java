package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import util.MessageBox;
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
    private final JPanel panelSud;
    private VueNiveau vueNiveau;
    private MessageBox messageBox;
    private final JPanel mainPanel;
    private final JPanel panelPrincipal;

    public VuePlateau(VueGrille vueGrille, LinkedHashMap<Integer, VueAventurier> listeVuesJoueurs, MessageBox mb, VueNiveau vn) {
        window = new JFrame();
        window.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        window.setTitle("L'ile Interdite");
        // Définit la taille de la fenêtre en pixels
        //window.setSize(1000, 750);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //window.setLocation(0,0);
        //window.setLocation(dim.width/2-window.getSize().width/2, dim.height/2-window.getSize().height/2);
        window.setSize(dim.width, dim.height*4/5);
        panelPrincipal = new JPanel(new BorderLayout());
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panelPrincipal, BorderLayout.CENTER);
        window.add(mainPanel);
        
        
        
        
        this.vueGrille = vueGrille;
        panelPrincipal.add(vueGrille, BorderLayout.CENTER);
        
            
        this.listeVuesJoueurs=listeVuesJoueurs;
        
        
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
        //PANNEAUX AVENTURIERS
        this.messageBox = mb;
        this.vueNiveau = vn;
        mainPanel.add(vn, BorderLayout.EAST);
        panelSud = new JPanel(new GridLayout(1,4));
        panelPrincipal.add(mb,BorderLayout.WEST);
        panelPrincipal.add(panelSud, BorderLayout.SOUTH);
        for(Integer key : this.listeVuesJoueurs.keySet()){
            panelSud.add(listeVuesJoueurs.get(key));
        }
        
    }
    public void setTuilesAssechement(ArrayList<Integer> listeIdTuiles, Integer idJoueur, Color couleur, Color couleur2){
        for (Integer idTuile : listeIdTuiles){
            this.vueGrille.getListeTuiles().get(idTuile).getBtnTuile().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.CHOISIR_TUILE_A, idJoueur, null, null, idTuile));
                    clearChanged();
                }
            });
            this.vueGrille.getListeTuiles().get(idTuile).setCouleurCliquable(couleur, couleur2);
        }
    }
    public void setTuilesDeplacement(ArrayList<Integer> listeIdTuiles, Integer idJoueur, Color couleur, Color couleur2){
        for(Integer idTuile : listeIdTuiles){
            this.vueGrille.getListeTuiles().get(idTuile).getBtnTuile().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.CHOISIR_TUILE_D, idJoueur, null, null, idTuile));
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

    public VueNiveau getVueNiveau() {
        return vueNiveau;
    }

    public MessageBox getMessageBox() {
        return messageBox;
    }

    public LinkedHashMap<Integer, VueAventurier> getListeVuesJoueurs() {
        return listeVuesJoueurs;
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }
    
    
    public void afficher() {
        this.window.setVisible(true);
    }
    
  



}
