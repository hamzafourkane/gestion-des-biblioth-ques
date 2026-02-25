package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginView extends JFrame {
    public JTextField txtUser = new JTextField();
    public JPasswordField txtPass = new JPasswordField();
    public JComboBox<String> comboRole = new JComboBox<>(new String[]{"ADMIN", "MEMBRE"});
    public JButton btnLogin = new JButton("SE CONNECTER");
    public JButton btnSignup = new JButton("Pas de compte ? S'inscrire");

    public LoginView() {
        setTitle("Bibliothèque - Accès");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        mainPanel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Connexion");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(50, 50, 150));
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);


        txtUser.setPreferredSize(new Dimension(300, 30));
        txtPass.setPreferredSize(new Dimension(300, 30));
        btnLogin.setBackground(new Color(50, 50, 150));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnSignup.setBackground(Color.WHITE);
        btnSignup.setForeground(new Color(50, 50, 150));
        btnSignup.setBorderPainted(false);
        btnSignup.setFocusPainted(false);
        btnSignup.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(lblTitle);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(new JLabel("Identifiant :"));
        mainPanel.add(txtUser);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JLabel("Mot de passe :"));
        mainPanel.add(txtPass);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(new JLabel("Rôle :"));
        mainPanel.add(comboRole);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(btnLogin);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(btnSignup);

        add(mainPanel);
    }
}