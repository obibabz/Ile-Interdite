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
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Controleur implements Observer{
    private ArrayList <Aventurier> listeJoueurs;
    private int nbActionsRestantes;
    private Aventurier JCourant;
    private Grille grille;
    private ArrayList <Tuile> tuilesAssech;
    private ArrayList <Tuile> tuilesAccess;
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
    
    
    /**
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
        tuilesAccess = JCourant.getTuilesAccessibles(grille);
        afficherTuiles(tuilesAccess);
        Tuile tuile = choixTuile(tuilesAccess);
        JCourant.getPosition().departJoueur(JCourant);
        JCourant.setPosition(tuile);
        System.out.println("Vous vous êtes déplacés sur la tuile : " +tuile.getNom());
        nbActionsRestantes+=-1;
    }
    
    public void gererAssechement(){
        tuilesAssech = JCourant.getTuilesAssechables(grille);
        afficherTuiles(tuilesAssech);
        Tuile tuile = choixTuile(tuilesAssech);
        tuile.setEtatTuile(l.ileinterdite.EtatTuile.NORMAL);
        System.out.println("Vous avez asséchés la tuile : " +tuile.getNom());
        nbActionsRestantes+=-1;
    }
    
    public void gererPouvoir(){
        
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
        if (arg instanceof MessageAction) {
            if (((MessageAction) arg) == MessageAction.BOUGER) {
//<<<<<<< HEAD
                if (tuilesAccess.isEmpty()) {
                    gererDeplacement();
                    finTour(o, nbActionsRestantes);
                } else {
                    System.out.println("Aucune tuile n'est disponible pour se déplacer");
                }
//=======
                gererDeplacement();
                ((VueAventurier) o).getPosition().setText(JCourant.getPosition().getNom());
                finTour(o, nbActionsRestantes);
//>>>>>>> origin/master
            }
        
            else if (((MessageAction) arg) == MessageAction.ASSECHER) {
                if (tuilesAssech.isEmpty()) {
                    gererAssechement();
                    finTour(o, nbActionsRestantes);
                } else {
                    System.out.println("Aucune tuile asséchable n'est disponible");
                }
            }
            else if (((MessageAction ) arg) == MessageAction.PASSER) {
                ((VueAventurier) o).close();
                joueurSuivant(listeJoueurs);
                VueAventurier vue1 = new VueAventurier(JCourant.getNomJoueur(), JCourant.getClass().getName() , JCourant.getPion().getCouleur(), JCourant.getPosition().getNom());
                vue1.addObserver(this);
                if(JCourant.getPion().toString()=="Bleu" ){vue1.getBtnAutreAction().setEnabled(true);}
                vue1.afficher();
            }
            else if (((MessageAction ) arg) == MessageAction.POUVOIR) {
                
            }
        }
    }
        
    
    
    

    public void finTour(Observable o, int nbActionsRestantes){
        if (nbActionsRestantes == 0){
                    ((VueAventurier) o).getBtnBouger().setEnabled(false);
                    ((VueAventurier) o).getBtnAutreAction().setEnabled(false);
                    ((VueAventurier) o).getBtnAssecher().setEnabled(false);
    }
}
}
