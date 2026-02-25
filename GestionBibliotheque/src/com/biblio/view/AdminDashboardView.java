package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminDashboardView extends JFrame {

    public JButton btnGestionLivres = new JButton("Gestion des Livres");
    public JButton btnGestionUsers = new JButton("Gestion Utilisateurs");
    public JButton btnGestionEmprunts = new JButton("Gestion Emprunts & Retours");
    public JButton btnStats = new JButton("Statistiques & Rapports");
    public JButton btnLogout = new JButton("Déconnexion");

    public AdminDashboardView(String adminName) {
        setTitle("Tableau de Bord Administrateur");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel header = new JPanel();
        header.setBackground(new Color(44, 62, 80)); 
        JLabel title = new JLabel("Bienvenue, Admin : " + adminName);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setBorder(new EmptyBorder(20, 15, 20, 15));
        header.add(title);
        add(header, BorderLayout.NORTH);

        
        JPanel menu = new JPanel(new GridLayout(5, 1, 15, 15));
        menu.setBorder(new EmptyBorder(40, 150, 40, 150));
        menu.setBackground(new Color(236, 240, 241)); 



        styleButton(btnGestionLivres, new Color(52, 152, 219));
        styleButton(btnGestionUsers, new Color(155, 89, 182));
        styleButton(btnGestionEmprunts, new Color(230, 126, 34));
        styleButton(btnStats, new Color(46, 204, 113));
        styleButton(btnLogout, new Color(192, 57, 43));

        menu.add(btnGestionLivres);
        menu.add(btnGestionUsers);
        menu.add(btnGestionEmprunts);
        menu.add(btnStats);
        menu.add(btnLogout);

        add(menu, BorderLayout.CENTER);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}