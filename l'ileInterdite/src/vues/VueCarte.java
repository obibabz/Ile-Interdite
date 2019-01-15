/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Administrateur
 */
public class VueCarte extends JPanel{
    private final String nomCarte;
    private final JButton btnCarte;

    public VueCarte(String nom) {
        this.setLayout(new BorderLayout());
        this.nomCarte = nom;
        btnCarte = new JButton(nomCarte);btnCarte.setFont(new Font("Serif", Font.PLAIN, 10));
        //btnCarte.setMargin(m);
        this.setBorder(BorderFactory.createLineBorder(Color.darkGray, 1));
        this.setPreferredSize(new Dimension(200,50));
        this.add(btnCarte, BorderLayout.CENTER);
        btnCarte.setEnabled(false);
    }

    public JButton getBtnCarte() {
        return btnCarte;
    }
    
}
