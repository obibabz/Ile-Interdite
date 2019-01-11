/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

import java.util.ArrayList;
import java.util.HashMap;
import util.Utils.Tresor;
import util.Utils.EtatTuile;

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
        
        while (i < 6 && j < 6 &&(grille[i][j].getNom() != tuile.getNom())){
            j = 0;
            while ( j < 5 && grille[i][j].getNom() !=tuile.getNom()){
                j++;
            }
            if(grille[i][j].getNom() != tuile.getNom()){
            i++;
            }
        }

        if (j != 5 && !"Void".equals(getTuile(i, j+1).getNom())){
            liste.add(getTuile(i,j+1));
        }
        if (j != 0 && !"Void".equals(getTuile(i, j-1).getNom())){
            liste.add(getTuile(i,j-1));
        }
        if (i != 0 &&!"Void".equals(getTuile(i-1, j).getNom())){
            liste.add(getTuile(i-1,j));
        }
        if (i != 5 && !"Void".equals(getTuile(i+1, j).getNom())){
            liste.add(getTuile(i+1,j));
        }
        
        return liste;
    }

    public ArrayList<Tuile> getTuileDiag(Tuile tuile){
        
        ArrayList<Tuile> liste = new ArrayList();
        
        int i = 0;
        int j = 0;
        
        while (i < 6 && j < 6 && grille[i][j].getNom() != tuile.getNom()){
            j = 0;
            while ( j <5 && grille[i][j].getNom()!=tuile.getNom()){
                j++;
            }
            if(grille[i][j].getNom() != tuile.getNom()){
            i++;
            }
        }
        
        if ( i != 5 && j !=5 && !"Void".equals(getTuile(i+1, j+1).getNom())){
            liste.add(getTuile(i+1,j+1));
        }
        if (i != 5 && j != 0 && !"Void".equals(getTuile(i+1, j-1).getNom())){
            liste.add(getTuile(i+1,j-1));
        }
        if (i != 0 && j != 5 && !"Void".equals(getTuile(i-1, j+1).getNom())){
            liste.add(getTuile(i-1,j+1));
        }
        if (i != 0 && j != 0 && !"Void".equals(getTuile(i-1, j-1).getNom())){
            liste.add(getTuile(i-1,j-1));
        }
                
        return liste;
    }
    
    public ArrayList<Tuile> getTuilePouvoirPilote(Tuile tuile){
        
        ArrayList<Tuile> tuilesPilote = new ArrayList();
        int i = 0;
        int j = 0;
        
        while (i < 6  ){
            j = 0;
            while ( j < 6 ){

                if (grille[i][j].getNom() != "Void" && grille[i][j].getEtatTuile() != EtatTuile.COULEE && tuile.getNom() != grille[i][j].getNom()){
                    tuilesPilote.add(grille[i][j]);

                }
                j++;
            }
            i++;
        }
        return tuilesPilote;
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
            if (tuile.getEtatTuile() != EtatTuile.ASSECHEE){
                tuilesPasSeches.add(tuile);
            }
        }
        return tuilesPasSeches;
    }
    
    public ArrayList<Tuile> getTuilesNonCoulees(ArrayList<Tuile> tuiles){
        ArrayList<Tuile> tuilesPasSeches = new ArrayList();
        for(Tuile tuile : tuiles){
            if (tuile.getEtatTuile() != EtatTuile.COULEE){
                tuilesPasSeches.add(tuile);
            }
        }
        return tuilesPasSeches;
    }
/*    
    public HashMap<Tresor, Integer> getTresorInondee(){
        HashMap<Tresor, Integer> listeTresorInondee = new HashMap();
        for(int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if(grille[i][j].getClass().getSimpleName().equals("Tresor")){
                   
                }
            }
        }
    }*/
}
