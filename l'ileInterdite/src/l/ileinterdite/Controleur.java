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
    private ArrayList<CarteTirage> piocheTirage;
    private ArrayList<CarteTirage> defausseTirage;
    
    
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
    
    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher 
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
 
    
    //methode de déplacement et decrementation de nbActionRestante

 
    /**
     * @return the nbActionsRestantes
     */
    public int getNbActionsRestantes() {
        return nbActionsRestantes;
    }

    /**
     * @param nbActionsRestantes the nbActionsRestantes to set
     */
    public void setNbActionsRestantes(int nbActionsRestantes) {
        this.nbActionsRestantes = nbActionsRestantes;
    }
 
    /**
     *
     * @return
     */
    public Grille getGrille(){
        return (null);
    
    }
        
    public void getTuile(){
    
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
     
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Message) {
            
            int idJoueur =((Message) arg).getIdAventurier();
            
            
            if (((Message) arg).getCommande() == Commandes.BOUGER) { 
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAccessibles(grille);
                this.vuePlateau.setTuilesDeplacement(listeIdTuiles, idJoueur, listeJoueurs.get(idJoueur).getPion().getCouleurSelectionAssechee(), listeJoueurs.get(idJoueur).getPion().getCouleurSelectionInondee());
                
          
            }
            else if (((Message) arg).getCommande() == Commandes.ASSECHER) {
                    //Gestion ingenieur (on relance gerer assechement, puis on incrémente nb action restantes)
                    ArrayList<Integer>listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                    this.vuePlateau.setTuilesAssechement(listeIdTuiles, idJoueur, listeJoueurs.get(idJoueur).getPion().getCouleurSelectionAssechee(), listeJoueurs.get(idJoueur).getPion().getCouleurSelectionInondee());
                    
                    //finTour(o, nbActionsRestantes);
                
            }
            else if (((Message) arg).getCommande() == Commandes.CHOISIR_TUILE_D){
                int idTuile =((Message) arg).getIdTuile();
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAccessibles(grille);
                this.vuePlateau.setTuilesDefaut(listeIdTuiles);
                gererDeplacement(idTuile, idJoueur);
                //finTour(o, nbActionsRestantes);
                
            }
            else if (((Message) arg).getCommande() == Commandes.CHOISIR_TUILE_A){
                int idTuile =((Message) arg).getIdTuile();
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                this.vuePlateau.setTuilesDefaut(listeIdTuiles);
                gererAssechement(idTuile, idJoueur);
                listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                if("Ingenieur".equals(listeJoueurs.get(idJoueur).getClass().getSimpleName()) && !listeIdTuiles.isEmpty() && listeJoueurs.get(idJoueur).getNbAssech() <2){
                    System.out.println("00");
                    
                    this.vuePlateau.notifyObservers(new Message(Commandes.ASSECHER, idJoueur, null, null, null));
                }
            }
            
            else if (((Message ) arg).getCommande() == Commandes.TERMINER) {
                
                nbActionsRestantes = 3;
                ((VuePlateau) o).getListeVuesJoueurs().get((idJoueur)).setVueJPrecedant();
                joueurSuivant();
                
                
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).setVueJCourant();
                
                
                
                
            }
            else if (((Message ) arg).getCommande() == Commandes.POUVOIR) {
                
                //((VueAventurier) o).getPosition().setText(JCourant.getPosition().getNom());
                //((VueAventurier) o).getBtnAutreAction().setEnabled(false);
                
            }
        }
    }
    public void gererAssechement(Integer idTuile, Integer idJoueur){
        vuePlateau.getVueGrille().getListeTuiles().get(idTuile).setEtat(EtatTuile.ASSECHEE.toString());
        vuePlateau.getVueGrille().getListeTuiles().get(idTuile).setCouleurDefaut();
        
        this.grille.getListeTuiles().get(idTuile).setEtatTuile(EtatTuile.ASSECHEE);
        this.JCourant.setNbAssech(this.JCourant.getNbAssech()+1);
    }
    
    
    public void gererDeplacement(Integer idTuileArrivee, Integer idJoueur){
        LinkedHashMap<Integer, VueTuile> listeVuesTuiles = this.vuePlateau.getVueGrille().getListeTuiles();
        Integer idTuileDepart =JCourant.getPosition().getId();
        Color couleur = JCourant.getPion().getCouleur();
        
        this.grille.getListeTuiles().get(idTuileArrivee).arriveeJoueur(JCourant);
        this.grille.getListeTuiles().get(idTuileDepart).departJoueur(JCourant);
        this.JCourant.setPosition(this.grille.getListeTuiles().get(idTuileArrivee));

        listeVuesTuiles.get(idTuileDepart).getJoueursSurTuile().remove(couleur);
        listeVuesTuiles.get(idTuileDepart).setCasesJoueurs();
        
        listeVuesTuiles.get(idTuileArrivee).getJoueursSurTuile().add(couleur);
        listeVuesTuiles.get(idTuileArrivee).setCasesJoueurs();
        
        
        
    }
    public void finTour(Observable o, int nbActionsRestantes){
        tirageCarte();
        tirageInondation();
        if (nbActionsRestantes == 0){
        //            ((VueAventurier) o).getBtnBouger().setEnabled(false);
          //          ((VueAventurier) o).getBtnAutreAction().setEnabled(false);
            //        ((VueAventurier) o).getBtnAssecher().setEnabled(false);
        }
        if (ifVictoire()){
            System.out.println("FAUT FAIRE LA VUE VICTOIRE VITE ET SUPPRIMER CE MESSAGE");
        }
    }
    
    //gestion tirage carte
    
    public void tirageCarte(){
        CarteTirage c = new CarteTirage();
        c.equals(piocheTirage.get(1));
        piocheTirage.remove(1);
        defausseTirage.add(c);
        if (c.getClass().getSimpleName() == "CarteMonteeDesEaux"){
            piocheCarteMonteeDesEaux();
        }else{
            if(JCourant.getCartesEnMain().size() < 5){
                JCourant.addCartesEnMain(c);
            } else {
                System.out.println("FAUT SUPPRIMER, mais carte enlevee");
            }
        }   
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
        
        boolean retour = false;
        for (int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if("Heliport".equals(grille.getTuile(i, j).getNom())){
                    if(grille.getTuile(i, j).getJoueursSurTuile().size() == listeJoueurs.size()){
                        retour = true;
                    }
                }
            }
        }
        
        return retour;
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
    
    
    
    
    
    /*IF DEFAITE A FAIRE
    
    public boolean ifHeliportNoyee(){
        boolean retour = false;
        for (int i=0; i<6; i++){
            for(int j=0; j<6; j++){
                if("Heliport".equals(grille.getTuile(i, j).getNom())){
                    if(grille.getTuile(i, j).getEtatTuile() == EtatTuile.NOYEE){
                        retour=true;
                    }
                }
            }
        }
        return retour;
    }
    
    public boolean ifNiveauMax(){
        return niveauInnond == 10; 
    }
    
    public boolean ifDefaite(){
        if(ifNiveauMax()){
           System.out.println("VUE DEFAITE A FAIRE");
        }else if(ifHeliportNoyee()){
            
        }
    }*/
}
