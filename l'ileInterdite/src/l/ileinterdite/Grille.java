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
        
        while (i < 6 && grille[i][j] != tuile){
            j = 0;
            while ( j < 6 && grille[i][j]!=tuile){
                j++;
            }
            i++;
        }
        
        if (!"void".equals(getTuile(i, j+1).getNom())){
            liste.add(getTuile(i,j+1));
        }
        if (!"void".equals(getTuile(i, j-1).getNom())){
            liste.add(getTuile(i,j-1));
        }
        if (!"void".equals(getTuile(i-1, j).getNom())){
            liste.add(getTuile(i-1,j));
        }
        if (!"void".equals(getTuile(i-1, j).getNom())){
            liste.add(getTuile(i-1,j));
        }
        
        return liste;
    }
    
    public Tuile getTuile(String nom){
        Tuile t = null;
        for(int i = 0; i<=6; i++){
            for(int j = 0; j<=6; j++){
                
                if(getTuile(i,j).getNom()== nom ){t = getTuile(i,j);}
                
            }
        }
        return t;
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
        
        if (!"void".equals(getTuile(i+1, j+1).getNom())){
            liste.add(getTuile(i+1,j+1));
        }
        if (!"void".equals(getTuile(i+1, j-1).getNom())){
            liste.add(getTuile(i+1,j-1));
        }
        if (!"void".equals(getTuile(i-1, j+1).getNom())){
            liste.add(getTuile(i-1,j+1));
        }
        if (!"void".equals(getTuile(i-1, j-1).getNom())){
            liste.add(getTuile(i-1,j-1));
        }
                
        return liste;
    }
    
    public ArrayList<Tuile> getTuilesInondees(ArrayList<Tuile> tuiles){
        ArrayList<Tuile> tuilesInondees = new ArrayList();
        for(Tuile tuile : tuiles){
            if (tuile.getEtatTuile() == EtatTuile.INONDEE){
                tuilesInondees.add(tuile);
            }
        }
        return tuilesInondees;
    }
    
    public ArrayList<Tuile> getTuilesPasSeches(ArrayList<Tuile> tuiles){
        ArrayList<Tuile> tuilesPasSeches = new ArrayList();
        for(Tuile tuile : tuiles){
            if (tuile.getEtatTuile() != EtatTuile.NORMAL){
                tuilesPasSeches.add(tuile);
            }
        }
        return tuilesPasSeches;
    }
    
    public ArrayList<Tuile> getTuilesNonCoulees(ArrayList<Tuile> tuiles){
        ArrayList<Tuile> tuilesPasSeches = new ArrayList();
        for(Tuile tuile : tuiles){
            if (tuile.getEtatTuile() != EtatTuile.NOYEE){
                tuilesPasSeches.add(tuile);
            }
        }
        return tuilesPasSeches;
    }
}
