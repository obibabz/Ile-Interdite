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
public class Pilote extends Aventurier{

    public Pilote(String nomJoueur , Tuile position, Pion pion) {
        super(nomJoueur, position, pion);
    }
    public ArrayList<Tuile> getTuileAccessiblesPouvoir(Grille g){
        return g.getTuilePouvoirPilote(getPosition());
    }
    
    
}
