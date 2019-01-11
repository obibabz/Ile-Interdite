package vues;
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class VueTuile extends JPanel {
    
    
    public VueTuile() {
        super();
        this.setLayout(new BorderLayout());
        
        JPanel panelBas = new JPanel(new GridLayout(1,5));
        this.add(panelBas, BorderLayout.SOUTH);
        
        JPanel pion1 = new JPanel();
        pion1.setBackground(Color.red);
        JPanel pion2 = new JPanel();
        pion2.setBackground(Color.orange);
        JPanel pion3 = new JPanel();
        pion3.setBackground(Color.lightGray);
        JPanel pion4 = new JPanel();
        pion4.setBackground(Color.yellow);
        JPanel tresor = new JPanel();
        tresor.setBackground(Color.magenta);//couleurs de test
        panelBas.add(pion1);
        panelBas.add(pion2);
        panelBas.add(pion3);
        panelBas.add(pion4);
        
    }
    
}