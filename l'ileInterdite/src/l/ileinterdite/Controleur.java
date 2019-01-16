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
import aventuriers.Explorateur;
import aventuriers.Ingenieur;
import aventuriers.Pilote;
import aventuriers.Plongeur;
import cartes.CarteHelicoptere;
import vues.VueAventurier;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.util.Observable;
import java.util.Observer;
import cartes.CarteTirage;
import cartes.CarteInondation;
import cartes.CarteMonteeDesEaux;
import cartes.CarteSacsDeSable;
import cartes.CarteTresor;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import util.Message;
import util.MessageBox;
import util.Utils;
import util.Utils.Tresor;
import util.Utils.Commandes;
import vues.VueNiveau;
import vues.VuePlateau;
import vues.VueTuile;
import util.Utils.EtatTuile;
import util.Utils.Pion;
import vues.VueCarte;
import vues.VueGrille;

        

public class Controleur implements Observer{
    private LinkedHashMap <Integer, Aventurier> listeJoueurs;
    private int nbActionsRestantes;
    private Aventurier JCourant;
    private Grille grille;
    private ArrayList<CarteInondation> piocheInondation;
    private ArrayList<CarteInondation> defausseInondation = new ArrayList();
    private ArrayList<Tresor> tresorPossede = new ArrayList();
    private int niveauInond;
    private LinkedHashMap< Integer, CarteTirage> piocheTirage;
    private LinkedHashMap<Integer, CarteTirage> defausseTirage = new LinkedHashMap();
    private boolean partiePerdue = false;
    
    
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
    


