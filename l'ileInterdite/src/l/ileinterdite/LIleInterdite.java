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
import cartes.CarteSacsDeSable;
import cartes.CarteTirage;
import cartes.CarteTresor;
import vues.VueAventurier;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import util.MessageBox;
import util.Utils.Pion;
import vues.VueGrille;
import vues.VuePlateau;
import vues.VueTuile;
import util.Utils.EtatTuile;
import util.Utils.Tresor;
import vues.VueNiveau;

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
        
        
        
        Tuile V1 = new Tuile("Void", EtatTuile.ASSECHEE); V1.setEtatTuile(null);
        Tuile V2 = new Tuile("Void", EtatTuile.ASSECHEE); V2.setEtatTuile(null);
        Tuile V3 = new Tuile("Void", EtatTuile.ASSECHEE); V3.setEtatTuile(null);
        Tuile V4 = new Tuile("Void", EtatTuile.ASSECHEE); V4.setEtatTuile(null);
        Tuile V5 = new Tuile("Void", EtatTuile.ASSECHEE); V5.setEtatTuile(null);
        Tuile V6 = new Tuile("Void", EtatTuile.ASSECHEE); V6.setEtatTuile(null);
        Tuile V7 = new Tuile("Void", EtatTuile.ASSECHEE); V7.setEtatTuile(null);
        Tuile V8 = new Tuile("Void", EtatTuile.ASSECHEE); V8.setEtatTuile(null);
        Tuile V9 = new Tuile("Void", EtatTuile.ASSECHEE); V9.setEtatTuile(null);
        Tuile V10 = new Tuile("Void", EtatTuile.ASSECHEE); V10.setEtatTuile(null);
        Tuile V11 = new Tuile("Void", EtatTuile.ASSECHEE); V11.setEtatTuile(null);
        Tuile V12 = new Tuile("Void", EtatTuile.ASSECHEE); V12.setEtatTuile(null);
        Tuile PAb = new Tuile("Le Pont des Abîmes", EtatTuile.ASSECHEE); 
        Tuile PBr = new Tuile("La Porte de Bronze", EtatTuile.INONDEE); 
        Tuile COm = new Tuile("La Caverne des Ombres", EtatTuile.ASSECHEE); COm.setTresor(Tresor.CRISTAL);
        Tuile PFe = new Tuile("La Porte de Fer", EtatTuile.ASSECHEE);
        Tuile POr = new Tuile("La Porte d'Or", EtatTuile.ASSECHEE);
        Tuile FOu = new Tuile("Les Falaises de l'Oubli", EtatTuile.ASSECHEE);
        Tuile PCo = new Tuile("Le Palais de Corail", EtatTuile.ASSECHEE); PCo.setTresor(Tresor.CALICE);
        Tuile PAr = new Tuile("La Porte d'Argent", EtatTuile.ASSECHEE);
        Tuile DIl = new Tuile("Les Dunes de l'Illusion", EtatTuile.COULEE);
        Tuile H = new Tuile("Heliport", EtatTuile.ASSECHEE);
        Tuile PCu = new Tuile("La Porte de Cuivre", EtatTuile.ASSECHEE);
        Tuile JHu = new Tuile("Le Jardin des Hurlements", EtatTuile.ASSECHEE); JHu.setTresor(Tresor.ZEPHYR);
        Tuile FPo = new Tuile("La Forêt Pourpre", EtatTuile.ASSECHEE);
        Tuile LPe = new Tuile("Le Lagon Perdu", EtatTuile.INONDEE);
        Tuile MBr = new Tuile("Le Marais Brumeux", EtatTuile.COULEE);
        Tuile O = new Tuile("Observatoire", EtatTuile.INONDEE);
        Tuile RFa = new Tuile("Le Rocher Fantôme", EtatTuile.COULEE);
        Tuile CBr = new Tuile("La Caverne du Brasier", EtatTuile.INONDEE); CBr.setTresor(Tresor.CRISTAL);
        Tuile TSo = new Tuile("Le Temple du Soleil", EtatTuile.ASSECHEE); TSo.setTresor(Tresor.PIERRE);
        Tuile TLu = new Tuile("Le Temple de la Lune", EtatTuile.COULEE); TLu.setTresor(Tresor.PIERRE);
        Tuile PMa = new Tuile("Le Palais des Marées", EtatTuile.ASSECHEE); PMa.setTresor(Tresor.CALICE);
        Tuile VCr = new Tuile("Le Val du Crépuscule", EtatTuile.INONDEE);
        Tuile TGu = new Tuile("La Tour du Guet", EtatTuile.ASSECHEE);
        Tuile JMu = new Tuile("Le Jardin des Murmures", EtatTuile.INONDEE); JMu.setTresor(Tresor.ZEPHYR);
        
        LinkedHashMap<Integer, Tuile> listeTuiles = new LinkedHashMap<>();
        listeTuiles.put(PAb.getId(), PAb);
        listeTuiles.put(PBr.getId(), PBr);
        listeTuiles.put(COm.getId(), COm);
        listeTuiles.put(PFe.getId(), PFe);
        listeTuiles.put(POr.getId(), POr);
        listeTuiles.put(FOu.getId(), FOu);
        listeTuiles.put(PCo.getId(), PCo);
        listeTuiles.put(PAr.getId(), PAr);
        listeTuiles.put(DIl.getId(), DIl);
        listeTuiles.put(H.getId(), H);
        listeTuiles.put(PCu.getId(), PCu);
        listeTuiles.put(JHu.getId(), JHu);
        listeTuiles.put(FPo.getId(), FPo);
        listeTuiles.put(LPe.getId(), LPe);
        listeTuiles.put(MBr.getId(), MBr);
        listeTuiles.put(O.getId(), O);
        listeTuiles.put(RFa.getId(), RFa);
        listeTuiles.put(CBr.getId(), CBr);
        listeTuiles.put(TSo.getId(), TSo);
        listeTuiles.put(TLu.getId(), TLu);
        listeTuiles.put(PMa.getId(), PMa);
        listeTuiles.put(VCr.getId(), VCr);
        listeTuiles.put(TGu.getId(), TGu);
        listeTuiles.put(JMu.getId(), JMu);
        
        
        Grille grid = new Grille(listeTuiles);
        
        Pion rouge = Pion.ROUGE;
        Pion bleu = Pion.BLEU;
        Pion jaune = Pion.JAUNE;
        Pion orange = Pion.ORANGE;
        Pion vert = Pion.VERT;
        Pion violet = Pion.VIOLET;
        
        LinkedHashMap<Integer, CarteTirage> listeCartes = new LinkedHashMap<>();
        int i =0;
        while(i<=31){
            if(i<5){
                CarteTresor ct = new CarteTresor(Tresor.CALICE);
                listeCartes.put(ct.getId(), ct);
            }
            if(5<i && i<10){
                CarteTresor ct = new CarteTresor(Tresor.PIERRE);
                listeCartes.put(ct.getId(), ct);
            }
            if(10<i && i<15){
                CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
                listeCartes.put(ct.getId(), ct);
            }
            if(15<i && i<20){
                CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
                listeCartes.put(ct.getId(), ct);
            }
            if(20<i && i<25){
                CarteSacsDeSable ct = new CarteSacsDeSable();
                listeCartes.put(ct.getId(), ct);
            }
            
        }
        
        
        
        
        LinkedHashMap<Integer, Aventurier> joueursSurGrille = new LinkedHashMap();
        Aventurier joueur1 = new Ingenieur("joueur1", PBr, rouge); joueursSurGrille.put(joueur1.getId(), joueur1);PMa.arriveeJoueur(joueur1); joueur1.setPosition(PMa);
        Aventurier joueur2 = new Explorateur("joueur2", PCu, vert); joueursSurGrille.put(joueur2.getId(), joueur2);PCu.arriveeJoueur(joueur2);
        Aventurier joueur3 = new Plongeur("joueur3", PFe, violet); joueursSurGrille.put(joueur3.getId(), joueur3);PFe.arriveeJoueur(joueur3);
        Aventurier joueur4 = new Pilote("joueur4", H, bleu); joueursSurGrille.put(joueur4.getId(), joueur4);H.arriveeJoueur(joueur4);
        //Aventurier joueur5 = new Navigateur("joueur5", POr, jaune); joueursSurGrille.add(joueur5);
        //Aventurier joueur6 = new Messager("joueur6", PAr, orange); joueursSurGrille.add(joueur6);

        VueAventurier vue1 = new VueAventurier(joueur1.getId(),  joueur1.getNomJoueur(), joueur1.getClass().getSimpleName(), joueur1.getPion().getCouleur(), joueur1.getPion().getCouleurGrisee(), joueur1.getPosition().getNom()); vue1.setVueJCourant();
        VueAventurier vue2 = new VueAventurier(joueur2.getId(),  joueur2.getNomJoueur(), joueur2.getClass().getSimpleName(), joueur2.getPion().getCouleur(), joueur2.getPion().getCouleurGrisee(), joueur2.getPosition().getNom());
        VueAventurier vue3 = new VueAventurier(joueur3.getId(),  joueur3.getNomJoueur(), joueur3.getClass().getSimpleName(), joueur3.getPion().getCouleur(), joueur3.getPion().getCouleurGrisee(), joueur3.getPosition().getNom());
        VueAventurier vue4 = new VueAventurier(joueur4.getId(),  joueur4.getNomJoueur(), joueur4.getClass().getSimpleName(), joueur4.getPion().getCouleur(), joueur4.getPion().getCouleurGrisee(), joueur4.getPosition().getNom());
     
        LinkedHashMap<Integer, VueAventurier> vuesAventurier = new LinkedHashMap<>();
        vuesAventurier.put(joueur1.getId(), vue1);
        vuesAventurier.put(joueur2.getId(), vue2);
        vuesAventurier.put(joueur3.getId(), vue3);
        vuesAventurier.put(joueur4.getId(), vue4);
        
        Controleur controleur = new Controleur();

        controleur.setGrille(grid);
        controleur.setListeJoueurs(joueursSurGrille);
        controleur.setJCourant(joueur1);
        controleur.setNbActionsRestantes(3);
        
     
        // INNITIALISATION PLATEAU
        
        LinkedHashMap<Integer, VueTuile> vuesTuiles= new LinkedHashMap<>();
        for(Integer key : listeTuiles.keySet()){
            VueTuile vT = controleur.initVueTuile(listeTuiles.get(key));
            vuesTuiles.put(vT.getIdVueTuile(), vT);
        }
      
        MessageBox mb = new MessageBox();
        VueGrille vG = new VueGrille(vuesTuiles);
        
        
        VuePlateau vP = new VuePlateau(vG, vuesAventurier, mb, new VueNiveau(2));
        controleur.setVuePlateau(vP);
        vP.addObserver(controleur);
        vP.afficher();
        
        
    }
    
}
