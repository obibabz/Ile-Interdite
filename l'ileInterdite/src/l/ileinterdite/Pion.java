/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

/**
 *
 * @author rousstan
 */
      
import java.awt.Color;

public enum Pion {
    ROUGE("Rouge", new Color(255, 0, 0)),
    VERT("Vert", new Color(0, 195, 0)),
    BLEU("Bleu", new Color(55,194,198)),
    ORANGE("Orange", new Color(255, 148, 0)),
    VIOLET("Violet", new Color(204, 94, 255)),
    JAUNE("Jaune", new Color(255, 255, 0)) ;    

        private final String libelle ;
        private final Color couleur ;


        Pion (String libelle, Color couleur) {
            this.libelle = libelle ;
            this.couleur = couleur ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }
        

        static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) return ROUGE ;
            if (VERT.name().equals(name)) return VERT ;
            if (BLEU.name().equals(name)) return BLEU ;
            if (ORANGE.name().equals(name)) return ORANGE ;
            if (VIOLET.name().equals(name)) return VIOLET ;
            if (JAUNE.name().equals(name)) return JAUNE ;
            return null ;
        }
    
}