    //Fonction de déroulement de la partie: 
    
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
                this.vuePlateau.setTuilesDefaut();
                gererDeplacement(idTuile, idJoueur);
                vuePlateau.getMessageBox().displayMessage("Vous vous êtes déplacés sur : <br/>" +JCourant.getPosition().getNom(), couleur1, Boolean.TRUE, Boolean.TRUE);
                nbActionsRestantes-=1;
                vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueJCourant();
                verifFinTour(o, idJoueur);
                
            }
            else if (((Message) arg).getCommande() == Commandes.CHOISIR_TUILE_A){
                int idTuile =((Message) arg).getIdTuile();
                ArrayList<Integer> listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                this.vuePlateau.setTuilesDefaut();
                gererAssechement(idTuile, idJoueur);
                listeIdTuiles = listeJoueurs.get(idJoueur).getTuilesAssechables(grille);
                vuePlateau.getMessageBox().displayMessage("Vous avez asséché : <br/>" +grille.getListeTuiles().get(idTuile).getNom(), couleur1, Boolean.TRUE, Boolean.TRUE);
                
                //GESTION DU POUVOIR DE L'INGENIEUR
                if("Ingenieur".equals(listeJoueurs.get(idJoueur).getClass().getSimpleName()) && !listeIdTuiles.isEmpty() && listeJoueurs.get(idJoueur).getNbAssech() <2){
                    if(Utils.poserQuestion("Voulez vous assécher une seconde tuile")){
                        this.vuePlateau.setTuilesAssechement(listeIdTuiles, idJoueur, couleur1, couleur2);
                        nbActionsRestantes+=1;
                    }
                    
                }
                nbActionsRestantes-=1;
                vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueJCourant();
                System.out.println(nbActionsRestantes);
                verifFinTour(o, idJoueur);
            }
            
            else if (((Message ) arg).getCommande() == Commandes.TERMINER) {
                actionFinTour();
                nbActionsRestantes = 3;
                ((VuePlateau) o).getListeVuesJoueurs().get((idJoueur)).setVueJPrecedant();
                joueurSuivant();
                
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).setVueJCourant();
                vuePlateau.getMessageBox().displayMessage("C'est à  : <br/>" +JCourant.getClass().getSimpleName()+" de jouer.", JCourant.getPion().getCouleur(), Boolean.TRUE, Boolean.TRUE);
                actionDebutTour();
            }
            else if (((Message ) arg).getCommande() == Commandes.POUVOIR) {
                ArrayList<Integer> idTuiles = new ArrayList<>();
                idTuiles = grille.getToutesTuilesPasCoulees();
                vuePlateau.setTuilesDeplacement(idTuiles, idJoueur, couleur2, couleur2);
                
            }
            //COMMANDE DONNER CARTE
            else if(((Message ) arg).getCommande() == Commandes.DONNER_CARTE){
                
                ArrayList<Integer> idJoueurs = JCourant.getJoueursCiblables(grille);
                if(!idJoueurs.isEmpty() && !JCourant.getCartesTresor().isEmpty()){
                    vuePlateau.setBoutonsRecevoirCarte(idJoueurs, idJoueur);
                    vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueChoix();
                }
            }
            else if(((Message ) arg).getCommande() == Commandes.RECEVOIR_CARTE){
                ArrayList<Integer> idJoueurs = JCourant.getJoueursCiblables(grille);
                vuePlateau.setBoutonsDonnerCarte(idJoueurs, idJoueur);
                
                ArrayList<Integer> idCartes = JCourant.getCartesTresor();
                for(Integer key :idCartes){
                    System.out.println(key);
                }
                vuePlateau.setCartesDonnables(idCartes, JCourant.getId(), idJoueur);
            }
            //CHOIX DE LA CARTE
            else if(((Message ) arg).getCommande() == Commandes.CHOISIR_CARTE){
                int idCarte = ((Message) arg).getIdCarte();
                CarteTirage ct= JCourant.getCartesEnMain().get(idCarte);//On récupère la carte en question
                vuePlateau.setCartesDefaut(JCourant.getCartesTresor(), JCourant.getId());//On remet l'affichade par défaut des cartes
                
                JCourant.retirerCarte(ct);//On la retire de la main du JCourant
                VueCarte vc = vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).getCartesEnMain().get(idCarte);
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).retirerCarte(idCarte);//On retire la VueCarte associée de la VueAventurier du JCourant

                listeJoueurs.get(idJoueur).addCartesEnMain(ct); //On ajoute la carte au joueur cible
                vuePlateau.getListeVuesJoueurs().get(idJoueur).ajouterCarte(idCarte, vc);//On ajoute la vue carte au joueur cible
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).setVueJCourant();
                nbActionsRestantes -=1;
            }
            else if (((Message) arg).getCommande() == Commandes.DEFAUSSER_CARTE){
                int idCarte = ((Message) arg).getIdCarte();
                CarteTirage c = listeJoueurs.get(idJoueur).getCartesEnMain().get(idCarte);
                listeJoueurs.get(idJoueur).retirerCarte(c);//On retire la carte de la main du joueur
                
                ArrayList<Integer> listeIdCartes = new ArrayList<>();
                listeIdCartes.addAll(listeJoueurs.get(idJoueur).getCartesEnMain().keySet());
                
                vuePlateau.getListeVuesCartes().put(idCarte, vuePlateau.getListeVuesJoueurs().get(idJoueur).getCartesEnMain().get(idCarte));//On stock la VueCarte sur le plateau pour ne pas la perdre
                vuePlateau.getListeVuesJoueurs().get(idJoueur).retirerCarte(idCarte);//On retire la VueCarte de la VueAventurier qui se défausse
                vuePlateau.setCartesDefaut(listeIdCartes, idJoueur);//On affiche le changement
                
                defausseTirage.put(idCarte, c);//On rajoute la carte à la défausse
                gestionDefausse(idJoueur);//On relance la gestion de défausse tant que le joueur a plus de 5 cartes en main
                if(Utils.poserQuestion("Voulez vous utiliser la carte ?")){
                    if("Sac de Sable".equals(c.getNom())){
                        utilisationSac(idCarte, idJoueur);
                    }
                    else if(("Helicoptere").equals(c.getNom())){
                        utilisationHelicoptere(idCarte, idJoueur);
                    }
                }
            }
            
            else if (((Message ) arg).getCommande() == Commandes.ANNULER) {
                ArrayList<Integer> listeIdTuiles = new ArrayList<>();
                for(Integer key : this.grille.getListeTuiles().keySet()){
                    listeIdTuiles.add(key);
                }
                vuePlateau.setTuilesDefaut();
                vuePlateau.getListeVuesJoueurs().get(idJoueur).setVueJCourant();   
            }
            else if(((Message) arg).getCommande() == Commandes.UTILISER_CARTE){
                ArrayList<Integer> listeIdCartes = new ArrayList<>();
                listeIdCartes = JCourant.getCartesUtilisables();
                vuePlateau.setCartesDefaussables(listeIdCartes, idJoueur);
            }
            else if(((Message) arg).getCommande() == Commandes.RECUPERER_TRESOR){
                Utils.Tresor t = listeJoueurs.get(idJoueur).getPosition().getTresor();
                if(t != null){
                    if(listeJoueurs.get(idJoueur).tresorRecuperable(t))
                    this.tresorPossede.add(t);
                    this.vuePlateau.getMessageBox().displayTresor(t);
                    
                }
            }
        }
    }
    
    public void verifFinTour(Observable o, int idJCourant){
        if (nbActionsRestantes == 0){
            this.vuePlateau.getListeVuesJoueurs().get(idJCourant).setVueFinTour();
        }
    }    
    public void actionDebutTour(){
        System.out.println("0");
        gestionDefausse(JCourant.getId());
    }       
    public void actionFinTour(){
        
             //Tirage des cartes Tresor et Inondation
        tirageCarte();
        tirageCarte();
        tirageInondation();

        if (ifVictoire()){
            System.out.println("INSEREZ VUE VICTOIRE");
        }

        if(ifDefaite()){
            System.out.println("INSEREZ VUE DEFAITE");
        }
        
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
    


    //Fonction de dépalcement et d'assechement
    
    public void gererDeplacement(Integer idTuileArrivee, Integer idJoueur){
        Aventurier joueur = listeJoueurs.get(idJoueur);
        LinkedHashMap<Integer, VueTuile> listeVuesTuiles = this.vuePlateau.getVueGrille().getListeTuiles();
        Integer idTuileDepart =joueur.getPosition().getId();
        Color couleur = joueur.getPion().getCouleur();
        
        this.grille.getListeTuiles().get(idTuileArrivee).arriveeJoueur( joueur);
        this.grille.getListeTuiles().get(idTuileDepart).departJoueur(joueur.getId());
        joueur.setPosition(this.grille.getListeTuiles().get(idTuileArrivee));
        this.vuePlateau.getListeVuesJoueurs().get(idJoueur).getPosition().setText(joueur.getPosition().getNom());

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
    


    //gestion tirage carte
    
    public void tirageCarte(){
        ArrayList<Integer> listeId = new ArrayList<>();
        listeId.addAll( piocheTirage.keySet());
        if(piocheTirage.isEmpty()){
            for(Integer key : defausseTirage.keySet()){
                piocheTirage.put(key, defausseTirage.get(key));
                listeId.add(key);
            }
        }
            CarteTirage c = piocheTirage.get(listeId.get(0));
       
        if (c.getClass().getSimpleName() == "CarteMonteeDesEaux"){
            piocheCarteMonteeDesEaux();
            piocheTirage.put(c.getId(), c);
        }else{
                JCourant.addCartesEnMain(c);
                vuePlateau.getListeVuesJoueurs().get(JCourant.getId()).ajouterCarte(listeId.get(0), vuePlateau.getListeVuesCartes().get(listeId.get(0)));
        }   
        
        // Pas propre pour commencer le fait que les cartes s'affichent mal
        vuePlateau.afficher();
        piocheTirage.remove(listeId.get(0));
        
    }
    public void gestionDefausse(Integer idJoueur){
        System.out.println("1");
        if(!listeJoueurs.get(idJoueur).cartesEnMaininf5()){
            System.out.println("2");
            ArrayList<Integer> listeIdCartes = new ArrayList<>();
            listeIdCartes.addAll(listeJoueurs.get(idJoueur).getCartesEnMain().keySet());
            vuePlateau.setCartesDefaussables(listeIdCartes, idJoueur);
        }
    }
    public void utilisationSac(Integer idCarte, Integer idJoueur){
            ArrayList<Integer> idTuiles = new ArrayList<>();
            idTuiles = this.grille.getToutesTuilesInondees();
            vuePlateau.setTuilesAssechement(idTuiles, idJoueur, listeJoueurs.get(idJoueur).getPion().getCouleurSelectionAssechee(), listeJoueurs.get(idJoueur).getPion().getCouleurSelectionInondee());
            nbActionsRestantes+=1;
        
    }
    public void utilisationHelicoptere(Integer idCarte, Integer idJoueur){
        ArrayList<Integer> idTuiles = new ArrayList<>();
            idTuiles = this.grille.getToutesTuilesPasCoulees();
            vuePlateau.setTuilesDeplacement(idTuiles, idJoueur, listeJoueurs.get(idJoueur).getPion().getCouleurSelectionAssechee(), listeJoueurs.get(idJoueur).getPion().getCouleurSelectionInondee());
            nbActionsRestantes +=1;
    }
    //Gestion montée des eaux
    
    public void piocheCarteMonteeDesEaux(){
        niveauInond++;
        vuePlateau.getVueNiveau().setNiveau(niveauInond);
        defausseInondation.addAll(piocheInondation);
        piocheInondation.clear();
        piocheInondation.addAll(defausseInondation);
        defausseInondation.clear();
    }
    
    
    
    //pioche d'une carte inondation

    public void piocheCarteInondee(){
        CarteInondation c = piocheInondation.get(0);
        Tuile t = grille.getListeTuiles().get(c.getId());
        VueTuile vt = vuePlateau.getVueGrille().getListeTuiles().get(c.getId());
        // inondation de la tuile correspondante
        if (piocheInondation.isEmpty()){
            for(CarteInondation ci: this.defausseInondation){
                piocheInondation.add(ci);
            }
        }
        if(t.getEtatTuile() == EtatTuile.ASSECHEE){        //
            t.setEtatTuile(EtatTuile.INONDEE);
            defausseInondation.add(c);
            piocheInondation.remove(0);
            vuePlateau.getMessageBox().displayMessage("La tuile : "+grille.getListeTuiles().get(c.getId()).getNom()+" a été inondée", Color.BLACK, Boolean.TRUE, Boolean.TRUE);
            vt.setEtat(EtatTuile.INONDEE.toString());
            vt.setCouleurDefaut();
        }else if(t.getEtatTuile() == EtatTuile.INONDEE){
            t.setEtatTuile(EtatTuile.COULEE);
            vt.setEtat(EtatTuile.COULEE.toString());
            vt.setCouleurDefaut();
            vuePlateau.getMessageBox().displayMessage("La tuile "+grille.getListeTuiles().get(c.getId()).getNom()+" a été coulée.", Color.BLACK, Boolean.TRUE, Boolean.TRUE);
            piocheInondation.remove(0);
            if(!t.getJoueursSurTuile().isEmpty()){
                for(Integer key : t.getJoueursSurTuile().keySet()){
                    if(!t.getJoueursSurTuile().get(key).getTuilesAccessibles(grille).isEmpty()){
                        vuePlateau.setTuilesDeplacement(t.getJoueursSurTuile().get(key).getTuilesAccessibles(grille), key, Color.blue, Color.blue);
                    }else{
                        partiePerdue = true;
                        
                    }
                }
            }
        }
        else if(t.getEtatTuile() == EtatTuile.COULEE){
            piocheInondation.remove(0);
            piocheCarteInondee();
            
            
        }
    }

    //nb de carte inondation tiree + tirage effectif
    
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
        for(Integer key  : t.getJoueursSurTuile().keySet()){
            couleurs.add(t.getJoueursSurTuile().get(key).getPion().getCouleur());
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
    
    public void setPiocheInondation(ArrayList<CarteInondation> piocheInondation) {
        this.piocheInondation = piocheInondation;
    }
    
    public String listeIdTuilesToString(ArrayList<Integer> listeTuiles){
        String res ="";
        for(Integer key : listeTuiles){
            res+=this.grille.getListeTuiles().get(key).getNom();
            
            res += " ";
        }
        return res;
    }

    
    
    
            //Test des différentes conditions de défaites (sauf le cas de la "mort" d'un joueur, directement géré dans tirageCarteInodation)
    
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
                if (grille.getListeTuiles().get(key).getTresor()==Tresor.PIERRE && grille.getListeTuiles().get(key).getEtatTuile() == EtatTuile.COULEE){
                        nbTempleInondee++;
                        System.out.println("Nombre de temple inondee : "+nbTempleInondee+ "  carte comptee : "+grille.getListeTuiles().get(key).getNom());
                }
            }
        }
        return nbTempleInondee == 2;
    }
    
    public boolean ifTresorZephyrPerdu(){
        int nbTempleInondee=0;    
        if(!tresorPossede.contains(Tresor.ZEPHYR)){
            for(Integer key : this.grille.getListeTuiles().keySet()){
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.ZEPHYR && grille.getListeTuiles().get(key).getEtatTuile() == EtatTuile.COULEE){
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
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.CRISTAL && grille.getListeTuiles().get(key).getEtatTuile() == EtatTuile.COULEE){
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
                if (this.grille.getListeTuiles().get(key).getTresor()==Tresor.CALICE && grille.getListeTuiles().get(key).getEtatTuile() == EtatTuile.COULEE){
                        nbTempleInondee++;
                }
            }
        }
        return nbTempleInondee == 2;
    }
    
    public boolean ifDefaite(){
        return(ifNiveauMax() || ifHeliportNoyee() || ifTresorPierrePerdu() || ifTresorZephyrPerdu() || ifTresorCristalPerdu() || ifTresorCalicePerdu() || partiePerdue);
    }
    
