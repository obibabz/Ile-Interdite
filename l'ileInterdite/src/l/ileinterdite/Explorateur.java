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

    public Explorateur(String nomJoueur, Tuile position) {
        super(nomJoueur, position);
    }
    
    public ArrayList <Tuile> getTuilesAssechables(Grille g){
        
        ArrayList <Tuile> tuilesAdj = g.getTuileAdj(position);
        
    }
    
    
}
