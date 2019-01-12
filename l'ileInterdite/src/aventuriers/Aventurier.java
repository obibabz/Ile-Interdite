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
    private HashMap<Integer, CarteTirage> cartesEnMain;

    public Aventurier(String nomJoueur, Tuile position, Pion pion) {
        this.nomJoueur = nomJoueur;
        this.position = position;
        this.pion = pion;
        this.cartesEnMain = new HashMap();
    }

    public HashMap<Integer,CarteTirage> getCartesEnMain() {
        return cartesEnMain;
    }
    
    public void addCartesEnMain(CarteTirage c){
        cartesEnMain.put(c.getId(),c);
    }
    public void retirerCarte(Integer id){
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
    
    public ArrayList<Tuile> getTuilesAccessibles(Grille g){
        ArrayList<Tuile> tuilesAdj = g.getTuileAdj(position);
        ArrayList<Tuile> tuilesAccess = g.getTuilesNonCoulees(tuilesAdj);
        return tuilesAccess;
    }
    
    public ArrayList<Tuile> getTuilesAssechables(Grille g) {
        ArrayList<Tuile> tuilesAdj = g.getTuileAdj(position);
        ArrayList<Tuile> tuilesAssech = g.getTuilesInondees(tuilesAdj);
        if(position.getEtatTuile() == EtatTuile.INONDEE) {tuilesAssech.add(position);}
        return tuilesAssech;
    }
    
    public ArrayList<Tuile> getTuileAccessiblesPouvoir(Grille g){
    ArrayList <Tuile> tuiles = new ArrayList<>();
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

    public Pion getPion() {
        return pion;
    }
    

    
}
