package vues;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Utils.EtatTuile;

public class VueTuile extends JPanel {
    private final JButton tuile;
    private final ArrayList<JLabel> casesJoueurs;
    
    
    public VueTuile(String nomTuile, String tresor, String etat, ArrayList<Color> joueursSurTuile) {
        super();
        this.setLayout(new BorderLayout());
        
        //Affichage du Tresor
        if(tresor != null){
            JLabel labelTresor = new JLabel(tresor);
            this.add(labelTresor, BorderLayout.NORTH);
        }
        
        //Affichage du bouton central
        tuile = new JButton(nomTuile);
        this.add(tuile, BorderLayout.CENTER);
        
        if (etat == EtatTuile.ASSECHEE.toString()){
            this.tuile.setBackground(Color.lightGray);
        }
        else if (etat == EtatTuile.COULEE.toString()){
            this.tuile.setBackground(Color.BLUE);
        }
        else{this.tuile.setBackground(Color.cyan);}
        
        //Affichage des joueurs sur la tuile
        JPanel panelBas = new JPanel(new GridLayout(1,4));
        this.add(panelBas, BorderLayout.SOUTH);
        int i = 0;
        casesJoueurs = new ArrayList<>();
        JLabel case1 = new JLabel();
        casesJoueurs.add(case1);
        JLabel case2 = new JLabel();
        casesJoueurs.add(case2);
        JLabel case3 = new JLabel();
        casesJoueurs.add(case3);
        JLabel case4 = new JLabel();
        casesJoueurs.add(case4);
        
        while(i <= joueursSurTuile.size()-1){
            casesJoueurs.get(i).setBackground(joueursSurTuile.get(i));
            i++;
        }
        
        
    }

    public JButton getTuile() {
        return tuile;
    }

    public ArrayList<JLabel> getCasesJoueurs() {
        return casesJoueurs;
    }
    
}