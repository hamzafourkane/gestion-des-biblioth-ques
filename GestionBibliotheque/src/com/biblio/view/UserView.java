package com.biblio.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class UserView extends JFrame {
    public DefaultTableModel model = new DefaultTableModel();
    public JTable table = new JTable(model);

    public UserView() {
        setTitle("Espace Consultation - Membre");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        model.addColumn("Titre");
        model.addColumn("Auteur");
        model.addColumn("Disponibilité");
        
        add(new JScrollPane(table));
    }
}