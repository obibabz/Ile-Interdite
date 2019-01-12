/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import static javax.swing.SwingConstants.CENTER;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final JPanel panelBoutonsPere;
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
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
    private final JTextField position;

    public VueAventurier(int id, int numJCourant, int numJoueur, String nomJoueur, String nomAventurier, Color couleur, Color couleurGrisee, String pos){

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

        // =================================================================================                                                                                            
        // NORD : le titre = nom de l'aventurier sur la couleurActive du pion                                                                                                           

        this.panelAventurier = new JPanel();
        panelAventurier.setBackground(couleur);
        panelAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ));
        mainPanel.add(panelAventurier, BorderLayout.NORTH);

        // =================================================================================                                                                                            
        // CENTRE : 1 ligne pour position courante                                                                                                                                      
        this.panelCentre = new JPanel(new GridLayout(2, 1));
        this.panelCentre.setOpaque(false);
        this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleur));
        mainPanel.add(this.panelCentre, BorderLayout.CENTER);

        panelCentre.add(new JLabel ("Position", SwingConstants.CENTER));
        position = new  JTextField(30);
        position.setText(pos);
	position.setHorizontalAlignment(CENTER);
        panelCentre.add(position);


	// =================================================================================                                                                                            
        // SUD : les boutons  
        this.panelBoutonsPere = new JPanel(new BorderLayout());
        this.panelBoutons = new JPanel(new GridLayout(3,2));
        this.panelBoutons.setOpaque(false);
	mainPanel.add(this.panelBoutonsPere, BorderLayout.SOUTH);
        this.panelBoutonsPere.add(this.panelBoutons, BorderLayout.CENTER);

        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
	this.btnAutreAction = new JButton("AutreAction") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnUtiliserCarte = new JButton("Utiliser Carte") ;
        this.btnRecupTresor = new JButton("Récupérer Trésor") ;
        
        
        this.panelBoutons.add(btnBouger);
        /*btnBouger.*/
	this.panelBoutons.add(btnAssecher);
        /*btnAssecher.*/
        this.panelBoutons.add(btnAutreAction); btnAutreAction.setEnabled(false);
        /*btnAutreAction.*/
        this.panelBoutons.add(btnDonnerCarte); btnDonnerCarte.setEnabled(false);
        /*btnDonnerCarte.*/
        this.panelBoutons.add(btnUtiliserCarte); btnUtiliserCarte.setEnabled(false);
        /*btnUtiliserCarte.*/
        this.panelBoutons.add(btnRecupTresor); btnRecupTresor.setEnabled(false);
        /*btnRecupTresor*/
        
        this.panelBoutonsPere.add(btnTerminerTour, BorderLayout.SOUTH);
        /*btnTerminerTour*/
        
        if(numJCourant != numJoueur){
            this.btnBouger.setEnabled(false);
            this.btnAssecher.setEnabled(false);
            panelAventurier.setBackground(couleurGrisee);
            mainPanel.setBorder(BorderFactory.createLineBorder(couleurGrisee, 2)) ;
            this.panelCentre.setBorder(new MatteBorder(0, 0, 2, 0, couleurGrisee));
            this.btnTerminerTour.setEnabled(false);
        }

    }
    public void setVueJPrecedant(){
        this.getBtnBouger().setEnabled(false);
            this.getBtnAssecher().setEnabled(false);
            this.getBtnTerminerTour().setEnabled(false);
            if("Pilote".equals(nomAventurier)){
                this.getBtnAutreAction().setEnabled(false);
            }
            this.getPanelAventurier().setBackground(couleurGrisee);
            this.getMainPanel().setBorder(BorderFactory.createLineBorder(couleurGrisee, 2)) ;
            this.getPanelCentre().setBorder(new MatteBorder(0, 0, 2, 0, couleurGrisee));
    }
    
    public void setVueJCourant(){
        this.getBtnBouger().setEnabled(true);
            this.getBtnAssecher().setEnabled(true);
            this.getBtnTerminerTour().setEnabled(true);
            if("Pilote".equals(nomAventurier)){
                this.getBtnAutreAction().setEnabled(true);
            }
            this.getPanelAventurier().setBackground(couleur);
            this.getMainPanel().setBorder(BorderFactory.createLineBorder(couleur, 2)) ;
            this.getPanelCentre().setBorder(new MatteBorder(0, 0, 2, 0, couleur));
    }

    public JButton getBtnBouger() {
        return btnBouger;
    }

    public JPanel getPanelBoutonsPere() {
        return panelBoutonsPere;
    }

    public JPanel getPanelBoutons() {
        return panelBoutons;
    }

    public JPanel getPanelCentre() {
        return panelCentre;
    }
/*
    public JFrame getWindow() {
        return window;
    }
*/
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
 

    
}
