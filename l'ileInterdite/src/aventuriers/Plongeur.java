/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aventuriers;

import aventuriers.Aventurier;
import java.util.ArrayList;
import l.ileinterdite.Grille;
import l.ileinterdite.Pion;
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
   public ArrayList<Tuile> getTuilesAccessibles(Grille g){
       int i = 0;
       
       ArrayList <Tuile> tuilesAdj = g.getTuileAdj(super.getPosition());
       ArrayList <Tuile> tuilesAccess = g.getTuilesNonCoulees(tuilesAdj);
       ArrayList <Tuile> tuilesAdjPS = g.getTuilesPasSeches(tuilesAdj);
       
       while(i < tuilesAdjPS.size()){
           ArrayList <Tuile> tAdj = g.getTuileAdj(tuilesAdjPS.get(i));
           ArrayList <Tuile> tAdjPC = g.getTuilesNonCoulees(tAdj);
           for(Tuile t2 : tAdjPC){
               if(tuilesAccess.contains(t2) == false && t2 != super.getPosition()){
                   tuilesAccess.add(t2);
               }
           }
           ArrayList <Tuile> tAdjPS = g.getTuilesPasSeches(tAdj);
           for(Tuile t3 : tAdjPS){
               if(tuilesAdjPS.contains(t3) == false){
                   tuilesAdjPS.add(t3);
               }
           }
           i++;
       }
       
       return tuilesAccess;
   }
        
    
    
}
