/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import static javax.swing.SwingConstants.CENTER;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import util.Parameters;
import util.Utils.Commandes;


/**
 *
 * @author rousstan
 */
public class VueAventurier extends JPanel{
    private final Integer idVueAventurier;
    private final String nomAventurier;
    private final Color couleur;
    private final Color couleurGrisee;
    private final JPanel panelBoutons ;
    private final JPanel panelPosition ;
    private final JPanel panelCentre;
    private final JPanel panelCartes;
    //private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger  ;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnDonnerCarte;
    private final JButton btnUtiliserCarte;
    private final JButton btnRecupTresor;
    private final JButton btnTerminerTour;
    private final JButton btnAnnuler;
    private final JTextField position;
    private final LinkedHashMap<Integer,VueCarte> cartesEnMain;

    public VueAventurier(int id, String nomJoueur, String nomAventurier, Color couleur, Color couleurGrisee, String pos){

        //this.window = new JFrame();
        //window.setSize(350, 200);
        //le titre = nom du joueur                                                                                                                                                      
        //window.setTitle(nomJoueur);      
        mainPanel = new JPanel(new BorderLayout());
        this.add(mainPanel);
        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
        this.idVueAventurier = id;
        this.nomAventurier = nomAventurier;
        this.couleur = couleur;
        this.couleurGrisee= couleurGrisee;
        this.cartesEnMain= new LinkedHashMap();
        //this.cartesEnMain=cartesEnMain;

        // =================================================================================                                                                                            
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion                                                                                                           

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);

        // =================================================================================                                                                                            
        // CENTRE : 1 ligne pour position courante puis les cartes en main      
        panelCentre = new JPanel(new GridLayout(2,1));
        mainPanel.add(panelCentre, BorderLayout.CENTER);
        this.panelPosition = new JPanel(new GridLayout(2, 1));
        this.panelPosition.setOpaque(false);
        this.panelPosition.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        panelCentre.add(this.panelPosition);

        panelPosition.add(new JLabel ("Position", SwingConstants.CENTER));
        position = new  JTextField(30);
        position.setText(pos);
	position.setHorizontalAlignment(CENTER);
        panelPosition.add(position);

        panelCartes = new JPanel(new FlowLayout());
        panelCentre.add(panelCartes);
        for(Integer key : this.cartesEnMain.keySet()){
            panelCartes.add(cartesEnMain.get(key));
        }
	// =================================================================================                                                                                            
        // SUD : les boutons  
        
        this.panelBoutons = new JPanel(new GridLayout(4,2));
        this.panelBoutons.setOpaque(false);
	mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
	this.btnAutreAction = new JButton("Autre Action") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnUtiliserCarte = new JButton("Utiliser Carte") ;
        this.btnRecupTresor = new JButton("Récupérer Trésor") ;
        this.btnAnnuler = new JButton("Annuler");
        
        this.panelBoutons.add(btnBouger);
        
	this.panelBoutons.add(btnAssecher);
        
        this.panelBoutons.add(btnAutreAction); btnAutreAction.setEnabled(false);
        
        this.panelBoutons.add(btnDonnerCarte); btnDonnerCarte.setEnabled(false);
        
        this.panelBoutons.add(btnUtiliserCarte); btnUtiliserCarte.setEnabled(false);
        
        this.panelBoutons.add(btnRecupTresor); btnRecupTresor.setEnabled(false);
        
        this.panelBoutons.add(btnAnnuler); btnAnnuler.setEnabled(false);
        this.panelBoutons.add(btnTerminerTour);
        
        
        
            this.btnBouger.setEnabled(false);
            this.btnAssecher.setEnabled(false);
            panelAventurier.setBackground(couleurGrisee);
            mainPanel.setBorder(BorderFactory.createLineBorder(couleurGrisee, 2)) ;
            this.panelPosition.setBorder(new MatteBorder(0, 0, 2, 0, couleurGrisee));
            this.btnTerminerTour.setEnabled(false);
        

    }
    //AFFICHAGE SI NBACTIONSRESTANTES =0
    public void setVueFinTour(){
            this.btnBouger.setEnabled(false);
            this.btnAssecher.setEnabled(false);
            if("Pilote".equals(nomAventurier)){
                this.getBtnAutreAction().setEnabled(false);
            }
    }
    //AFFICHAGE POUR LES CHOIX DE TUILES/CARTES
    public void setVueChoix(){
            this.setVueFinTour();
            this.btnTerminerTour.setEnabled(false);
    }
    
    //DESACTIVATION DE LA VUE
    public void setVueJPrecedant(){
            this.setVueFinTour();
            this.btnTerminerTour.setEnabled(false);
            this.panelAventurier.setBackground(couleurGrisee);
            this.mainPanel.setBorder(BorderFactory.createLineBorder(couleurGrisee, 2)) ;
            this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleurGrisee));
    }
    //ACTIVATION DE LA VUE
    public void setVueJCourant(){
            this.btnBouger.setEnabled(true);
            this.btnAnnuler.setEnabled(false);
            this.btnAssecher.setEnabled(true);
            this.btnTerminerTour.setEnabled(true);
            if("Pilote".equals(nomAventurier)){
                this.btnAutreAction.setEnabled(true);
            }
            this.panelAventurier.setBackground(couleur);
            this.mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
            this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
    }

    public void ajouterCarte(Integer id, VueCarte vc){
        System.out.println("0");
        this.cartesEnMain.put(id, vc);
                System.out.println("1");

        panelCartes.removeAll();
                System.out.println("2");

        for(Integer key : this.cartesEnMain.keySet()){
                    System.out.println("3");

            panelCartes.add(cartesEnMain.get(key));
                    System.out.println("4");

        }
    }
    public void retirerCarte(Integer id){
        this.cartesEnMain.remove(id);panelCartes.removeAll();
        for(Integer key : this.cartesEnMain.keySet()){
            panelCartes.add(cartesEnMain.get(key));
        }
    }
    public JButton getBtnBouger() {
        return btnBouger;
    }

    public JPanel getPanelBoutons() {
        return panelBoutons;
    }

    public JPanel getPanelCentre() {
        return panelPosition;
    }

    public JPanel getPanelAventurier() {
        return panelAventurier;
    }

    public JButton getBtnDonnerCarte() {
        return btnDonnerCarte;
    }

    public JButton getBtnUtiliserCarte() {
        return btnUtiliserCarte;
    }

    public JButton getBtnRecupTresor() {
        return btnRecupTresor;
    }
    

    public JButton getBtnAssecher() {
        return btnAssecher;
    }

    public JButton getBtnAutreAction() {
        return btnAutreAction;
    }

    public JTextField getPosition() {
        return position;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }

    public Integer getIdVueAventurier() {
        return idVueAventurier;
    }

    public LinkedHashMap<Integer, VueCarte> getCartesEnMain() {
        return cartesEnMain;
    }

    public JButton getBtnAnnuler() {
        return btnAnnuler;
    }
 

    
}
