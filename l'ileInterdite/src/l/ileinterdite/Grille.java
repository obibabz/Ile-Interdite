/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

import java.util.ArrayList;

/**
 *
 * @author rousstan
 */
public class Grille {
    private Tuile[][] grille = new Tuile[6][6];

    public Grille(Tuile[][] grille) {
        this.grille = grille;
    }

    public Tuile[][] getGrille() {
        return grille;
    }

    public void setGrille(Tuile[][] grille) {
        this.grille = grille;
    }
    
    public Tuile getTuile(int i, int j){
        return grille[i][j];
    }
    
    public ArrayList<Tuile> getTuileAdj(Tuile tuile){
        
        ArrayList<Tuile> liste = new ArrayList();
        
        int i = 0;
        int j = 0;
        
        while (i < 6 && grille[i][j]!= tuile){
            j = 0;
            while ( j <6 && grille[i][j]!=tuile){
                j++;
            }
            i++;
        }
        
        liste.add(getTuile(i+1,j));
        liste.add(getTuile(i,j+1));
        liste.add(getTuile(i-1,j));
        liste.add(getTuile(i,j-1));
        
        return liste;
    }
    
        public ArrayList<Tuile> getTuileDiag(Tuile tuile){
        
        ArrayList<Tuile> liste = new ArrayList();
        
        int i = 0;
        int j = 0;
        
        while (i < 6 && grille[i][j]!= tuile){
            j = 0;
            while ( j <6 && grille[i][j]!=tuile){
                j++;
            }
            i++;
        }
        
        liste.add(getTuile(i+1,j+1));
        liste.add(getTuile(i+1,j-1));
        liste.add(getTuile(i-1,j+1));
        liste.add(getTuile(i-1,j-1));
        
        return liste;
    }
}
