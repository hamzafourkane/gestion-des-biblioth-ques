package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserCatalogView extends JFrame {

    public JTextField txtSearch = new JTextField(20);


    public JButton btnEmprunter = new JButton("Emprunter");
    public JButton btnHistorique = new JButton("Mes Emprunts & Historique");
    public JButton btnLogout = new JButton("Déconnexion");
    
    // Table
    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel() {
        public Class<?> getColumnClass(int c) { return c == 3 ? Icon.class : Object.class; }
        public boolean isCellEditable(int r, int c) { return false; }
    };

    public UserCatalogView(String userName) {
        setTitle("Bibliothèque - Espace Membre : " + userName);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 152, 219));
        headerPanel.setBorder(new EmptyBorder(15, 15, 15, 15));


        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        JLabel lblSearch = new JLabel("Rechercher : ");
        lblSearch.setForeground(Color.WHITE);
        lblSearch.setFont(new Font("Arial", Font.BOLD, 14));
        searchPanel.add(lblSearch);
        searchPanel.add(txtSearch);


        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actionPanel.setOpaque(false);
        
        styleButton(btnEmprunter);
        styleButton(btnHistorique);
        
        btnLogout.setBackground(new Color(231, 76, 60));
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);

        actionPanel.add(btnEmprunter);
        actionPanel.add(Box.createHorizontalStrut(10));
        actionPanel.add(btnHistorique);
        actionPanel.add(Box.createHorizontalStrut(10));
        actionPanel.add(btnLogout);

        headerPanel.add(searchPanel, BorderLayout.WEST);
        headerPanel.add(actionPanel, BorderLayout.EAST);

        add(headerPanel, BorderLayout.NORTH);


        model.setColumnIdentifiers(new Object[]{"Titre", "Auteur", "Disponibilité", "Couverture"});
        table.setModel(model);
        table.setRowHeight(100); 
        table.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
    
    private void styleButton(JButton btn) {
        btn.setBackground(Color.WHITE);
        btn.setForeground(new Color(52, 152, 219));
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
    }
}