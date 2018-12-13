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
        
import java.util.ArrayList;
import java.util.Scanner;

public class Controleur {
    
    private int nbActionsRestantes;
    
    /**
     *
     * @param nbJoueurs
     * @return
     */
    public ArrayList<Aventurier> getListeAventuriers(int nbJoueurs){
        String nomj;
        ArrayList<Aventurier> ListeAventuriers;
        ListeAventuriers = new ArrayList();
        Scanner sc = new Scanner(System.in);
        
        for(int i=1;i<=nbJoueurs;i++){
            System.out.println("Nom du joueur "+i+" : ");
            nomj=sc.nextLine();
            }
        return ListeAventuriers;
            
        }

    public void afficherTuiles(ArrayList<Tuile> listeTuiles){
        for(Tuile t : listeTuiles){
            System.out.println(t.getNom());
        }
    }
    
    public Tuile choixTuile(){
         Scanner sc = new Scanner(System.in);
    System.out.println("Veuillez rentrer le nom de la tuile choisie");            
    String nomActeur = sc.nextLine();
    Tuile t = 
    }
    
    
    
    public void gererDeplacement(){
    
    }
    
    public void gererAssechement(){
    
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
    
    public void getJoueurCourant(){
    
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
    
    
}
