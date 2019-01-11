/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author rousstan
 */
public class VueGrille extends JPanel{
    
    
    
     public VueGrille() {
    
        super();
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

    }
}
