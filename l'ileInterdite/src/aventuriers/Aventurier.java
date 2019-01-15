/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aventuriers;

import l.ileinterdite.ObjetIdentifie;
import java.util.ArrayList;
import l.ileinterdite.Grille;
import l.ileinterdite.Tuile;
import cartes.CarteTirage;
import cartes.CarteTresor;
import java.util.HashMap;
import java.util.LinkedHashMap;
import util.Utils.Pion;
import util.Utils.EtatTuile;
/**
 *
 * @author rousstan
 */
public abstract class Aventurier extends ObjetIdentifie{
 
    
    private String nomJoueur;
    private Tuile position;
    private Pion pion;
    private LinkedHashMap<Integer, CarteTirage> cartesEnMain;
    private Integer nbAssech;

    public Aventurier(String nomJoueur, Tuile position, Pion pion) {
        this.nomJoueur = nomJoueur;
        this.position = position;
        this.pion = pion;
        this.cartesEnMain = new LinkedHashMap();
        this.nbAssech = 0;
    }

    public void setNbAssech(Integer nbAssech) {
        this.nbAssech = nbAssech;
    }

    public Integer getNbAssech() {
        return nbAssech;
    }

    public void addCartesEnMain(CarteTirage c){
        if(cartesEnMain.size()<5){
            cartesEnMain.put(c.getId(),c);
        }
        else{ 
            this.defausseCarte(c);
        }
    }

    public void defausseCarte(CarteTirage c){
        cartesEnMain.remove(id);
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
    
    public ArrayList<Integer> getTuilesAccessibles(Grille g){
        ArrayList<Integer> tuilesAdj = g.getTuileAdj(position.getId());
        ArrayList<Integer> tuilesAccess = g.getTuilesNonCoulees(tuilesAdj);
        return tuilesAccess;
    }
    
    public ArrayList<Integer> getTuilesAssechables(Grille g) {
        ArrayList<Integer> tuilesAdj = g.getTuileAdj(position.getId());
        ArrayList<Integer> tuilesAssech = g.getTuilesInondees(tuilesAdj);
        if(position.getEtatTuile() == EtatTuile.INONDEE) {tuilesAssech.add(position.getId());}
        
        return tuilesAssech;
    }
    
    public ArrayList<Integer> getTuileAccessiblesPouvoir(Grille g){
    ArrayList <Integer> tuiles = new ArrayList<>();
    return tuiles;
    }
    
    public ArrayList<Aventurier> getJoueursCiblables(Grille g){
        ArrayList <Aventurier> cibles = new ArrayList<>();
        this.getPosition().getJoueursSurTuile();
        cibles.remove(this);
        return cibles;
    }
    
    public HashMap<Integer,CarteTirage> getCartesTresor(){
        HashMap<Integer, CarteTirage> cartesDonnables = new HashMap<>();
        for(Integer key : cartesEnMain.keySet()){
            if ("CarteTresor".equals(cartesEnMain.get(key).getClass().getSimpleName())){
                cartesDonnables.put(key, cartesEnMain.get(key));
            }
        }
        return cartesDonnables;
    }

    public LinkedHashMap<Integer, CarteTirage> getCartesEnMain() {
        return cartesEnMain;
    }

    public void setCartesEnMain(LinkedHashMap<Integer, CarteTirage> cartesEnMain) {
        this.cartesEnMain = cartesEnMain;
    }

    public Pion getPion() {
        return pion;
    }
    

    
}
