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
        

import aventuriers.Aventurier;
import vues.VueAventurier;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import cartes.CarteTirage;
import cartes.CarteInondation;
import java.awt.Color;
import java.util.LinkedHashMap;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.border.MatteBorder;
import util.Message;
import util.Utils;
import util.Utils.Tresor;
import util.Utils.Commandes;
import vues.VueNiveau;
import vues.VuePlateau;
import vues.VueTuile;
import util.Utils.EtatTuile;

        

public class Controleur implements Observer{
    private LinkedHashMap <Integer, Aventurier> listeJoueurs;
    private int nbActionsRestantes;
    private Aventurier JCourant;
    private Grille grille;
    private ArrayList <CarteInondation> piocheInondation;
    private ArrayList <CarteInondation> defausseInondation;
    private ArrayList<Tresor> tresorPossede;
    private int niveauInond;
    private LinkedHashMap< Integer, CarteTirage> piocheTirage;
    private LinkedHashMap<Integer, CarteTirage> defausseTirage;
    
    
    private VuePlateau vuePlateau;
    
    

    /**
     *
     * @param listeTuiles
     */
    
    /*public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }*/

    
    /**case7.getEtat() == EtatCase.O
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }    

    public int getNbActionsRestantes() {
        return nbActionsRestantes;
    }

    public void setNbActionsRestantes(int nbActionsRestantes) {
        this.nbActionsRestantes = nbActionsRestantes;
    }

    public Grille getGrille(){
        return (null);
    }
         
    public void setGrille(Grille grille) {
        this.grille = grille;
    }


    public void setVuePlateau(VuePlateau vuePlateau) {
        this.vuePlateau = vuePlateau;
    }
    
    public void setJCourant(Aventurier JCourant) {
        this.JCourant = JCourant;
    }

    public void setListeJoueurs(LinkedHashMap<Integer, Aventurier> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }
    
    public void joueurSuivant(){
        ArrayList<Integer> listeId = new ArrayList<>();
        for(Integer key : this.listeJoueurs.keySet()){
            listeId.add(key);
        }
        if(listeId.indexOf(JCourant.getId()) < listeId.size()-1 ){   
            setJCourant(listeJoueurs.get(listeId.get(listeId.indexOf(JCourant.getId())+1 )));
        }
        else{setJCourant(listeJoueurs.get(listeId.get(0)));}
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message) {
            
            int idJoueur =((Message) arg).getIdAventurier();
            Color couleur1 = listeJoueurs.get(idJoueur).getPion().getCouleurSelectionAssechee();
            Color couleur2 =listeJoueurs.get(idJoueur).getPion().getCouleurSelectionInondee();
            
            
            if (((Message) arg).getCommande() == Commandes.BOUGER) { 
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAccessibles(grille);
                if(!listeIdTuiles.isEmpty()){
                    this.vuePlateau.setTuilesDeplacement(listeIdTuiles, idJoueur, couleur1, couleur2);
                    vuePlateau.getMessageBox().displayMessage("Vous pouvez vous déplacer vers : <br/>" +listeIdTuilesToString(listeIdTuiles), couleur1, Boolean.TRUE, Boolean.TRUE);
                    vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueChoix();
                }
                else{
                    vuePlateau.getMessageBox().displayMessage("Vous ne pouvez vous déplacer nul-part." +listeIdTuilesToString(listeIdTuiles), couleur1, Boolean.TRUE, Boolean.TRUE);
                }
            }
            else if (((Message) arg).getCommande() == Commandes.ASSECHER) {
                    ArrayList<Integer>listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                    
                    if(!listeIdTuiles.isEmpty()){
                        this.vuePlateau.setTuilesAssechement(listeIdTuiles, idJoueur, couleur1, couleur2  );
                        vuePlateau.getMessageBox().displayMessage("Vous pouvez assécher : <br/>" +listeIdTuilesToString(listeIdTuiles), couleur1, Boolean.TRUE, Boolean.TRUE);
                        vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueChoix();
                    }
                    else{
                        vuePlateau.getMessageBox().displayMessage("Il n'y a pas de tuile asséchable adjacente" +listeIdTuilesToString(listeIdTuiles), couleur1, Boolean.TRUE, Boolean.TRUE);
                    }
                    
            }
            else if (((Message) arg).getCommande() == Commandes.CHOISIR_TUILE_D){
                int idTuile =((Message) arg).getIdTuile();
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAccessibles(grille);
                this.vuePlateau.setTuilesDefaut(listeIdTuiles);
                gererDeplacement(idTuile, idJoueur);
                vuePlateau.getMessageBox().displayMessage("Vous vous êtes déplacés sur : <br/>" +JCourant.getPosition().getNom(), couleur1, Boolean.TRUE, Boolean.TRUE);
                nbActionsRestantes-=1;
                vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueJCourant();
                finTour(o, idJoueur);
                
            }
            else if (((Message) arg).getCommande() == Commandes.CHOISIR_TUILE_A){
                int idTuile =((Message) arg).getIdTuile();
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                this.vuePlateau.setTuilesDefaut(listeIdTuiles);
                gererAssechement(idTuile, idJoueur);
                listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                vuePlateau.getMessageBox().displayMessage("Vous avez asséché : <br/>" +grille.getListeTuiles().get(idTuile).getNom(), couleur1, Boolean.TRUE, Boolean.TRUE);
                
                //GESTION DU POUVOIR DE L'INGENIEUR
                if("Ingenieur".equals(listeJoueurs.get(idJoueur).getClass().getSimpleName()) && !listeIdTuiles.isEmpty() && listeJoueurs.get(idJoueur).getNbAssech() <2){
                    System.out.println("00");
                    if(Utils.poserQuestion("Voulez vous assécher une seconde tuile")){
                        this.vuePlateau.setTuilesAssechement(listeIdTuiles, idJoueur, couleur1, couleur2);
                        nbActionsRestantes+=1;
                    }
                    
                }
                nbActionsRestantes-=1;
                vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueJCourant();
                System.out.println(nbActionsRestantes);
                finTour(o, idJoueur);
            }
            
            else if (((Message ) arg).getCommande() == Commandes.TERMINER) {
                
                nbActionsRestantes = 3;
                ((VuePlateau) o).getListeVuesJoueurs().get((idJoueur)).setVueJPrecedant();
                joueurSuivant();
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).setVueJCourant();
                vuePlateau.getMessageBox().displayMessage("C'est à  : <br/>" +JCourant.getClass().getSimpleName()+" de jouer.", JCourant.getPion().getCouleur(), Boolean.TRUE, Boolean.TRUE);
            }
            else if (((Message ) arg).getCommande() == Commandes.POUVOIR) {
                
                //((VueAventurier) o).getPosition().setText(JCourant.getPosition().getNom());
                //((VueAventurier) o).getBtnAutreAction().setEnabled(false);
                
            }
            
            else if (((Message ) arg).getCommande() == Commandes.ANNULER) {
                ArrayList<Integer> listeIdTuiles = new ArrayList<>();
                for(Integer key : this.grille.getListeTuiles().keySet()){
                    listeIdTuiles.add(key);
                }
                vuePlateau.setTuilesDefaut(listeIdTuiles);
                vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueJCourant();
                
            }
        }
    }
    
    

    
    
    public void gererDeplacement(Integer idTuileArrivee, Integer idJoueur){
        LinkedHashMap<Integer, VueTuile> listeVuesTuiles = this.vuePlateau.getVueGrille().getListeTuiles();
        Integer idTuileDepart =JCourant.getPosition().getId();
        Color couleur = JCourant.getPion().getCouleur();
        
        this.grille.getListeTuiles().get(idTuileArrivee).arriveeJoueur(JCourant);
        this.grille.getListeTuiles().get(idTuileDepart).departJoueur(JCourant);
        this.JCourant.setPosition(this.grille.getListeTuiles().get(idTuileArrivee));
        this.vuePlateau.getListeVuesJoueurs().get(idJoueur).getPosition().setText(JCourant.getPosition().getNom());

        listeVuesTuiles.get(idTuileDepart).getJoueursSurTuile().remove(couleur);
        listeVuesTuiles.get(idTuileDepart).setCasesJoueurs();
        
        listeVuesTuiles.get(idTuileArrivee).getJoueursSurTuile().add(couleur);
        listeVuesTuiles.get(idTuileArrivee).setCasesJoueurs();
    }
    
        
    public void gererAssechement(Integer idTuile, Integer idJoueur){
        vuePlateau.getVueGrille().getListeTuiles().get(idTuile).setEtat(EtatTuile.ASSECHEE.toString());
        vuePlateau.getVueGrille().getListeTuiles().get(idTuile).setCouleurDefaut();
        
        this.grille.getListeTuiles().get(idTuile).setEtatTuile(EtatTuile.ASSECHEE);
        this.JCourant.setNbAssech(this.JCourant.getNbAssech()+1);
    }
    
    
    public void finTour(Observable o, int idJCourant){
        //tirageCarte();
        //tirageInondation();
        if (nbActionsRestantes == 0){
            this.vuePlateau.getListeVuesJoueurs().get(idJCourant).setVueFinTour();
        }
        if (ifVictoire()){
            System.out.println("FAUT FAIRE LA VUE VICTOIRE VITE ET SUPPRIMER CE MESSAGE");
        }
    }
    
    //gestion tirage carte
    
    public void tirageCarte(){
        ArrayList<Integer> listeId = new ArrayList<>();
        for(Integer key : piocheTirage.keySet()){
            listeId.add(key);
        }
        CarteTirage c = piocheTirage.get(listeId.get(0));
        
        if (c.getClass().getSimpleName() == "CarteMonteeDesEaux"){
            piocheCarteMonteeDesEaux();
            piocheTirage.put(c.getId(), c);
        }else{
                JCourant.addCartesEnMain(c);
                
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).ajouterCarte(listeId.get(0), vuePlateau.getListeVuesCartes().get(listeId.get(0)));
        }   
        piocheTirage.remove(listeId.get(0));
    }
    
    //Gestion montée des eaux
    
    public void piocheCarteMonteeDesEaux(){
        niveauInond++;
        defausseInondation.addAll(piocheInondation);
        piocheInondation.clear();
        piocheInondation.addAll(defausseInondation);
        defausseInondation.clear();
    }
    
    //nb de carte tirer+tirage effectif
    
    public void tirageInondation(){
        if(niveauInond <3){         //2 carte piochées
            piocheCarteInondee();
            piocheCarteInondee();
        }else if(niveauInond <6){   //3 carte piochées
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
        }else if(niveauInond <8){   //4 carte piochées
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
        }else{                      //5 carte piochées
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
            piocheCarteInondee();
        }
    }

    //gestion tirage carte inondee
    
    public void piocheCarteInondee(){
        CarteInondation c = new CarteInondation();
        c.equals(piocheInondation.get(1));
        // inondation de la tuile correspondante
        defausseInondation.add(c);
        piocheInondation.remove(c);
    }
    
    // fonction pour les conditions de Victoire et de Defaite
    public boolean ifCarteHelico(Aventurier a){
        boolean retour = false;
        String cH = "CarteHelicoptere";
        for(Integer key : a.getCartesEnMain().keySet()){
            if(a.getCartesEnMain().get(key).getClass().getSimpleName().equals(cH)){
                retour = true;
            }
        }
        return retour;
    }
    
    public boolean ifToutLesTrésors(){
        return (tresorPossede.size() == 4);
    }
    
    public boolean ifToutLesJoueursSurHeliport(){
        Tuile t = JCourant.getPosition();
        for(Integer key : this.grille.getListeTuiles().keySet()){
            if (this.grille.getListeTuiles().get(key).getNom()=="Heliport"){
                t = this.grille.getListeTuiles().get(key);
            }
        }
        return t.getJoueursSurTuile().size() ==4;
    }
    
    public boolean ifVictoire(){
        boolean retour = false;
        if(ifToutLesJoueursSurHeliport()){
            if(ifToutLesTrésors()){
                for(Integer key : listeJoueurs.keySet()){
                    if(ifCarteHelico(listeJoueurs.get(key))){
                        retour = true;
                    }
                }
            }
        }
        return retour;
    }
    // GESTION DE VUES
    
    public VueTuile initVueTuile(Tuile t){
        
        VueTuile vT;
        ArrayList<Color> couleurs = new ArrayList<>();
        for(Aventurier a : t.getJoueursSurTuile()){
            couleurs.add(a.getPion().getCouleur());
        }
        String tresor;
        tresor = null;
        if("La Caverne des Ombres".equals(t.getNom()) || "La Caverne du Brasier".equals(t.getNom())){
            tresor = Tresor.CRISTAL.toString();
        }
        else if("Le Palais de Corail".equals(t.getNom()) || "Le Palais des Marées".equals(t.getNom())){
            tresor = Tresor.CALICE.toString();
        }
        else if("Le Temple de la Lune".equals(t.getNom()) || "Le Temple du Soleil".equals(t.getNom())){
            tresor = Tresor.PIERRE.toString();
        }
        else if("Le Jardin des Murmures".equals(t.getNom()) || "Le Jardin des Hurlements".equals(t.getNom())){
            tresor = Tresor.ZEPHYR.toString();
        }
        vT = new VueTuile(t.getId(), t.getNom(), tresor, t.getEtatTuile().toString(), couleurs);
        
        return vT;
    }

    public LinkedHashMap<Integer, CarteTirage> getPiocheTirage() {
        return piocheTirage;
    }

    public void setPiocheTirage(LinkedHashMap<Integer, CarteTirage> piocheTirage) {
        this.piocheTirage = piocheTirage;
    }
    
    public String listeIdTuilesToString(ArrayList<Integer> listeTuiles){
        String res ="";
        for(Integer key : listeTuiles){
            res+=this.grille.getListeTuiles().get(key).getNom();
            
            res += " ";
        }
        return res;
    }

    
    
    
    
    
    public boolean ifHeliportNoyee(){
        boolean retour = false;
        
        Tuile t = JCourant.getPosition();
        for(Integer key : this.grille.getListeTuiles().keySet()){
            if (this.grille.getListeTuiles().get(key).getNom()=="Heliport"){
                t = this.grille.getListeTuiles().get(key);
            }
        }
        
        return t.getEtatTuile() == EtatTuile.COULEE;
    }
    
    public boolean ifNiveauMax(){
        return niveauInond == 10; 
    }
    
    public boolean ifTresorPierrePerdu(){
        int nbTempleInondee=0;
        if(!tresorPossede.contains(Tresor.PIERRE)){
            for(Integer key : this.grille.getListeTuiles().keySet()){
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.PIERRE){
                        nbTempleInondee++;
                }
            }
        }
        return nbTempleInondee == 2;
    }
    
    public boolean ifTresorZephyrPerdu(){
        int nbTempleInondee=0;    
        if(!tresorPossede.contains(Tresor.ZEPHYR)){
            for(Integer key : this.grille.getListeTuiles().keySet()){
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.ZEPHYR){
                        nbTempleInondee++;
                }
            }
        }
        return nbTempleInondee == 2;
    }
    
    public boolean ifTresorCristalPerdu(){
        int nbTempleInondee=0;    
        if(!tresorPossede.contains(Tresor.CRISTAL)){
            for(Integer key : this.grille.getListeTuiles().keySet()){
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.CRISTAL){
                        nbTempleInondee++;
                }
            }
        }
        return nbTempleInondee == 2;
    }
    
    public boolean ifTresorCalicePerdu(){
        int nbTempleInondee=0;    
        if(!tresorPossede.contains(Tresor.CALICE)){
            for(Integer key : this.grille.getListeTuiles().keySet()){
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.CALICE){
                        nbTempleInondee++;
                }
            }
        }
        return nbTempleInondee == 2;
    }
    
    public boolean ifDefaite(){
        return(ifNiveauMax() || ifHeliportNoyee() || this.ifTresorPierrePerdu() ||this.ifTresorZephyrPerdu() || this.ifTresorCristalPerdu() || this.ifTresorCalicePerdu());
    }
}
