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
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import cartes.CarteTirage;
import cartes.CarteInondation;
import util.Utils.Tresor;
import util.Utils.Commandes;
import vues.VueNiveau;
import vues.VuePlateau;
        

public class Controleur implements Observer{
    private ArrayList <Aventurier> listeJoueurs;
    private int nbActionsRestantes;
    private Aventurier JCourant;
    private Grille grille;
    private ArrayList <CarteInondation> piocheInondation;
    private ArrayList <CarteInondation> defausseInondation;
    private ArrayList<Tresor> tresorPossede;
    private int niveauInnond;
    private ArrayList<CarteTirage> piocheTirage;
    private ArrayList<CarteTirage> defausseTirage;
    private ArrayList<VueAventurier> vuesAventuriers;
    private VueNiveau vueNiveau;
    private VuePlateau vuePlateau;
    
    

    /**
     *
     * @param listeTuiles
     */
    
    /*public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }*/

    public void afficherTuiles(ArrayList<Tuile> listeTuiles){
        for(Tuile t : listeTuiles) {
            System.out.println(listeTuiles.indexOf(t)+ " : "+t.getNom());
        }
        
    }
    
    public Tuile choixTuile(ArrayList<Tuile> listeTuiles){
         
         Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez rentrer le numéro de la tuile choisie");            
    Integer numTuile = sc.nextInt();
    return listeTuiles.get(numTuile);
    
    }
    
    
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
 
    
    
    
    public void gererDeplacement(){
        ArrayList <Tuile>  tuilesAccess = JCourant.getTuilesAccessibles(grille);
        afficherTuiles(tuilesAccess);
        if(tuilesAccess.isEmpty()){
            System.out.println("Il n'y a pas de tuile asséchable disponible");
            nbActionsRestantes+=1;
        }
        Tuile tuile = choixTuile(tuilesAccess);
        JCourant.getPosition().departJoueur(JCourant);
        JCourant.setPosition(tuile);
        System.out.println("Vous vous êtes déplacé sur la tuile : " +tuile.getNom());
    }
    
    public void gererAssechement(){
        ArrayList <Tuile> tuilesAssech = JCourant.getTuilesAssechables(grille);
        if(tuilesAssech.isEmpty()){
            System.out.println("Il n'y a pas de tuile asséchable disponible");
            nbActionsRestantes+=1;
        }else{
            afficherTuiles(tuilesAssech);
            Tuile tuile = choixTuile(tuilesAssech);
            tuile.setEtatTuile(l.ileinterdite.EtatTuile.NORMAL);
            System.out.println("Vous avez asséché la tuile : " +tuile.getNom());
        }
        
    }
    
    public void gererPouvoir(){
        ArrayList <Tuile> tuilesAccess = JCourant.getTuileAccessiblesPouvoir(grille);
        afficherTuiles(tuilesAccess);
        if(tuilesAccess.isEmpty()){
            System.out.println("Il n'y a pas de tuile asséchable disponible");
            nbActionsRestantes+=1;
        }
        Tuile tuile = choixTuile(tuilesAccess);
        JCourant.getPosition().departJoueur(JCourant);
        JCourant.setPosition(tuile);
        System.out.println("Vous vous êtes déplacé sur la tuile : " +tuile.getNom());        
    }

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

    public void setVuesAventuriers(ArrayList<VueAventurier> vuesAventuriers) {
        this.vuesAventuriers = vuesAventuriers;
    }

    public void setVueNiveau(VueNiveau vueNiveau) {
        this.vueNiveau = vueNiveau;
    }

    public void setVuePlateau(VuePlateau vuePlateau) {
        this.vuePlateau = vuePlateau;
    }
    

    public void setJCourant(Aventurier JCourant) {
        this.JCourant = JCourant;
    }

    public void setListeJoueurs(ArrayList<Aventurier> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }
    
    public void joueurSuivant(ArrayList <Aventurier> listeJoueurs){
        if(listeJoueurs.indexOf(JCourant) < listeJoueurs.size()-1 ){
            setJCourant(listeJoueurs.get(listeJoueurs.indexOf(JCourant)+1 ));
        }
        else{setJCourant(listeJoueurs.get(0));}
    }
     
    public void setGrille(Grille grille) {
        this.grille = grille;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Commandes) {
            if (((Commandes) arg) == Commandes.BOUGER) { 

                gererDeplacement();
                ((VueAventurier) o).getPosition().setText(JCourant.getPosition().getNom());
                nbActionsRestantes+=-1;
                System.out.println(nbActionsRestantes);
                finTour(o, nbActionsRestantes);

            }
        
            else if (((Commandes) arg) == Commandes.ASSECHER) {
               
                    gererAssechement();
                    if(JCourant.getPion().toString()=="Rouge"){
                        gererAssechement();
                        nbActionsRestantes+=-1;
                    }
                    nbActionsRestantes+=-1;
                    System.out.println(nbActionsRestantes);
                    finTour(o, nbActionsRestantes);
                
            }
            else if (((Commandes ) arg) == Commandes.TERMINER) {
                
                nbActionsRestantes = 3;
                joueurSuivant(listeJoueurs);
                ((VueAventurier) o).getMainPanel().revalidate();
              
                
                
                
            }
            else if (((Commandes ) arg) == Commandes.POUVOIR) {
                gererPouvoir();
                ((VueAventurier) o).getPosition().setText(JCourant.getPosition().getNom());
                ((VueAventurier) o).getBtnAutreAction().setEnabled(false);
                
            }
        }
    }

    public void finTour(Observable o, int nbActionsRestantes){
        if (nbActionsRestantes == 0){
                    ((VueAventurier) o).getBtnBouger().setEnabled(false);
                    ((VueAventurier) o).getBtnAutreAction().setEnabled(false);
                    ((VueAventurier) o).getBtnAssecher().setEnabled(false);
        }
        if (ifVictoire()){
            System.out.println("FAUT FAIRE LA VUE VICTOIRE VITE ET SUPPRIMER CE MESSAGE");
        }
    }
    
    //Gestion montée des eaux
    
    public void piocheCarteMonteeDesEaux(){
        
    }
    
    
    // fonction pour les conditions de Victoire et de Defaite
    public boolean ifCarteHelico(Aventurier a){
        boolean retour = false;
        String cH = "CarteHelicoptere";
        for(CarteTirage c : a.getCartesEnMain()){
            if(c.getClass().getSimpleName().equals(cH)){
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
                for(Aventurier a : listeJoueurs){
                    if(ifCarteHelico(a)){
                        retour = true;
                    }
                }
            }
        }
        return retour;
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
