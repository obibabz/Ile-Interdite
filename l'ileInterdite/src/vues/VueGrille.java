/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.MessageBox;

/**
 *
 * @author rousstan
 */
public class VueGrille extends JPanel{
    private final LinkedHashMap<Integer,VueTuile> listeTuiles;

    private final JPanel panelSud;
    private JLabel piocheInondation;
    private JLabel defausseInondation;
    private JLabel piocheTresor;
    private JLabel defausseTresor;
    
    
    
     public VueGrille(LinkedHashMap<Integer,VueTuile> listeTuiles) {

        this.listeTuiles = listeTuiles;
        this.setLayout(new BorderLayout());
        
        //AFFICHAGE GRILLE
        JPanel panelGrille = new JPanel(new GridLayout(6,6));
        int i= 0;
        int j=0;
        this.add(panelGrille, BorderLayout.CENTER);
        ArrayList<Integer> liste = new ArrayList<>(Arrays.asList(0,1,4,5,6,11,24,29,30,31,34,35));
        ArrayList<Integer> listeid = new ArrayList<>();
        for(Integer key : listeTuiles.keySet()){
            listeid.add(key);
            
            
            }
        while(i <= 35 ){
                if(liste.contains(i)){
                    
                    JLabel vide = new JLabel();
                    panelGrille.add(vide);
                }
                
                else{
                   panelGrille.add(listeTuiles.get(listeid.get(j)));
                   if (j !=23){
                    j++;
                   }
                }
                i++;
            }
            int k = 0;

        //AFFICHAGE PIOCHE/DEFAUSSE CARTES TRESOR ET INONDATION
        /*
        panelSud = new JPanel(new FlowLayout());
        this.add(panelSud, BorderLayout.SOUTH);
        
        piocheTresor = new JLabel("Pioche Carte Tresor");
        piocheTresor.setForeground(Color.red);
        piocheTresor.setPreferredSize(new Dimension(75, 50));
        piocheTresor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        panelSud.add(piocheTresor);
               
        defausseTresor = new JLabel("Defausse Carte Tresor");
        defausseTresor.setForeground(Color.red);
        defausseTresor.setPreferredSize(new Dimension(75, 50));
        defausseTresor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        panelSud.add(defausseTresor);
                
                
        piocheInondation = new JLabel("Pioche Carte Inondation");
        piocheInondation.setForeground(Color.blue);
        piocheInondation.setPreferredSize(new Dimension(75, 50));
        piocheInondation.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        panelSud.add(piocheInondation);
                
                
        defausseInondation = new JLabel("Defausse Carte Inondation");
        defausseInondation.setForeground(Color.blue);
        defausseInondation.setPreferredSize(new Dimension(75, 50));
        defausseInondation.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        panelSud.add(defausseInondation);
                */
        this.panelSud=new JPanel();
 
    }
     

    public LinkedHashMap<Integer,VueTuile> getListeTuiles() {
        return listeTuiles;
    }

    public JLabel getPiocheInondation() {
        return piocheInondation;
    }

    public JLabel getDefausseInondation() {
        return defausseInondation;
    }

    public JLabel getPiocheTresor() {
        return piocheTresor;
    }

    public JLabel getDefausseTresor() {
        return defausseTresor;
    }
}
     

