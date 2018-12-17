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
public class Explorateur extends Aventurier{

    public Explorateur(String nomJoueur, Tuile position, Pion pion) {
        super(nomJoueur, position, pion);
    }
    
    public ArrayList <Tuile> getTuilesAssechables(Grille g){
        
        ArrayList <Tuile> tuilesAdj = g.getTuileAdj(super.getPosition());
        tuilesAdj.addAll(g.getTuileDiag(super.getPosition()));
        return tuilesAdj;
    }
    
    
}
