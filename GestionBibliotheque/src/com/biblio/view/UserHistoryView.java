package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserHistoryView extends JFrame {
    public JTable table = new JTable();
    public JButton btnProlonger = new JButton("Prolonger (+15 jours)");
    public JButton btnRetour = new JButton("Retour Catalogue");
    
    public DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int r, int c) { return false; }
    };

    public UserHistoryView() {
        setTitle("Mes Emprunts & Historique");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("Mon Historique d'Emprunts");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setBorder(new EmptyBorder(10,10,10,10));
        add(title, BorderLayout.NORTH);

        model.setColumnIdentifiers(new Object[]{"ID", "Livre", "Date Emprunt", "Date Retour Prévue", "Etat"});
        table.setModel(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bot = new JPanel();
        btnProlonger.setBackground(new Color(230, 126, 34)); 
        btnProlonger.setForeground(Color.WHITE);
        bot.add(btnProlonger);
        bot.add(btnRetour);
        add(bot, BorderLayout.SOUTH);
    }
}