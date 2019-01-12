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
    private final JPanel panelNord;
    private final JPanel panelEst;
    private final VueNiveau vueNiveau;
    private JLabel piocheInondation;
    private JLabel defausseInondation;
    private JLabel piocheTresor;
    private JLabel defausseTresor;
    private final ArrayList<JLabel> labelsTresors;
    
    
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
        ArrayList<Integer> liste = new ArrayList<>(Arrays.asList(6,11,24,29,30,31,34,35));
        
        while(i <= 35 ){
                if(liste.contains(i)){
                    
                    JLabel vide = new JLabel();
                    panelGrille.add(vide);
                }
                else if(i ==0){
                    piocheTresor = new JLabel("Pioche Carte Tresor");
                    piocheTresor.setForeground(Color.red);
                    piocheTresor.setPreferredSize(new Dimension(75, 50));
                    piocheTresor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                    panelGrille.add(piocheTresor);
                }
                else if( i == 1){
                defausseTresor = new JLabel("Defausse Carte Tresor");
                defausseTresor.setForeground(Color.red);
                defausseTresor.setPreferredSize(new Dimension(75, 50));
                defausseTresor.setBorder(BorderFactory.createLineBorder(Color.red, 1));
                panelGrille.add(defausseTresor);
                }
                else if (i == 4){
                piocheInondation = new JLabel("Pioche Carte Inondation");
                piocheInondation.setForeground(Color.blue);
                piocheInondation.setPreferredSize(new Dimension(75, 50));
                piocheInondation.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
                panelGrille.add(piocheInondation);
                }
                else if (i ==5){
                defausseInondation = new JLabel("Defausse Carte Inoendation");
                defausseInondation.setForeground(Color.blue);
                defausseInondation.setPreferredSize(new Dimension(75, 50));
                defausseInondation.setBorder(BorderFactory.createLineBorder(Color.blue, 1));
                panelGrille.add(defausseInondation);
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
        
        
        
        
        
        
        
        
        
        //AFFICHAGE VUE NIVEAU
        panelEst = new JPanel();
        this.add(panelEst, BorderLayout.EAST);
      
        panelEst.add(vueNiveau);
        
    }
     

    public ArrayList<VueTuile> getListeTuiles() {
        return listeTuiles;
    }

    public JPanel getPanelNord() {
        return panelNord;
    }

    public JPanel getPanelEst() {
        return panelEst;
    }

    public VueNiveau getVueNiveau() {
        return vueNiveau;
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

    public ArrayList<JLabel> getLabelsTresors() {
        return labelsTresors;
    }
}
//  public VueGrille getVueGrille(int x) {
//      return this.getComponent(x).vueTuile;
//  }
     

