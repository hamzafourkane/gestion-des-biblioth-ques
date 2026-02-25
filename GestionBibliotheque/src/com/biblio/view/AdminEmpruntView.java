package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminEmpruntView extends JFrame {
    public JButton btnRetourner = new JButton("Valider Retour Livre");
    public JButton btnRetour = new JButton("Retour Dashboard");
    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int r, int c) { return false; }
    };

    public AdminEmpruntView() {
        setTitle("Suivi des Emprunts");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(new Color(230, 230, 230));
        top.setBorder(new EmptyBorder(10,10,10,10));
        btnRetourner.setBackground(new Color(39, 174, 96));
        btnRetourner.setForeground(Color.WHITE);
        top.add(btnRetourner);
        add(top, BorderLayout.NORTH);

        model.setColumnIdentifiers(new Object[]{"ID", "Utilisateur", "Livre", "Date Emprunt", "Date Retour Prévue"});
        table.setModel(model);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bot = new JPanel();
        bot.add(btnRetour);
        add(bot, BorderLayout.SOUTH);
    }
}