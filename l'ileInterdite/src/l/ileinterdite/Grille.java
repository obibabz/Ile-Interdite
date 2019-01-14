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
            j =0;
            while (j<=5){
                
                System.out.println("i : "+i);
                System.out.println("j : "+j);
                if(!(casesNonAccess.contains(i) || casesNonAccess.contains(j)) && (i==1 && j==1) && (i==1 && j==4) && (i==4 && j==1) && (i==4 && j==4)){
                    grille[i][j] = listeTuiles.get(listeIdTuiles.get(k));
                    k++;
                   System.out.println("case assignée : " + grille[i][j].getId() + " = " + listeIdTuiles.get(k-1));
                   System.out.println();
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
    
    public ArrayList<Integer> getTuileAdj(Integer idTuile){
        
        ArrayList<Integer> liste = new ArrayList();
        int iTuile =0;
        int jTuile =0;
        
        //On cherche la tuile passée en parametre via son id
        for(int i = 0; i<6; i++){
            for(int j = 0; j<6; j++){
                if(casesAccess.contains(i) || casesAccess.contains(j)){
                    if(grille[i][j].getId() == idTuile){
                        iTuile = i;
                        jTuile = j;
                    }
                }
            }
        }
        
        //System.out.println(grille[iTuile][jTuile].getId() + "est a la position :" + iTuile + jTuile);
        
        if (iTuile != 0 && grille[iTuile-1][jTuile]!= null){
            liste.add(getTuile(iTuile-1,jTuile).getId());
            System.out.println("Tuile au dessus add");
        }
        if (iTuile != 5 && grille[iTuile+1][jTuile] != null){
            liste.add(getTuile(iTuile+1,jTuile).getId());
            System.out.println("Tuile en dessous add");
        }
        if (jTuile != 0 && grille[iTuile][jTuile-1] != null){
            liste.add(getTuile(iTuile,jTuile-1).getId());
            System.out.println("Tuile a gauche add");
        }
        if (jTuile != 5 && grille[iTuile][jTuile+1] != null){
            liste.add(getTuile(iTuile,jTuile+1).getId());
            System.out.println("Tuile a droite add");
        }
        
        
        for(Integer i : liste){
            System.out.println("case "+i);
        }
        
        return liste;
    }

    public ArrayList<Integer> getTuileDiag(Integer idTuile){
        
        ArrayList<Integer> liste = new ArrayList();
        
        /*int i = 0;
        int j = 0;
        
        while (i < 5 && j < 5 && grille[i][j].getId() != tuile){
            j = 0;
            while (j < 5 && grille[i][j].getId() !=tuile){
                j++;
            }
            if(grille[i][j].getId() != tuile){
            i++;
            }
        }*/
        
        int iTuile =0;
        int jTuile =0;
        for(int i = 0; i<6; i++){
            for(int j = 0; j<6; j++){
                if(casesAccess.contains(i) || casesAccess.contains(j)){
                    //System.out.println("i : "+i);
                    //System.out.println("j : "+j);
                    if(grille[i][j].getId() == idTuile){
                        iTuile = i;
                        jTuile = j;
                    }
                }
            }
        }
        
        /*if ( iTuile != 5 && jTuile !=5 && !"Void".equals(getTuile(iTuile+1, jTuile+1).getNom())){
            liste.add(getTuile(iTuile+1,jTuile+1).getId());
        }
        if (iTuile != 5 && jTuile != 0 && !"Void".equals(getTuile(iTuile+1, jTuile-1).getNom())){
            liste.add(getTuile(iTuile+1,jTuile-1).getId());
        }
        if (iTuile != 0 && jTuile != 5 && !"Void".equals(getTuile(iTuile-1, jTuile+1).getNom())){
            liste.add(getTuile(iTuile-1,jTuile+1).getId());
        }
        if (iTuile != 0 && jTuile != 0 && !"Void".equals(getTuile(iTuile-1, jTuile-1).getNom())){
            liste.add(getTuile(iTuile-1,jTuile-1).getId());
        }*/
        
        if (iTuile != 0 && grille[iTuile-1][jTuile+1] != null){         // i sort du tableau en négatif
            liste.add(getTuile(iTuile-1,jTuile+1).getId());
        }
        
        if (jTuile != 0 && grille[iTuile-1][jTuile-1] != null){         // j sort du tableau en négatif
            liste.add(getTuile(iTuile-1,jTuile-1).getId());
        }
        
        if (iTuile != 5 && grille[iTuile+1][jTuile+1] != null){         // i sort du tableau (>5)
            liste.add(getTuile(iTuile+1,jTuile+1).getId());
        }
        
        if (jTuile != 0 && grille[iTuile-1][jTuile-1] != null){         //j sort du tableau (>5)
            liste.add(getTuile(iTuile-1,jTuile-1).getId());
        }
        
        if (jTuile != 0 && jTuile != 5 && grille[iTuile+1][jTuile+1] != null){
            liste.add(getTuile(iTuile+1,jTuile-1).getId());
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
