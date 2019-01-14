/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import util.Utils.Tresor;
import util.Utils.EtatTuile;

/**
 *
 * @author rousstan
 */
public class Grille {
    private Tuile[][] grille = new Tuile[6][6];
    private LinkedHashMap<Integer, Tuile> listeTuiles;
    private final ArrayList<Integer>  casesNonAccess=new ArrayList<>(Arrays.asList(0, 1, 4, 5));
    private final ArrayList<Integer> casesAccess = new ArrayList<>(Arrays.asList(2, 3));

    public Grille(LinkedHashMap<Integer, Tuile> listeTuiles) {
        this.listeTuiles = listeTuiles;
        ArrayList<Integer> listeIdTuiles = new ArrayList<>();
        for(Integer key : listeTuiles.keySet()){
            listeIdTuiles.add(key);
        }
        int i =0;
        int j =0;
        int k =0;

        while(i<=5){
            while (j<=5){
                if(!(casesNonAccess.contains(i) && casesNonAccess.contains(j))){
                    grille[i][j] = listeTuiles.get(listeIdTuiles.get(k));
                    k++;
                }
                j++;
            }
            i++;
        }
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
    
    public ArrayList<Integer> getTuileAdj(Integer tuile){
        
        ArrayList<Integer> liste = new ArrayList();
        
        int i = 0;
        int j = 0;
        
        while (i < 6 &&  casesAccess.contains(i) && grille[i][j].getId() !=tuile){
            j = 0;
            while ( j < 5 && casesAccess.contains(j) && grille[i][j].getId() !=tuile){
                System.out.println(listeTuiles.get(grille[i][j].getId()));
                j++; 
            }
            if(grille[i][j].getId() != tuile){
            i++;
            }
        }

        if (j != 5 && grille[i][j+1] != null){
            liste.add(getTuile(i,j+1).getId());
        }
        if (j != 0 && grille[i][j-1] != null){
            liste.add(getTuile(i,j-1).getId());
        }
        if (i != 0 && grille[i-1][j]!= null){
            liste.add(getTuile(i-1,j).getId());
        }
        if (i != 5 && grille[i+1][j] != null){
            liste.add(getTuile(i+1,j).getId());
        }
        
        return liste;
    }

    public ArrayList<Integer> getTuileDiag(Integer tuile){
        
        ArrayList<Integer> liste = new ArrayList();
        
        int i = 0;
        int j = 0;
        
        while (i < 6 && j < 6 && grille[i][j].getId() != tuile){
            j = 0;
            while ( j <5 && grille[i][j].getId() !=tuile){
                j++;
            }
            if(grille[i][j].getId() != tuile){
            i++;
            }
        }
        
        if ( i != 5 && j !=5 && !"Void".equals(getTuile(i+1, j+1).getNom())){
            liste.add(getTuile(i+1,j+1).getId());
        }
        if (i != 5 && j != 0 && !"Void".equals(getTuile(i+1, j-1).getNom())){
            liste.add(getTuile(i+1,j-1).getId());
        }
        if (i != 0 && j != 5 && !"Void".equals(getTuile(i-1, j+1).getNom())){
            liste.add(getTuile(i-1,j+1).getId());
        }
        if (i != 0 && j != 0 && !"Void".equals(getTuile(i-1, j-1).getNom())){
            liste.add(getTuile(i-1,j-1).getId());
        }
                
        return liste;
    }
    
    public ArrayList<Integer> getTuilePouvoirPilote(Integer tuile){
        
        ArrayList<Integer> tuilesPilote = new ArrayList();
        int i = 0;
        int j = 0;
        
        while (i < 6  ){
            j = 0;
            while ( j < 6 ){

                if (grille[i][j].getNom() != "Void" && grille[i][j].getEtatTuile() != EtatTuile.COULEE && tuile != grille[i][j].getId()){
                    tuilesPilote.add(grille[i][j].getId());

                }
                j++;
            }
            i++;
        }
        return tuilesPilote;
    }

    public LinkedHashMap<Integer, Tuile> getListeTuiles() {
        return listeTuiles;
    }
    
    public ArrayList<Integer> getTuilesInondees(ArrayList<Integer> tuiles){
        ArrayList<Integer> tuilesInondees = new ArrayList();
        for(Integer key : tuiles){
            if (listeTuiles.get(key).getEtatTuile() == EtatTuile.INONDEE){
                tuilesInondees.add(key);
            }
        }
        return tuilesInondees;
    }
    
    public ArrayList<Integer> getTuilesPasSeches(ArrayList<Integer> tuiles){
        ArrayList<Integer> tuilesPasSeches = new ArrayList();
        for(Integer key : tuiles){
            if (listeTuiles.get(key).getEtatTuile() != EtatTuile.ASSECHEE){
                tuilesPasSeches.add(key);
            }
        }
        return tuilesPasSeches;
    }
    
    public ArrayList<Integer> getTuilesNonCoulees(ArrayList<Integer> tuiles){
        ArrayList<Integer> tuilesPasSeches = new ArrayList();
        for(Integer key : tuiles){
            if (listeTuiles.get(key).getEtatTuile() != EtatTuile.COULEE){
                tuilesPasSeches.add(key);
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
