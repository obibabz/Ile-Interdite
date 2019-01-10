/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;
import util.Utils.Tresor;
/**
 *
 * @author lienardr
 */
public class TuileTresor extends Tuile{
    private Tresor tresor;

    public TuileTresor(String nom, EtatTuile etatTuile, Tresor tresor) {
        super(nom, etatTuile);
        this.tresor = tresor;
    }



    public Tresor getTresor() {
        return tresor;
    }
    
    
}
