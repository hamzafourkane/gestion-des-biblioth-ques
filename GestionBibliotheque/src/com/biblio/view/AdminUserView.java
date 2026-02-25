package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminUserView extends JFrame {
    public JTextField txtUsername = new JTextField(10);


    public JTextField txtPassword = new JTextField(10); 
    
    public JComboBox<String> comboRole = new JComboBox<>(new String[]{"MEMBRE", "ADMIN"});
    
    public JButton btnAjouter = new JButton("Ajouter");
    public JButton btnModifier = new JButton("Modifier");
    public JButton btnSupprimer = new JButton("Supprimer");
    public JButton btnRetour = new JButton("Retour");

    public DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int r, int c) { return false; } 
    };
    public JTable table = new JTable(model);

    public AdminUserView() {
        setTitle("Gestion Utilisateurs (Mots de passe visibles)");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        top.setBackground(new Color(230, 230, 230));
        
        top.add(new JLabel("Utilisateur:")); 
        top.add(txtUsername);
        
        top.add(new JLabel("Mot de passe:")); 
        top.add(txtPassword);
        
        top.add(new JLabel("Rôle:")); 
        top.add(comboRole);
        
        add(top, BorderLayout.NORTH);


        model.addColumn("ID"); 
        model.addColumn("Username"); 
        model.addColumn("Password");
        model.addColumn("Role");
        
        table.setModel(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bot = new JPanel();
        bot.add(btnAjouter);
        bot.add(btnModifier);
        bot.add(btnSupprimer);
        bot.add(btnRetour);
        add(bot, BorderLayout.SOUTH);
    }
}