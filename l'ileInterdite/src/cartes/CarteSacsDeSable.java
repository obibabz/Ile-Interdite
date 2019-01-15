/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartes;

/**
 *
 * @author lienardr
 */
public class CarteSacsDeSable extends CarteTirage{
    private int test = 0;
    private final String nom = "Sac de Sable";

    public int getTest() {
        return test;
    }

    public String getNom() {
        return nom;
    }

    public CarteSacsDeSable() {
        super.setNom("Carte Sac de Sable");
    }
    
}
