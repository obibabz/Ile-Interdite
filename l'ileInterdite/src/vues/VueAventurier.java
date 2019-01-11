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
import util.Utils.Commandes;


/**
 *
 * @author rousstan
 */
public class VueAventurier extends Observable{                                                                                                                                            
    private final JPanel panelBoutons ;
    private final JPanel panelCentre ;
    private final JFrame window;
    private final JPanel panelAventurier;
    private final JPanel mainPanel;
    private final JButton btnBouger  ;
    private final JButton btnAssecher;
    private final JButton btnAutreAction;
    private final JButton btnDonnerCarte;
    private final JButton btnUtiliserCarte;
    private final JButton btnRecupTresor;
    private final JButton btnTerminerTour;
    private JTextField position;

    public VueAventurier(String nomJoueur, String nomAventurier, Color couleur, String pos){

        this.window = new JFrame();
        window.setSize(350, 200);
        //le titre = nom du joueur                                                                                                                                                      
        window.setTitle(nomJoueur);
        mainPanel = new JPanel(new BorderLayout());
        this.window.add(mainPanel);

        mainPanel.setBackground(new Color(230, 230, 230));
        mainPanel.setBorder(BorderFactory.createLineBorder(couleur, 2)) ;

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
        this.panelBoutons = new JPanel(new GridLayout(2,3));
        this.panelBoutons.setOpaque(false);
	mainPanel.add(this.panelBoutons, BorderLayout.SOUTH);

        this.btnBouger = new JButton("Bouger") ;
        this.btnAssecher = new JButton( "Assecher");
	this.btnAutreAction = new JButton("AutreAction") ;
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnUtiliserCarte = new JButton("Utiliser Carte") ;
        this.btnRecupTresor = new JButton("Récupérer Trésor") ;
        
        
        this.panelBoutons.add(btnBouger);
        btnBouger.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(Commandes.BOUGER);
                    clearChanged();
                }
            });
	this.panelBoutons.add(btnAssecher);
        btnAssecher.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(Commandes.ASSECHER);
                    clearChanged();
                }
            });
        this.panelBoutons.add(btnAutreAction); btnAutreAction.setEnabled(false);
        btnAutreAction.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(Commandes.POUVOIR);
                    clearChanged();
                }
            });
        this.panelBoutons.add(btnTerminerTour);
        btnTerminerTour.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setChanged();
                    notifyObservers(Commandes.TERMINER);
                    clearChanged();
                }
            });

    }

    public JButton getBtnBouger() {
        return btnBouger;
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

    public JButton getBtnTerminerTour() {
        return btnTerminerTour;
    }
    
    public void afficher() {
        this.window.setVisible(true);
    }
       public void close() {
        this.window.dispose();
    }
}
