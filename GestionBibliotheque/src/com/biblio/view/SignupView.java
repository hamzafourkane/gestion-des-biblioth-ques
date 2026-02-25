package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SignupView extends JFrame {
    public JTextField txtUser = new JTextField();
    public JPasswordField txtPass = new JPasswordField();
    public JButton btnValider = new JButton("S'INSCRIRE");
    public JButton btnRetourLogin = new JButton("Déjà un compte ? Se connecter");

    public SignupView() {
        setTitle("Bibliothèque - Inscription");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Nouvelle Inscription");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(new Color(39, 174, 96)); 
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


        txtUser.setPreferredSize(new Dimension(300, 30));
        txtPass.setPreferredSize(new Dimension(300, 30));


        btnValider.setBackground(new Color(39, 174, 96));
        btnValider.setForeground(Color.WHITE);
        btnValider.setFocusPainted(false);
        btnValider.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnRetourLogin.setBackground(Color.WHITE);
        btnRetourLogin.setForeground(new Color(50, 50, 150));
        btnRetourLogin.setBorderPainted(false);
        btnRetourLogin.setFocusPainted(false);
        btnRetourLogin.setAlignmentX(Component.CENTER_ALIGNMENT);


        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(new JLabel("Choisir un Identifiant :"));
        mainPanel.add(txtUser);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JLabel("Choisir un Mot de passe :"));
        mainPanel.add(txtPass);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnValider);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnRetourLogin);

        add(mainPanel);
    }
}