/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes;
import util.Utils.Tresor;
/**
 *
 * @author lienardr
 */
public class CarteTresor extends CarteTirage{
    private Tresor tresor;

    public CarteTresor(Tresor tresor) {
        this.tresor = tresor;
        super.setNom(tresor.toString());
    }

    public Tresor getTresor() {
        return tresor;
    }

    public void setTresor(Tresor tresor) {
        this.tresor = tresor;
    }
}
