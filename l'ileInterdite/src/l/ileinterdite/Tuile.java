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
public class Tuile {
    private NomTuile nom;
    private EtatTuile etatTuile;
    private ArrayList<Aventurier> joueursSurGrille = new ArrayList();

    public Tuile(NomTuile nom, EtatTuile etatTuile, ArrayList<Aventurier> joueursSurGrille) {
        this.nom = nom;
        this.etatTuile = etatTuile;
        this.joueursSurGrille = joueursSurGrille;
    }

    public EtatTuile getEtatTuile() {
        return etatTuile;
    }

    public ArrayList<Aventurier> arriveJoueur() {
        return joueursSurGrille;
    }

    public void setEtatTuile(EtatTuile etatTuile) {
        this.etatTuile = etatTuile;
    }

    public void arriveeJoueur(Aventurier joueur) {
        this.joueursSurGrille.add(joueur);
    }
    
    public void departJoueur(Aventurier joueur) {
        this.joueursSurGrille.remove(joueur);
    }

    public Tuile(NomTuile nom) {
        this.nom = nom;
    }
    
    
}
