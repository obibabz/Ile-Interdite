/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

/**
 *
 * @author rousstan
 */
public abstract class Aventurier {
 
    
    String nomJoueur;
    Tuile position;

    public Aventurier(String nomJoueur , Tuile position) {
        this.nomJoueur = nomJoueur;
        this.position = position;
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
    
    public void getTuilesAccessibles(Grille g){
        
    }
    
    public void getTuilesAssechables(Grille g) {
        
    }
    

    
}
