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
public class Explorateur extends Aventurier{

    public Explorateur(String nomJoueur, Tuile position, Pion pion) {
        super(nomJoueur, position, pion);
    }
    
    public ArrayList <Integer> getTuilesAssechables(Grille g){
        
        ArrayList <Integer> tuilesAdj = g.getTuileAdj(super.getPosition().getId());
        tuilesAdj.addAll(g.getTuileDiag(super.getPosition().getId()));
        return g.getTuilesInondees(tuilesAdj);
    }
    
    public ArrayList <Integer> getTuilesAccessibles(Grille g){
        ArrayList <Integer> tuilesAdj = g.getTuileAdj(super.getPosition().getId());
        tuilesAdj.addAll(g.getTuileDiag(super.getPosition().getId()));
        return g.getTuilesNonCoulees(tuilesAdj);
        
    }
    
    
}
