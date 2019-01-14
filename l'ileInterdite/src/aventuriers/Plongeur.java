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
public class Plongeur extends Aventurier{

    public Plongeur(String nomJoueur, Tuile position, Pion pion) {
        super(nomJoueur, position, pion);
    }
    
    @Override
   public ArrayList<Integer> getTuilesAccessibles(Grille g){
       int i = 0;
       
       ArrayList <Integer> tuilesAdj = g.getTuileAdj(super.getPosition().getId());
       ArrayList <Integer> tuilesAccess = g.getTuilesNonCoulees(tuilesAdj);
       ArrayList <Integer> tuilesAdjPS = g.getTuilesPasSeches(tuilesAdj);
       
       while(i < tuilesAdjPS.size()){
           ArrayList <Integer> tAdj = g.getTuileAdj(tuilesAdjPS.get(i));
           ArrayList <Integer> tAdjPC = g.getTuilesNonCoulees(tAdj);
           for(Integer t2 : tAdjPC){
               if(tuilesAccess.contains(t2) == false && t2 != super.getPosition().getId()){
                   tuilesAccess.add(t2);
               }
           }
           ArrayList <Integer> tAdjPS = g.getTuilesPasSeches(tAdj);
           for(Integer t3 : tAdjPS){
               if(tuilesAdjPS.contains(t3) == false){
                   tuilesAdjPS.add(t3);
               }
           }
           i++;
       }
       
       return tuilesAccess;
   }
        
    
    
}
