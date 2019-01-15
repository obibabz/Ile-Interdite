/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

import aventuriers.Aventurier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import util.Utils.Tresor;
import util.Utils.EtatTuile;

/**
 *
 * @author rousstan
 */
public class Tuile extends ObjetIdentifie{
    private String nom;
    private EtatTuile etatTuile;
    private LinkedHashMap<Integer, Aventurier> joueursSurTuile = new LinkedHashMap();
    private Tresor tresor;

    public Tuile(String nom, EtatTuile etatTuile) {
        this.nom = nom;
        this.etatTuile = etatTuile;
        
        
    }

    public EtatTuile getEtatTuile() {
        return etatTuile;
    }

    public LinkedHashMap<Integer, Aventurier> getJoueursSurTuile() {
        return joueursSurTuile;
    }

    public String getNom() {
        return nom;
    }

    public Tresor getTresor() {
        return tresor;
    }

    public void setJoueursSurTuile(LinkedHashMap<Integer, Aventurier> joueursSurTuile) {
        this.joueursSurTuile = joueursSurTuile;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
    

    public void setEtatTuile(EtatTuile etatTuile) {
        this.etatTuile = etatTuile;
    }

    public void arriveeJoueur( Aventurier joueur) {
        this.joueursSurTuile.put(joueur.getId(), joueur);
    }
    
    public void departJoueur(Integer idJoueur) {
        this.joueursSurTuile.remove(idJoueur);
    }

    public Tuile(String nom) {
        this.nom = nom;
    }
    
    
}
