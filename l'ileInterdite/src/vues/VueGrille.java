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
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rousstan
 */
public class VueGrille extends JPanel{
    private final ArrayList<VueTuile> listeTuiles;
    private JPanel panelNord;
    private JPanel panelEst;
    private VueNiveau vueNiveau;
    private JLabel piocheInondation;
    private JLabel defausseInondation;
    private JLabel piocheTresor;
    private JLabel defausseTresor;
    private ArrayList<JLabel> labelsTresors;
    
    
     public VueGrille(ArrayList<VueTuile> listeTuiles, VueNiveau vueNiveau, ArrayList<String> tresorsPossedes) {
    
        this.vueNiveau = vueNiveau;
        this.listeTuiles = listeTuiles;
        this.setLayout(new BorderLayout());
        labelsTresors = new ArrayList<>();
        //AFFICHAGE GRILLE
        JPanel panelGrille = new JPanel(new GridLayout(6,6));
        int i= 0;
        int j=0;
        this.add(panelGrille, BorderLayout.CENTER);
        ArrayList<Integer> liste = new ArrayList<>(Arrays.asList(0,1,4,5,6,11,24,29,30,31,34,35));
        
        while(i <= 35 ){
                if(liste.contains(i)){
                    
                    JLabel vide = new JLabel();
                    panelGrille.add(vide);
                }
                else{
                   panelGrille.add(listeTuiles.get(j));
                   //JLabel vide = new JLabel("i :"+i+" "+"j : "+j+" pasvide");
                    //this.add(vide);
                   if (j !=23){
                    j++;
                   }
                }
                i++;
            }
            int k = 0;
            // AFFICHAGE TRESORS ET PIOCHE/DEFAUSSE CARTES TRESOR  ET PIOCHE/DEFAUSSE CARTES INONDATION
        panelNord = new JPanel(new FlowLayout());
        this.add(panelNord, BorderLayout.NORTH);
        ArrayList<String> tresors = new ArrayList<>(Arrays.asList("La Statue du Zéphyr", "Le Calice de l'Onde", "Le Cristal Ardent", "La Pierre Sacrée"));
        for(String tresor : tresorsPossedes){
            if (tresors.contains(tresor)){
                JLabel labelT = new JLabel(tresor);
                panelNord.add(labelT);
                labelT.setPreferredSize(new Dimension(100,100));
                labelT.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
                labelsTresors.add(labelT);
                k++;
            }
        }
        while(k<=3){
            JLabel labelVide = new JLabel("Tresor pas encore récupéré");
                panelNord.add(labelVide);
                labelVide.setPreferredSize(new Dimension(100, 100));
                labelVide.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
                labelsTresors.add(labelVide);
                k++;
        }
        piocheTresor = new JLabel("Pioche Carte Tresor");
        piocheTresor.setForeground(Color.red);
        piocheTresor.setPreferredSize(new Dimension(75, 50));
        piocheTresor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        
        panelNord.add(piocheTresor);
        
        defausseTresor = new JLabel("Defausse Carte Tresor");
        defausseTresor.setForeground(Color.red);
        defausseTresor.setPreferredSize(new Dimension(75, 50));
        defausseTresor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        panelNord.add(defausseTresor);
        
        
        piocheInondation = new JLabel("Pioche Carte Inondation");
        piocheInondation.setForeground(Color.blue);
        piocheInondation.setPreferredSize(new Dimension(75, 50));
        piocheInondation.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        panelNord.add(piocheInondation);
        
        defausseInondation = new JLabel("Defausse Carte Inoendation");
        defausseInondation.setForeground(Color.blue);
        defausseInondation.setPreferredSize(new Dimension(75, 50));
        defausseInondation.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
        panelNord.add(defausseInondation);
        
        //AFFICHAGE VUE NIVEAU
        panelEst = new JPanel();
        this.add(panelEst, BorderLayout.EAST);
        
        
        
        
        
        panelEst.add(vueNiveau);
    }
     
        /*
        this.setLayout(new GridLayout(10,10));

        int i = 0;
        while (i<100) {
            this.add(new JPanel());
            i++;
        }

        int j = 0;
        while (j<100) {
        this.getComponent(j).setBackground(Color.BLUE);
        this.add(new VueTuile());
        j++;
        }

        this.getComponent(14).setBackground(Color.GREEN);
        this.getComponent(15).setBackground(Color.GREEN);
        this.getComponent(23).setBackground(Color.GREEN);
        this.getComponent(24).setBackground(Color.GREEN);
        this.getComponent(25).setBackground(Color.GREEN);
        this.getComponent(26).setBackground(Color.GREEN);
        this.getComponent(32).setBackground(Color.GREEN);
        this.getComponent(33).setBackground(Color.GREEN);
        this.getComponent(34).setBackground(Color.GREEN);
        this.getComponent(35).setBackground(Color.GREEN);
        this.getComponent(36).setBackground(Color.GREEN);
        this.getComponent(37).setBackground(Color.GREEN);
        this.getComponent(41).setBackground(Color.GREEN);
        this.getComponent(42).setBackground(Color.GREEN);
        this.getComponent(43).setBackground(Color.GREEN);
        this.getComponent(44).setBackground(Color.GREEN);
        this.getComponent(45).setBackground(Color.GREEN);
        this.getComponent(46).setBackground(Color.GREEN);
        this.getComponent(47).setBackground(Color.GREEN);
        this.getComponent(48).setBackground(Color.GREEN);
        this.getComponent(51).setBackground(Color.GREEN);
        this.getComponent(52).setBackground(Color.GREEN);
        this.getComponent(53).setBackground(Color.GREEN);
        this.getComponent(54).setBackground(Color.GREEN);
        this.getComponent(55).setBackground(Color.GREEN);
        this.getComponent(56).setBackground(Color.GREEN);
        this.getComponent(57).setBackground(Color.GREEN);
        this.getComponent(58).setBackground(Color.GREEN);
        this.getComponent(62).setBackground(Color.GREEN);
        this.getComponent(63).setBackground(Color.GREEN);
        this.getComponent(64).setBackground(Color.GREEN);
        this.getComponent(65).setBackground(Color.GREEN);
        this.getComponent(66).setBackground(Color.GREEN);
        this.getComponent(67).setBackground(Color.GREEN);
        this.getComponent(73).setBackground(Color.GREEN);
        this.getComponent(74).setBackground(Color.GREEN);
        this.getComponent(75).setBackground(Color.GREEN);
        this.getComponent(76).setBackground(Color.GREEN);
        this.getComponent(84).setBackground(Color.GREEN);
        this.getComponent(85).setBackground(Color.GREEN);
*/
}
//  public VueGrille getVueGrille(int x) {
//      return this.getComponent(x).vueTuile;
//  }
     

