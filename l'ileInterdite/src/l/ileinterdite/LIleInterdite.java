/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l.ileinterdite;

import aventuriers.Navigateur;
import aventuriers.Plongeur;
import aventuriers.Ingenieur;
import aventuriers.Messager;
import aventuriers.Pilote;
import aventuriers.Explorateur;
import aventuriers.Aventurier;
import vues.VueAventurier;
import java.awt.Color;
import java.util.ArrayList;
import util.Utils.Pion;

/**
 *
 * @author poensing
 */
public class LIleInterdite {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        Tuile V1 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V1.setEtatTuile(null);
        Tuile V2 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V2.setEtatTuile(null);
        Tuile V3 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V3.setEtatTuile(null);
        Tuile V4 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V4.setEtatTuile(null);
        Tuile V5 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V5.setEtatTuile(null);
        Tuile V6 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V6.setEtatTuile(null);
        Tuile V7 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V7.setEtatTuile(null);
        Tuile V8 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V8.setEtatTuile(null);
        Tuile V9 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V9.setEtatTuile(null);
        Tuile V10 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V10.setEtatTuile(null);
        Tuile V11 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V11.setEtatTuile(null);
        Tuile V12 = new Tuile("Void", l.ileinterdite.EtatTuile.NORMAL); V12.setEtatTuile(null);
        Tuile PAb = new Tuile("Le Pont des Abîmes", l.ileinterdite.EtatTuile.NORMAL); 
        Tuile PBr = new Tuile("La Porte de Bronze", l.ileinterdite.EtatTuile.INONDEE);
        Tuile COm = new Tuile("La Caverne des Ombres", l.ileinterdite.EtatTuile.NORMAL);
        Tuile PFe = new Tuile("La Porte de Fer", l.ileinterdite.EtatTuile.NORMAL);
        Tuile POr = new Tuile("La Porte d'Or", l.ileinterdite.EtatTuile.NORMAL);
        Tuile FOu = new Tuile("Les Falaises de l'Oubli", l.ileinterdite.EtatTuile.NORMAL);
        Tuile PCo = new Tuile("Le Palais de Corail", l.ileinterdite.EtatTuile.NORMAL);
        Tuile PAr = new Tuile("La Porte d'Argent", l.ileinterdite.EtatTuile.NORMAL);
        Tuile DIl = new Tuile("Les Dunes de l'Illusion", l.ileinterdite.EtatTuile.NOYEE);
        Tuile H = new Tuile("Heliport", l.ileinterdite.EtatTuile.NORMAL);
        Tuile PCu = new Tuile("La Porte de Cuivre", l.ileinterdite.EtatTuile.NORMAL);
        Tuile JHu = new Tuile("Le Jardin des Hurlements", l.ileinterdite.EtatTuile.NORMAL);
        Tuile FPo = new Tuile("La Forêt Pourpre", l.ileinterdite.EtatTuile.NORMAL);
        Tuile LPe = new Tuile("Le Lagon Perdu", l.ileinterdite.EtatTuile.INONDEE);
        Tuile MBr = new Tuile("Le Marais Brumeux", l.ileinterdite.EtatTuile.NOYEE);
        Tuile O = new Tuile("Observatoire", l.ileinterdite.EtatTuile.INONDEE);
        Tuile RFa = new Tuile("Le Rocher Fantôme", l.ileinterdite.EtatTuile.NOYEE);
        Tuile CBr = new Tuile("La Caverne du Brasier", l.ileinterdite.EtatTuile.INONDEE);
        Tuile TSo = new Tuile("Le Temple du Soleil", l.ileinterdite.EtatTuile.NORMAL);
        Tuile TLu = new Tuile("Le Temple de la Lune", l.ileinterdite.EtatTuile.NOYEE);
        Tuile PMa = new Tuile("Le Palais des Marées", l.ileinterdite.EtatTuile.NORMAL);
        Tuile VCr = new Tuile("Le Val du Crépuscule", l.ileinterdite.EtatTuile.NORMAL);
        Tuile TGu = new Tuile("La Tour du Guet", l.ileinterdite.EtatTuile.NORMAL);
        Tuile JMu = new Tuile("Le Jardin des Murmures", l.ileinterdite.EtatTuile.INONDEE);
        
        Tuile[][] grille = new Tuile[6][6];

