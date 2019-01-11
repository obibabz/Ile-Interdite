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
    
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new GridLayout(10,10));

        int i = 0;
        while (i<100) {
            panelCentral.add(new JPanel());
            i++;
        }

        int j = 0;
        while (j<100) {
        panelCentral.getComponent(j).setBackground(Color.BLUE);
        j++;
        }

        panelCentral.getComponent(14).setBackground(Color.yellow);
        panelCentral.getComponent(15).setBackground(Color.yellow);
        panelCentral.getComponent(23).setBackground(Color.yellow);
        panelCentral.getComponent(24).setBackground(Color.yellow);
        panelCentral.getComponent(25).setBackground(Color.yellow);
        panelCentral.getComponent(26).setBackground(Color.yellow);
        panelCentral.getComponent(32).setBackground(Color.yellow);
        panelCentral.getComponent(33).setBackground(Color.yellow);
        panelCentral.getComponent(34).setBackground(Color.yellow);
        panelCentral.getComponent(35).setBackground(Color.yellow);
        panelCentral.getComponent(36).setBackground(Color.yellow);
        panelCentral.getComponent(37).setBackground(Color.yellow);
        panelCentral.getComponent(41).setBackground(Color.yellow);
        panelCentral.getComponent(42).setBackground(Color.yellow);
        panelCentral.getComponent(43).setBackground(Color.yellow);
        panelCentral.getComponent(44).setBackground(Color.yellow);
        panelCentral.getComponent(45).setBackground(Color.yellow);
        panelCentral.getComponent(46).setBackground(Color.yellow);
        panelCentral.getComponent(47).setBackground(Color.yellow);
        panelCentral.getComponent(48).setBackground(Color.yellow);
        panelCentral.getComponent(51).setBackground(Color.yellow);
        panelCentral.getComponent(52).setBackground(Color.yellow);
        panelCentral.getComponent(53).setBackground(Color.yellow);
        panelCentral.getComponent(54).setBackground(Color.yellow);
        panelCentral.getComponent(55).setBackground(Color.yellow);
        panelCentral.getComponent(56).setBackground(Color.yellow);
        panelCentral.getComponent(57).setBackground(Color.yellow);
        panelCentral.getComponent(58).setBackground(Color.yellow);
        panelCentral.getComponent(62).setBackground(Color.yellow);
        panelCentral.getComponent(63).setBackground(Color.yellow);
        panelCentral.getComponent(64).setBackground(Color.yellow);
        panelCentral.getComponent(65).setBackground(Color.yellow);
        panelCentral.getComponent(66).setBackground(Color.yellow);
        panelCentral.getComponent(67).setBackground(Color.yellow);
        panelCentral.getComponent(73).setBackground(Color.yellow);
        panelCentral.getComponent(74).setBackground(Color.yellow);
        panelCentral.getComponent(75).setBackground(Color.yellow);
        panelCentral.getComponent(76).setBackground(Color.yellow);
        panelCentral.getComponent(84).setBackground(Color.yellow);
        panelCentral.getComponent(85).setBackground(Color.yellow);

        
        
        
        
        
        
        
        
        this.add(panelCentral);
    }
}
