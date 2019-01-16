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
import javax.swing.BorderFactory;
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
    private LinkedHashMap<Integer, VueCarte> listeVuesCartes;

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
        
        //AFFICHAGE VUE NIVEAU ET MESSAGE BOX
        window.add(mainPanel);
        this.messageBox = mb;
        this.vueNiveau = vn;
        mainPanel.add(vn, BorderLayout.EAST);
        panelPrincipal.add(mb,BorderLayout.WEST);
        
        
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
            listeVuesJoueurs.get(key).getBtnAnnuler().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.ANNULER, key, null, null, null));
                    clearChanged();
                }
            });
            listeVuesJoueurs.get(key).getBtnDonnerCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.DONNER_CARTE, key, null, null, null));
                    clearChanged();
                }
            });

           
        }
        ArrayList<Integer> listeId = new ArrayList<>();
        for(Integer key : listeVuesJoueurs.keySet()){
            listeId.add(key);
        }
        //PANNEAUX AVENTURIERS

        panelSud = new JPanel(new GridLayout(1,4));

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
        this.listeVuesJoueurs.get(idJoueur).getBtnAnnuler().setEnabled(true);
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
        this.listeVuesJoueurs.get(idJoueur).getBtnAnnuler().setEnabled(true);
    }
    //REMISE PAR DEFAUT DE L'AFFICHAGE DES TUILES APRES UN DEPLACEMENT OU UN ASSECHEMENT
    public void setTuilesDefaut(ArrayList<Integer> listeIdTuiles){
        for(Integer idTuile : listeIdTuiles){
            JButton btnTuile = this.vueGrille.getListeTuiles().get(idTuile).getBtnTuile();
            if (!(btnTuile.getActionListeners().length == 0) ){
                btnTuile.removeActionListener(btnTuile.getActionListeners()[0]);
                this.vueGrille.getListeTuiles().get(idTuile).setCouleurDefaut();
            }
            
        }
    }
    
    //AFFICHAGE DE VUE POUR CHOISIR UNE CARTE
    public void setCartesDonnables(ArrayList<Integer> listeIdCartes, Integer idJoueurCourant, Integer idJoueurCible){
        for (Integer idCarte : listeIdCartes){
            VueCarte vc = this.listeVuesJoueurs.get(idJoueurCourant).getCartesEnMain().get(idCarte);
            
   
            vc.setSkinCliquable();
            vc.getBtnCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.CHOISIR_CARTE, idJoueurCible, idCarte, null, null));
                    clearChanged();
                }
            });
        }
    }
    public void setCartesDefaussables(ArrayList<Integer> listeIdCartes, Integer idJoueurCourant){
        for (Integer idCarte : listeIdCartes){
            VueCarte vc = this.listeVuesJoueurs.get(idJoueurCourant).getCartesEnMain().get(idCarte);
            vc.setSkinCliquable();
            
            vc.getBtnCarte().addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(new Message(Commandes.DEFAUSSER_CARTE, idJoueurCourant, idCarte, null, null));
                    clearChanged();
                }
            });
        }
    }
    public void setCartesDefaut(ArrayList<Integer> listeIdCartes, Integer idJoueur){
        for (Integer idCarte : listeIdCartes){
            VueCarte vc = this.listeVuesJoueurs.get(idJoueur).getCartesEnMain().get(idCarte);
            vc.setSkinDefaut();
            vc.getBtnCarte().removeActionListener(vc.getBtnCarte().getActionListeners()[0]);
             
        }
    }
    public void setBoutonsRecevoirCarte(ArrayList<Integer> listeIdJoueurs, Integer idJoueur){
        for(Integer key : listeIdJoueurs){
            
                JButton btn = this.listeVuesJoueurs.get(key).getBtnDonnerCarte();
                btn.setText("Recevoir Carte");
                btn.setEnabled(true);
                btn.removeActionListener(btn.getActionListeners()[0]);
                
                btn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        
                        notifyObservers(new Message(Commandes.RECEVOIR_CARTE, key, null, null, null));
                        clearChanged();
                    }
                });
            
        }
    }
    public void setBoutonsDonnerCarte(ArrayList<Integer> listeIdJoueurs, Integer idJoueur){
        for(Integer key : listeIdJoueurs){
            
                JButton btn = this.listeVuesJoueurs.get(key).getBtnDonnerCarte();
                btn.setText("Donner Carte");
                btn.setEnabled(false);
                btn.removeActionListener(btn.getActionListeners()[0]);
                
                btn.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setChanged();
                        
                        notifyObservers(new Message(Commandes.DONNER_CARTE, key, null, null, null));
                        clearChanged();
                    }
                });
        }
    }
    
    public JFrame getWindow() {
        return window;
    }

    public LinkedHashMap<Integer, VueCarte> getListeVuesCartes() {
        return listeVuesCartes;
    }

    public void setListeVuesCartes(LinkedHashMap<Integer, VueCarte> listeVuesCartes) {
        this.listeVuesCartes = listeVuesCartes;
    }
    public void addCarte(Integer id, VueCarte vc){
        this.listeVuesCartes.put(id, vc);
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
