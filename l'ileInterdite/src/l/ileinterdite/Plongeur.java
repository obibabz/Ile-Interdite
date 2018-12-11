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
public class Plongeur extends Aventurier{

    public Plongeur(String nomJoueur, Tuile position) {
        super(nomJoueur, position);
    }
    
    @Override
   public ArrayList<Tuile> getTuilesAccessibles(Grille g){
       
       ArrayList <Tuile> tuilesAdj = g.getTuileAdj(position);
       ArrayList <Tuile> tuilesAccess = g.getTuilesNonCoulees(tuilesAdj);
       ArrayList <Tuile> tuilesAdjPS = g.getTuilesPasSeches(tuilesAdj);
       
       for(Tuile t : tuilesAdjPS){
           ArrayList <Tuile> tAdj = g.getTuileAdj(t);
           ArrayList <Tuile> tAdjPC = g.getTuilesNonCoulees(tAdj);
           for(Tuile t2 : tAdjPC){
               if(tuilesAccess.contains(t2) == false && t2 != position){
                   tuilesAccess.add(t2);
               }
           }
           ArrayList <Tuile> tAdjPS = g.getTuilesPasSeches(tAdj);
           for(Tuile t3 : tAdjPS){
               if(tuilesAdjPS.contains(t3) == false){
                   tuilesAdjPS.add(t3);
               }
           }
       }
       
       return tuilesAccess;
   }
        
    
    
}
