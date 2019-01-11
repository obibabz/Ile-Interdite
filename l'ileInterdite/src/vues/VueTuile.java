package vues;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.Utils.EtatTuile;

public class VueTuile extends JPanel {
    private final JButton tuile;
    private final ArrayList<JPanel> casesJoueurs;
    
    
    public VueTuile(String nomTuile, String tresor, String etat, ArrayList<Color> joueursSurTuile) {
        super();
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1));
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
        
        casesJoueurs = new ArrayList<>();
        JPanel case1 = new JPanel();
        
        case1.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        casesJoueurs.add(case1);
        JPanel case2 = new JPanel();
        case2.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        casesJoueurs.add(case2);
        JPanel case3 = new JPanel();
        case3.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        casesJoueurs.add(case3);
        JPanel case4 = new JPanel();
        case4.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        casesJoueurs.add(case4);
        panelBas.add(case1);
        panelBas.add(case2);
        panelBas.add(case3);
        panelBas.add(case4);
        
        int i = 0;
        while(i <= joueursSurTuile.size()-1){
            System.out.println(i);
            System.out.println(joueursSurTuile.get(i).toString());
            System.out.println(casesJoueurs.get(i).toString());
            casesJoueurs.get(i).setBackground(joueursSurTuile.get(i));
            i++;
        }
        
        
    }

    public JButton getTuile() {
        return tuile;
    }

    public ArrayList<JPanel> getCasesJoueurs() {
        return casesJoueurs;
    }
    
}