        grille[0][0] = V1;
        grille[0][1] = V2;
        grille[0][2] = PAb;
        grille[0][3] = PBr;
        grille[0][4] = V3;
        grille[0][5] = V4;
        grille[1][0] = V5;
        grille[1][1] = COm;
        grille[1][2] = PFe;
        grille[1][3] = POr;
        grille[1][4] = FOu;
        grille[1][5] = V6;
        grille[2][0] = PCo;
        grille[2][1] = PAr;
        grille[2][2] = DIl;
        grille[2][3] = H;
        grille[2][4] = PCu;
        grille[2][5] = JHu;
        grille[3][0] = FPo;
        grille[3][1] = LPe;
        grille[3][2] = MBr;
        grille[3][3] = O;
        grille[3][4] = RFa;
        grille[3][5] = CBr;
        grille[4][0] = V7;
        grille[4][1] = TSo;
        grille[4][2] = TLu;
        grille[4][3] = PMa;
        grille[4][4] = VCr;
        grille[4][5] = V8;
        grille[5][0] = V9;
        grille[5][1] = V10;
        grille[5][2] = TGu;
        grille[5][3] = JMu;
        grille[5][4] = V11;
        grille[5][5] = V12;
        
        Grille grid = new Grille(grille);
        
        Pion rouge = Pion.ROUGE;
        Pion bleu = Pion.BLEU;
        Pion jaune = Pion.JAUNE;
        Pion orange = Pion.ORANGE;
        Pion vert = Pion.VERT;
        Pion violet = Pion.VIOLET;
        
        
        ArrayList<Aventurier> joueursSurGrille = new ArrayList<Aventurier>();
        Aventurier joueur1 = new Ingenieur("joueur1", PBr, rouge); joueursSurGrille.add(joueur1);
        Aventurier joueur2 = new Explorateur("joueur2", PCu, vert); joueursSurGrille.add(joueur2);
        Aventurier joueur3 = new Plongeur("joueur3", PFe, violet); joueursSurGrille.add(joueur3);
        Aventurier joueur4 = new Pilote("joueur4", H, bleu); joueursSurGrille.add(joueur4);
        //Aventurier joueur5 = new Navigateur("joueur5", POr, jaune); joueursSurGrille.add(joueur5);
        //Aventurier joueur6 = new Messager("joueur6", PAr, orange); joueursSurGrille.add(joueur6);
        
        
       /* for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.println(grille[j][i].getNom());  
            }
        }
        */
        VueAventurier vue1 = new VueAventurier(0, joueursSurGrille.indexOf(joueur1), joueur1.getNomJoueur(), joueur1.getClass().getSimpleName(), joueur1.getPion().getCouleur(), joueur1.getPion().getCouleurGrisee(), joueur1.getPosition().getNom());
        VueAventurier vue2 = new VueAventurier(0, joueursSurGrille.indexOf(joueur2), joueur2.getNomJoueur(), joueur2.getClass().getSimpleName(), joueur2.getPion().getCouleur(), joueur2.getPion().getCouleurGrisee(), joueur2.getPosition().getNom());
        VueAventurier vue3 = new VueAventurier(0, joueursSurGrille.indexOf(joueur3), joueur3.getNomJoueur(), joueur3.getClass().getSimpleName(), joueur3.getPion().getCouleur(), joueur3.getPion().getCouleurGrisee(), joueur3.getPosition().getNom());
        VueAventurier vue4 = new VueAventurier(0, joueursSurGrille.indexOf(joueur4), joueur4.getNomJoueur(), joueur4.getClass().getSimpleName(), joueur4.getPion().getCouleur(), joueur4.getPion().getCouleurGrisee(), joueur4.getPosition().getNom());
        vue1.afficher();
        vue2.afficher();
        vue3.afficher();
        vue4.afficher();
        
        ArrayList<VueAventurier> vuesAventurier = new ArrayList<>();
        vuesAventurier.add(vue1);
        vuesAventurier.add(vue2);
        vuesAventurier.add(vue3);
        vuesAventurier.add(vue4);
        
        Controleur controleur = new Controleur();

        controleur.setGrille(grid);
        controleur.setListeJoueurs(joueursSurGrille);
        controleur.setJCourant(joueur1);
        controleur.setNbActionsRestantes(3);
        controleur.setVuesAventuriers(vuesAventurier);
        vue1.addObserver(controleur);
        vue2.addObserver(controleur);
        vue3.addObserver(controleur);
        vue4.addObserver(controleur);
       
    }
    
}
