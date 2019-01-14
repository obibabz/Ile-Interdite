/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

import aventuriers.Aventurier;
import java.util.ArrayList;
import util.Utils.Tresor;
import util.Utils.EtatTuile;

/**
 *
 * @author rousstan
 */
public class Tuile extends ObjetIdentifie{
    private String nom;
    private EtatTuile etatTuile;
    private ArrayList<Aventurier> joueursSurTuile = new ArrayList();
    private Tresor tresor;

    public Tuile(String nom, EtatTuile etatTuile) {
        this.nom = nom;
        this.etatTuile = etatTuile;
        
        
    }

    public EtatTuile getEtatTuile() {
        return etatTuile;
    }

    public ArrayList<Aventurier> getJoueursSurTuile() {
        return joueursSurTuile;
    }

    public String getNom() {
        return nom;
    }

    public Tresor getTresor() {
        return tresor;
    }

    public void setJoueursSurTuile(ArrayList<Aventurier> joueursSurTuile) {
        this.joueursSurTuile = joueursSurTuile;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
    

    public void setEtatTuile(EtatTuile etatTuile) {
        this.etatTuile = etatTuile;
    }

    public void arriveeJoueur(Aventurier joueur) {
        System.out.println("Arrivée sur la tuile"+this.nom);
        this.joueursSurTuile.add(joueur);
    }
    
    public void departJoueur(Aventurier joueur) {
        System.out.println("Départ de la tuile"+this.nom);
        this.joueursSurTuile.remove(joueur);
    }

    public Tuile(String nom) {
        this.nom = nom;
    }
    
    
}