//  ================================== SCENARIO 1: test partie normale ============================================================
    public static void scenario1() {
    
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
        
        //INITIALISATION DES CARTES INONDATIONS
        
        ArrayList<CarteInondation> piocheInond = new ArrayList();
        
        ArrayList<CarteInondation> listeCartesInond = new ArrayList();
        for(Integer key : listeTuiles.keySet()){
            CarteInondation cI = new CarteInondation(listeTuiles.get(key));
            
            piocheInond.add(cI);
            
        }
        
        
        
        Grille grid = new Grille(listeTuiles);
        
        
        Utils.Pion rouge = Utils.Pion.ROUGE;
        Utils.Pion bleu = Utils.Pion.BLEU;
        Utils.Pion jaune = Utils.Pion.JAUNE;
        Utils.Pion orange = Utils.Pion.ORANGE;
        Utils.Pion vert = Utils.Pion.VERT;
        Utils.Pion violet = Utils.Pion.VIOLET;
        
        //INITIALISATION DES CARTES/MAINS JOUEURS
        LinkedHashMap<Integer, CarteTirage> listeCartes = new LinkedHashMap<>();
        int i =0;
        while(i<=31){
            if(i<5){
                CarteTresor ct = new CarteTresor(Tresor.CALICE);
                listeCartes.put(ct.getId(), ct);
            }
            if(5<=i && i<10){
                CarteHelicoptere ct = new CarteHelicoptere();
                listeCartes.put(ct.getId(), ct);
                
            }
            if(10<=i && i<15){
                CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
                listeCartes.put(ct.getId(), ct);
            }
            if(15<=i && i<20){
                CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
                listeCartes.put(ct.getId(), ct);
            }
            if(20<=i && i<25){
                CarteSacsDeSable ct = new CarteSacsDeSable();
                listeCartes.put(ct.getId(), ct);
            }
            if(25<=i && i<30){
                CarteTresor ct = new CarteTresor(Tresor.PIERRE);
                listeCartes.put(ct.getId(), ct);
            }
            if(30<=i && i<31){
            CarteMonteeDesEaux ct = new CarteMonteeDesEaux();
            listeCartes.put(ct.getId(), ct);
            }
            i++;
        }
        //((Collections) listeCartes).shuffle();
        LinkedHashMap<Integer, VueCarte> piocheTirage = new LinkedHashMap();
        
        for(Integer key : listeCartes.keySet()){
            VueCarte vc = new VueCarte(listeCartes.get(key).getNom());
            piocheTirage.put(key, vc);
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
        controleur.setPiocheTirage(listeCartes);
        controleur.setPiocheInondation(piocheInond);
        
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
        vP.setListeVuesCartes(piocheTirage);
        vP.addObserver(controleur);
        vP.afficher();
        controleur.tirageCarte();
        controleur.tirageCarte();
        controleur.tirageCarte();
        controleur.tirageCarte();

        
    }
    
   
    
    //  ================================== SCENARIO 2: test condition de victoire ============================================================
    public static void scenario2() {
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
        Tuile PBr = new Tuile("La Porte de Bronze", EtatTuile.ASSECHEE); 
        Tuile COm = new Tuile("La Caverne des Ombres", EtatTuile.ASSECHEE); COm.setTresor(Tresor.CRISTAL);
        Tuile PFe = new Tuile("La Porte de Fer", EtatTuile.ASSECHEE);
        Tuile POr = new Tuile("La Porte d'Or", EtatTuile.INONDEE);
        Tuile FOu = new Tuile("Les Falaises de l'Oubli", EtatTuile.ASSECHEE);
        Tuile PCo = new Tuile("Le Palais de Corail", EtatTuile.ASSECHEE); PCo.setTresor(Tresor.CALICE);
        Tuile PAr = new Tuile("La Porte d'Argent", EtatTuile.ASSECHEE);
        Tuile DIl = new Tuile("Les Dunes de l'Illusion", EtatTuile.ASSECHEE);
        Tuile H = new Tuile("Heliport", EtatTuile.ASSECHEE);
        Tuile PCu = new Tuile("La Porte de Cuivre", EtatTuile.ASSECHEE);
        Tuile JHu = new Tuile("Le Jardin des Hurlements", EtatTuile.COULEE); JHu.setTresor(Tresor.ZEPHYR);
        Tuile FPo = new Tuile("La Forêt Pourpre", EtatTuile.COULEE);
        Tuile LPe = new Tuile("Le Lagon Perdu", EtatTuile.ASSECHEE);
        Tuile MBr = new Tuile("Le Marais Brumeux", EtatTuile.ASSECHEE);
        Tuile O = new Tuile("Observatoire", EtatTuile.ASSECHEE);
        Tuile RFa = new Tuile("Le Rocher Fantôme", EtatTuile.ASSECHEE);
        Tuile CBr = new Tuile("La Caverne du Brasier", EtatTuile.INONDEE); CBr.setTresor(Tresor.CRISTAL);
        Tuile TSo = new Tuile("Le Temple du Soleil", EtatTuile.ASSECHEE); TSo.setTresor(Tresor.PIERRE);
        Tuile TLu = new Tuile("Le Temple de la Lune", EtatTuile.INONDEE); TLu.setTresor(Tresor.PIERRE);
        Tuile PMa = new Tuile("Le Palais des Marées", EtatTuile.ASSECHEE); PMa.setTresor(Tresor.CALICE);
        Tuile VCr = new Tuile("Le Val du Crépuscule", EtatTuile.COULEE);
        Tuile TGu = new Tuile("La Tour du Guet", EtatTuile.ASSECHEE);
        Tuile JMu = new Tuile("Le Jardin des Murmures", EtatTuile.ASSECHEE); JMu.setTresor(Tresor.ZEPHYR);
        
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
            if(5<=i && i<10){
                CarteTresor ct = new CarteTresor(Tresor.PIERRE);
                listeCartes.put(ct.getId(), ct);
            }
            if(10<=i && i<15){
                CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
                listeCartes.put(ct.getId(), ct);
            }
            if(15<=i && i<20){
                CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
                listeCartes.put(ct.getId(), ct);
            }
            if(20<=i && i<25){
                CarteSacsDeSable ct = new CarteSacsDeSable();
                listeCartes.put(ct.getId(), ct);
            }
            if(25<=i && i<30){
                CarteHelicoptere ct = new CarteHelicoptere();
                listeCartes.put(ct.getId(), ct);
            }
            if(30<=i && i<31){
            CarteMonteeDesEaux ct = new CarteMonteeDesEaux();
            listeCartes.put(ct.getId(), ct);
            }
            i++;
        }
        
        LinkedHashMap<Integer, VueCarte> piocheTirage = new LinkedHashMap();
        
        for(Integer key : listeCartes.keySet()){
            VueCarte vc = new VueCarte(listeCartes.get(key).getNom());
            piocheTirage.put(key, vc);
        }
        
        LinkedHashMap<Integer, Aventurier> joueursSurGrille = new LinkedHashMap();
        Aventurier joueur1 = new Ingenieur("joueur1", PFe, rouge); joueursSurGrille.put(joueur1.getId(), joueur1);PMa.arriveeJoueur(joueur1); joueur1.setPosition(PMa);
        Aventurier joueur2 = new Explorateur("joueur2", PCo, vert); joueursSurGrille.put(joueur2.getId(), joueur2);PCu.arriveeJoueur(joueur2);
        Aventurier joueur3 = new Plongeur("joueur3", DIl, violet); joueursSurGrille.put(joueur3.getId(), joueur3);PFe.arriveeJoueur(joueur3);
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
        controleur.setPiocheTirage(listeCartes);
        
        
        
        
     
        // INNITIALISATION PLATEAU
        
        LinkedHashMap<Integer, VueTuile> vuesTuiles= new LinkedHashMap<>();
        for(Integer key : listeTuiles.keySet()){
            VueTuile vT = controleur.initVueTuile(listeTuiles.get(key));
            vuesTuiles.put(vT.getIdVueTuile(), vT);
        }
      
        MessageBox mb1 = new MessageBox();mb1.setCaliceVisible();
        MessageBox mb2 = new MessageBox();mb2.setCristalVisible();
        MessageBox mb3 = new MessageBox();mb3.setPierreVisible();
        MessageBox mb4 = new MessageBox();mb4.setZephyrVisible();
        VueGrille vG = new VueGrille(vuesTuiles);
        
        
        VuePlateau vP1 = new VuePlateau(vG, vuesAventurier, mb1, new VueNiveau(2));
        controleur.setVuePlateau(vP1);
        vP1.setListeVuesCartes(piocheTirage);
        vP1.addObserver(controleur);
        vP1.afficher();
        controleur.tirageCarte();
        
        VuePlateau vP2 = new VuePlateau(vG, vuesAventurier, mb2, new VueNiveau(2));
        controleur.setVuePlateau(vP2);
        vP2.setListeVuesCartes(piocheTirage);
        vP2.addObserver(controleur);
        vP2.afficher();
        controleur.tirageCarte();
        
        VuePlateau vP3 = new VuePlateau(vG, vuesAventurier, mb3, new VueNiveau(2));
        controleur.setVuePlateau(vP3);
        vP3.setListeVuesCartes(piocheTirage);
        vP3.addObserver(controleur);
        vP3.afficher();
        controleur.tirageCarte();
        
        VuePlateau vP4 = new VuePlateau(vG, vuesAventurier, mb4, new VueNiveau(2));
        controleur.setVuePlateau(vP4);
        vP4.setListeVuesCartes(piocheTirage);
        vP4.addObserver(controleur);
        vP4.afficher();
        controleur.tirageCarte();
    }
    
    
    
    
    //  ================================== SCENARIO 3: DEFAITE: un joueur est coulé ============================================================
    public static void scenario3() {
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
        Tuile PAb = new Tuile("Le Pont des Abîmes", EtatTuile.COULEE); 
        Tuile PBr = new Tuile("La Porte de Bronze", EtatTuile.COULEE); 
        Tuile COm = new Tuile("La Caverne des Ombres", EtatTuile.INONDEE); COm.setTresor(Tresor.CRISTAL);
        Tuile PFe = new Tuile("La Porte de Fer", EtatTuile.COULEE);
        Tuile POr = new Tuile("La Porte d'Or", EtatTuile.COULEE);
        Tuile FOu = new Tuile("Les Falaises de l'Oubli", EtatTuile.COULEE);
        Tuile PCo = new Tuile("Le Palais de Corail", EtatTuile.INONDEE); PCo.setTresor(Tresor.CALICE);
        Tuile PAr = new Tuile("La Porte d'Argent", EtatTuile.INONDEE);
        Tuile DIl = new Tuile("Les Dunes de l'Illusion", EtatTuile.INONDEE);
        Tuile H = new Tuile("Heliport", EtatTuile.INONDEE);
        Tuile PCu = new Tuile("La Porte de Cuivre", EtatTuile.COULEE);
        Tuile JHu = new Tuile("Le Jardin des Hurlements", EtatTuile.COULEE); JHu.setTresor(Tresor.ZEPHYR);
        Tuile FPo = new Tuile("La Forêt Pourpre", EtatTuile.COULEE);
        Tuile LPe = new Tuile("Le Lagon Perdu", EtatTuile.INONDEE);
        Tuile MBr = new Tuile("Le Marais Brumeux", EtatTuile.INONDEE);
        Tuile O = new Tuile("Observatoire", EtatTuile.INONDEE);
        Tuile RFa = new Tuile("Le Rocher Fantôme", EtatTuile.COULEE);
        Tuile CBr = new Tuile("La Caverne du Brasier", EtatTuile.COULEE); CBr.setTresor(Tresor.CRISTAL);
        Tuile TSo = new Tuile("Le Temple du Soleil", EtatTuile.COULEE); TSo.setTresor(Tresor.PIERRE);
        Tuile TLu = new Tuile("Le Temple de la Lune", EtatTuile.INONDEE); TLu.setTresor(Tresor.PIERRE);
        Tuile PMa = new Tuile("Le Palais des Marées", EtatTuile.INONDEE); PMa.setTresor(Tresor.CALICE);
        Tuile VCr = new Tuile("Le Val du Crépuscule", EtatTuile.COULEE);
        Tuile TGu = new Tuile("La Tour du Guet", EtatTuile.INONDEE);
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
            if(5<=i && i<10){
                CarteTresor ct = new CarteTresor(Tresor.PIERRE);
                listeCartes.put(ct.getId(), ct);
            }
            if(10<=i && i<15){
                CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
                listeCartes.put(ct.getId(), ct);
            }
            if(15<=i && i<20){
                CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
                listeCartes.put(ct.getId(), ct);
            }
            if(20<=i && i<25){
                CarteSacsDeSable ct = new CarteSacsDeSable();
                listeCartes.put(ct.getId(), ct);
            }
            if(25<=i && i<30){
                CarteHelicoptere ct = new CarteHelicoptere();
                listeCartes.put(ct.getId(), ct);
            }
            if(30<=i && i<31){
            CarteMonteeDesEaux ct = new CarteMonteeDesEaux();
            listeCartes.put(ct.getId(), ct);
            }
            i++;
        }
        
        LinkedHashMap<Integer, VueCarte> piocheTirage = new LinkedHashMap();
        
        for(Integer key : listeCartes.keySet()){
            VueCarte vc = new VueCarte(listeCartes.get(key).getNom());
            piocheTirage.put(key, vc);
        }
        
        LinkedHashMap<Integer, Aventurier> joueursSurGrille = new LinkedHashMap();
        Aventurier joueur1 = new Ingenieur("joueur1", PFe, rouge); joueursSurGrille.put(joueur1.getId(), joueur1);PMa.arriveeJoueur(joueur1); joueur1.setPosition(PMa);
        Aventurier joueur2 = new Explorateur("joueur2", PCo, vert); joueursSurGrille.put(joueur2.getId(), joueur2);PCu.arriveeJoueur(joueur2);
        Aventurier joueur3 = new Plongeur("joueur3", DIl, violet); joueursSurGrille.put(joueur3.getId(), joueur3);PFe.arriveeJoueur(joueur3);
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
      
        MessageBox mb = new MessageBox();mb.setCaliceVisible();
        VueGrille vG = new VueGrille(vuesTuiles);
        
        
        VuePlateau vP = new VuePlateau(vG, vuesAventurier, mb, new VueNiveau(2));
        controleur.setVuePlateau(vP);
        vP.setListeVuesCartes(piocheTirage);
        vP.addObserver(controleur);
        vP.afficher();
        controleur.tirageCarte();
    }
    
    
    
    //  ================================== SCENARIO 4: DEFAITE: tous les trésors sont coulés ============================================================
    public static void scenario4() {
       
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
        Tuile PAb = new Tuile("Le Pont des Abîmes", EtatTuile.COULEE); 
        Tuile PBr = new Tuile("La Porte de Bronze", EtatTuile.COULEE); 
        Tuile COm = new Tuile("La Caverne des Ombres", EtatTuile.COULEE); COm.setTresor(Tresor.CRISTAL);
        Tuile PFe = new Tuile("La Porte de Fer", EtatTuile.INONDEE);
        Tuile POr = new Tuile("La Porte d'Or", EtatTuile.COULEE);
        Tuile FOu = new Tuile("Les Falaises de l'Oubli", EtatTuile.COULEE);
        Tuile PCo = new Tuile("Le Palais de Corail", EtatTuile.COULEE); PCo.setTresor(Tresor.CALICE);
        Tuile PAr = new Tuile("La Porte d'Argent", EtatTuile.INONDEE);
        Tuile DIl = new Tuile("Les Dunes de l'Illusion", EtatTuile.INONDEE);
        Tuile H = new Tuile("Heliport", EtatTuile.INONDEE);
        Tuile PCu = new Tuile("La Porte de Cuivre", EtatTuile.COULEE);
        Tuile JHu = new Tuile("Le Jardin des Hurlements", EtatTuile.COULEE); JHu.setTresor(Tresor.ZEPHYR);
        Tuile FPo = new Tuile("La Forêt Pourpre", EtatTuile.COULEE);
        Tuile LPe = new Tuile("Le Lagon Perdu", EtatTuile.INONDEE);
        Tuile MBr = new Tuile("Le Marais Brumeux", EtatTuile.INONDEE);
        Tuile O = new Tuile("Observatoire", EtatTuile.INONDEE);
        Tuile RFa = new Tuile("Le Rocher Fantôme", EtatTuile.COULEE);
        Tuile CBr = new Tuile("La Caverne du Brasier", EtatTuile.COULEE); CBr.setTresor(Tresor.CRISTAL);
        Tuile TSo = new Tuile("Le Temple du Soleil", EtatTuile.COULEE); TSo.setTresor(Tresor.PIERRE);
        Tuile TLu = new Tuile("Le Temple de la Lune", EtatTuile.COULEE); TLu.setTresor(Tresor.PIERRE);
        Tuile PMa = new Tuile("Le Palais des Marées", EtatTuile.COULEE); PMa.setTresor(Tresor.CALICE);
        Tuile VCr = new Tuile("Le Val du Crépuscule", EtatTuile.COULEE);
        Tuile TGu = new Tuile("La Tour du Guet", EtatTuile.INONDEE);
        Tuile JMu = new Tuile("Le Jardin des Murmures", EtatTuile.COULEE); JMu.setTresor(Tresor.ZEPHYR);
        
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
            if(5<=i && i<10){
                CarteTresor ct = new CarteTresor(Tresor.PIERRE);
                listeCartes.put(ct.getId(), ct);
            }
            if(10<=i && i<15){
                CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
                listeCartes.put(ct.getId(), ct);
            }
            if(15<=i && i<20){
                CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
                listeCartes.put(ct.getId(), ct);
            }
            if(20<=i && i<25){
                CarteSacsDeSable ct = new CarteSacsDeSable();
                listeCartes.put(ct.getId(), ct);
            }
            if(25<=i && i<30){
                CarteHelicoptere ct = new CarteHelicoptere();
                listeCartes.put(ct.getId(), ct);
            }
            if(30<=i && i<31){
            CarteMonteeDesEaux ct = new CarteMonteeDesEaux();
            listeCartes.put(ct.getId(), ct);
            }
            i++;
        }
        
        LinkedHashMap<Integer, VueCarte> piocheTirage = new LinkedHashMap();
        
        for(Integer key : listeCartes.keySet()){
            VueCarte vc = new VueCarte(listeCartes.get(key).getNom());
            piocheTirage.put(key, vc);
        }
        
        LinkedHashMap<Integer, Aventurier> joueursSurGrille = new LinkedHashMap();
        Aventurier joueur1 = new Ingenieur("joueur1", PFe, rouge); joueursSurGrille.put(joueur1.getId(), joueur1);PMa.arriveeJoueur(joueur1); joueur1.setPosition(PMa);
        Aventurier joueur2 = new Explorateur("joueur2", PCo, vert); joueursSurGrille.put(joueur2.getId(), joueur2);PCu.arriveeJoueur(joueur2);
        Aventurier joueur3 = new Plongeur("joueur3", DIl, violet); joueursSurGrille.put(joueur3.getId(), joueur3);PFe.arriveeJoueur(joueur3);
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
        controleur.setPiocheTirage(listeCartes);
        
        
        
        
     
        // INNITIALISATION PLATEAU
        
        LinkedHashMap<Integer, VueTuile> vuesTuiles= new LinkedHashMap<>();
        for(Integer key : listeTuiles.keySet()){
            VueTuile vT = controleur.initVueTuile(listeTuiles.get(key));
            vuesTuiles.put(vT.getIdVueTuile(), vT);
        }
      
        MessageBox mb = new MessageBox();mb.setCaliceVisible();
        VueGrille vG = new VueGrille(vuesTuiles);
        
        
        VuePlateau vP = new VuePlateau(vG, vuesAventurier, mb, new VueNiveau(2));
        controleur.setVuePlateau(vP);
        vP.setListeVuesCartes(piocheTirage);
        vP.addObserver(controleur);
        vP.afficher();
        controleur.tirageCarte();
    }
    
    
    
    //  ================================== SCENARIO 5: DEFAITE: L'héliport est coulé ============================================================
    public static void scenario5() {
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
        Tuile PAb = new Tuile("Le Pont des Abîmes", EtatTuile.COULEE); 
        Tuile PBr = new Tuile("La Porte de Bronze", EtatTuile.COULEE); 
        Tuile COm = new Tuile("La Caverne des Ombres", EtatTuile.INONDEE); COm.setTresor(Tresor.CRISTAL);
        Tuile PFe = new Tuile("La Porte de Fer", EtatTuile.INONDEE);
        Tuile POr = new Tuile("La Porte d'Or", EtatTuile.COULEE);
        Tuile FOu = new Tuile("Les Falaises de l'Oubli", EtatTuile.COULEE);
        Tuile PCo = new Tuile("Le Palais de Corail", EtatTuile.INONDEE); PCo.setTresor(Tresor.CALICE);
        Tuile PAr = new Tuile("La Porte d'Argent", EtatTuile.INONDEE);
        Tuile DIl = new Tuile("Les Dunes de l'Illusion", EtatTuile.INONDEE);
        Tuile H = new Tuile("Heliport", EtatTuile.COULEE);
        Tuile PCu = new Tuile("La Porte de Cuivre", EtatTuile.COULEE);
        Tuile JHu = new Tuile("Le Jardin des Hurlements", EtatTuile.COULEE); JHu.setTresor(Tresor.ZEPHYR);
        Tuile FPo = new Tuile("La Forêt Pourpre", EtatTuile.COULEE);
        Tuile LPe = new Tuile("Le Lagon Perdu", EtatTuile.INONDEE);
        Tuile MBr = new Tuile("Le Marais Brumeux", EtatTuile.INONDEE);
        Tuile O = new Tuile("Observatoire", EtatTuile.INONDEE);
        Tuile RFa = new Tuile("Le Rocher Fantôme", EtatTuile.COULEE);
        Tuile CBr = new Tuile("La Caverne du Brasier", EtatTuile.COULEE); CBr.setTresor(Tresor.CRISTAL);
        Tuile TSo = new Tuile("Le Temple du Soleil", EtatTuile.COULEE); TSo.setTresor(Tresor.PIERRE);
        Tuile TLu = new Tuile("Le Temple de la Lune", EtatTuile.INONDEE); TLu.setTresor(Tresor.PIERRE);
        Tuile PMa = new Tuile("Le Palais des Marées", EtatTuile.INONDEE); PMa.setTresor(Tresor.CALICE);
        Tuile VCr = new Tuile("Le Val du Crépuscule", EtatTuile.COULEE);
        Tuile TGu = new Tuile("La Tour du Guet", EtatTuile.INONDEE);
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
            if(5<=i && i<10){
                CarteTresor ct = new CarteTresor(Tresor.PIERRE);
                listeCartes.put(ct.getId(), ct);
            }
            if(10<=i && i<15){
                CarteTresor ct = new CarteTresor(Tresor.CRISTAL);
                listeCartes.put(ct.getId(), ct);
            }
            if(15<=i && i<20){
                CarteTresor ct = new CarteTresor(Tresor.ZEPHYR);
                listeCartes.put(ct.getId(), ct);
            }
            if(20<=i && i<25){
                CarteSacsDeSable ct = new CarteSacsDeSable();
                listeCartes.put(ct.getId(), ct);
            }
            if(25<=i && i<30){
                CarteHelicoptere ct = new CarteHelicoptere();
                listeCartes.put(ct.getId(), ct);
            }
            if(30<=i && i<31){
            CarteMonteeDesEaux ct = new CarteMonteeDesEaux();
            listeCartes.put(ct.getId(), ct);
            }
            i++;
        }
        
        LinkedHashMap<Integer, VueCarte> piocheTirage = new LinkedHashMap();
        
        for(Integer key : listeCartes.keySet()){
            VueCarte vc = new VueCarte(listeCartes.get(key).getNom());
            piocheTirage.put(key, vc);
        }
        
        LinkedHashMap<Integer, Aventurier> joueursSurGrille = new LinkedHashMap();
        Aventurier joueur1 = new Ingenieur("joueur1", PFe, rouge); joueursSurGrille.put(joueur1.getId(), joueur1);PMa.arriveeJoueur(joueur1); joueur1.setPosition(PMa);
        Aventurier joueur2 = new Explorateur("joueur2", PCo, vert); joueursSurGrille.put(joueur2.getId(), joueur2);PCu.arriveeJoueur(joueur2);
        Aventurier joueur3 = new Plongeur("joueur3", DIl, violet); joueursSurGrille.put(joueur3.getId(), joueur3);PFe.arriveeJoueur(joueur3);
        Aventurier joueur4 = new Pilote("joueur4", O, bleu); joueursSurGrille.put(joueur4.getId(), joueur4);H.arriveeJoueur(joueur4);
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
        controleur.setPiocheTirage(listeCartes);
        
        
        
        
     
        // INNITIALISATION PLATEAU
        
        LinkedHashMap<Integer, VueTuile> vuesTuiles= new LinkedHashMap<>();
        for(Integer key : listeTuiles.keySet()){
            VueTuile vT = controleur.initVueTuile(listeTuiles.get(key));
            vuesTuiles.put(vT.getIdVueTuile(), vT);
        }
      
        MessageBox mb = new MessageBox();mb.setCaliceVisible();
        VueGrille vG = new VueGrille(vuesTuiles);
        
        
        VuePlateau vP = new VuePlateau(vG, vuesAventurier, mb, new VueNiveau(2));
        controleur.setVuePlateau(vP);
        vP.setListeVuesCartes(piocheTirage);
        vP.addObserver(controleur);
        vP.afficher();
        controleur.tirageCarte();
    }
}

