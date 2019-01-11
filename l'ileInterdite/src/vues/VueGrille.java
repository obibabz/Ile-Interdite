/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rousstan
 */
public class VueGrille extends JPanel{
    private final ArrayList<VueTuile> listeTuiles;
    
    
    
     public VueGrille(ArrayList<VueTuile> listeTuiles) {
    
        super();
        this.listeTuiles = listeTuiles;
        this.setLayout(new GridLayout(6,6));
        int i= 0;
        
        while(i <= 35){
            switch(i) {
                case 0: case 1: case 4: case 5: case 6: case 11: case 24: case 29: case 30: case 31: case 34: case 35:
                    JLabel vide = new JLabel();
                    vide.setBackground(Color.black);
                    this.add(vide);
                case 2: case 3: case 7 : case 8: case 9: case 10: case 12: case 13: case 14: case 15: case 16: case 17: case 18: case 19: case 20: case 21: case 22: case 23: case 25: case 26: case 27: case 28: case 32: case 33:
                    this.add(listeTuiles.get(i));
                
            }
        }
        /*
        this.setLayout(new GridLayout(10,10));

        int i = 0;
        while (i<100) {
            this.add(new JPanel());
            i++;
        }

        int j = 0;
        while (j<100) {
        this.getComponent(j).setBackground(Color.BLUE);
        this.add(new VueTuile());
        j++;
        }

        this.getComponent(14).setBackground(Color.GREEN);
        this.getComponent(15).setBackground(Color.GREEN);
        this.getComponent(23).setBackground(Color.GREEN);
        this.getComponent(24).setBackground(Color.GREEN);
        this.getComponent(25).setBackground(Color.GREEN);
        this.getComponent(26).setBackground(Color.GREEN);
        this.getComponent(32).setBackground(Color.GREEN);
        this.getComponent(33).setBackground(Color.GREEN);
        this.getComponent(34).setBackground(Color.GREEN);
        this.getComponent(35).setBackground(Color.GREEN);
        this.getComponent(36).setBackground(Color.GREEN);
        this.getComponent(37).setBackground(Color.GREEN);
        this.getComponent(41).setBackground(Color.GREEN);
        this.getComponent(42).setBackground(Color.GREEN);
        this.getComponent(43).setBackground(Color.GREEN);
        this.getComponent(44).setBackground(Color.GREEN);
        this.getComponent(45).setBackground(Color.GREEN);
        this.getComponent(46).setBackground(Color.GREEN);
        this.getComponent(47).setBackground(Color.GREEN);
        this.getComponent(48).setBackground(Color.GREEN);
        this.getComponent(51).setBackground(Color.GREEN);
        this.getComponent(52).setBackground(Color.GREEN);
        this.getComponent(53).setBackground(Color.GREEN);
        this.getComponent(54).setBackground(Color.GREEN);
        this.getComponent(55).setBackground(Color.GREEN);
        this.getComponent(56).setBackground(Color.GREEN);
        this.getComponent(57).setBackground(Color.GREEN);
        this.getComponent(58).setBackground(Color.GREEN);
        this.getComponent(62).setBackground(Color.GREEN);
        this.getComponent(63).setBackground(Color.GREEN);
        this.getComponent(64).setBackground(Color.GREEN);
        this.getComponent(65).setBackground(Color.GREEN);
        this.getComponent(66).setBackground(Color.GREEN);
        this.getComponent(67).setBackground(Color.GREEN);
        this.getComponent(73).setBackground(Color.GREEN);
        this.getComponent(74).setBackground(Color.GREEN);
        this.getComponent(75).setBackground(Color.GREEN);
        this.getComponent(76).setBackground(Color.GREEN);
        this.getComponent(84).setBackground(Color.GREEN);
        this.getComponent(85).setBackground(Color.GREEN);
*/
}
//  public VueGrille getVueGrille(int x) {
//      return this.getComponent(x).vueTuile;
//  }
     
}
