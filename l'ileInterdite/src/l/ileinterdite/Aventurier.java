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
public abstract class Aventurier {
 
    
    private String nomJoueur;
    private Tuile position;
    private Pion pion;

    public Aventurier(String nomJoueur, Tuile position, Pion pion) {
        this.nomJoueur = nomJoueur;
        this.position = position;
        this.pion = pion;
    }



    public String getNomJoueur() {
        return nomJoueur;
    }


    public Tuile getPosition() {
        return position;
    }

    public void setPosition(Tuile position) {
        this.position = position;
    }
    
    public ArrayList<Tuile> getTuilesAccessibles(Grille g){
        ArrayList<Tuile> tuilesAdj = g.getTuileAdj(position);
        ArrayList<Tuile> tuilesAccess = g.getTuilesNonCoulees(tuilesAdj);
        return tuilesAccess;
    }
    
    public ArrayList<Tuile> getTuilesAssechables(Grille g) {
        ArrayList<Tuile> tuilesAdj = g.getTuileAdj(position);
        ArrayList<Tuile> tuilesAssech = g.getTuilesInondees(tuilesAdj);
        tuilesAssech.add(position);
        if(position.getEtatTuile() == EtatTuile.INONDEE) {tuilesAssech.add(position);}
        return tuilesAssech;
    }

    public Pion getPion() {
        return pion;
    }
    

    
}
