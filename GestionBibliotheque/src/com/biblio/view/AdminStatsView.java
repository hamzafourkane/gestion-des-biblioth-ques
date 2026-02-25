package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminStatsView extends JFrame {
    public JLabel lblNbLivres = new JLabel("0");
    public JLabel lblNbUsers = new JLabel("0");
    public JLabel lblEmpruntsActifs = new JLabel("0");
    public JLabel lblTopLivre = new JLabel("Aucun");
    public JButton btnRetour = new JButton("Retour Dashboard");

    public AdminStatsView() {
        setTitle("Statistiques et Rapports");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Rapport d'activité de la Bibliothèque");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBorder(new EmptyBorder(20,0,20,0));
        add(title, BorderLayout.NORTH);


        JPanel grid = new JPanel(new GridLayout(2, 2, 20, 20));
        grid.setBorder(new EmptyBorder(20, 50, 20, 50));
        
        grid.add(createCard("Total Livres", lblNbLivres, new Color(52, 152, 219)));
        grid.add(createCard("Utilisateurs Inscrits", lblNbUsers, new Color(155, 89, 182)));
        grid.add(createCard("Emprunts en cours", lblEmpruntsActifs, new Color(230, 126, 34)));
        grid.add(createCard("Top Livre", lblTopLivre, new Color(46, 204, 113)));

        add(grid, BorderLayout.CENTER);
        
        JPanel bot = new JPanel();
        bot.add(btnRetour);
        add(bot, BorderLayout.SOUTH);
    }

    private JPanel createCard(String title, JLabel valueLabel, Color color) {
        JPanel p = new JPanel(new BorderLayout());
        p.setBackground(color);
        p.setBorder(new EmptyBorder(10,10,10,10));
        
        JLabel t = new JLabel(title);
        t.setForeground(Color.WHITE);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        p.add(t, BorderLayout.NORTH);
        p.add(valueLabel, BorderLayout.CENTER);
        return p;
    }
}