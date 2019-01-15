/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes;

import l.ileinterdite.Tuile;
import l.ileinterdite.ObjetIdentifie;

/**
 *
 * @author lienardr
 */
public class CarteInondation extends ObjetIdentifie{
    private Tuile tuile;

    public CarteInondation(Tuile tuile) {
        this.tuile = tuile;
        super.id = this.tuile.getId();
    }

}    
