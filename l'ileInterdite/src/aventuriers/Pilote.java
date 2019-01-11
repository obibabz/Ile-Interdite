/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aventuriers;

import aventuriers.Aventurier;
import java.util.ArrayList;
import l.ileinterdite.Grille;
import util.Utils.Pion;
import l.ileinterdite.Tuile;

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
