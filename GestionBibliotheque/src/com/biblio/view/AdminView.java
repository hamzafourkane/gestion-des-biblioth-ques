package com.biblio.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminView extends JFrame {

    public JTextField txtTitre = new JTextField(10);
    public JTextField txtAuteur = new JTextField(10);
    public JTextField txtIsbn = new JTextField(10);
    public JButton btnAjouter = new JButton("Ajouter Livre");


    public JTextField txtRecherche = new JTextField(15);
    
    // Table
    public DefaultTableModel model = new DefaultTableModel();
    public JTable table = new JTable(model);

    public AdminView(String adminName) {
        setTitle("Espace Administrateur - " + adminName);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel panelForm = new JPanel(new FlowLayout());
        panelForm.setBorder(BorderFactory.createTitledBorder("Nouveau Livre"));
        panelForm.add(new JLabel("Titre:")); panelForm.add(txtTitre);
        panelForm.add(new JLabel("Auteur:")); panelForm.add(txtAuteur);
        panelForm.add(new JLabel("ISBN:")); panelForm.add(txtIsbn);
        panelForm.add(btnAjouter);
        add(panelForm, BorderLayout.NORTH);


        model.addColumn("ID");
        model.addColumn("Titre");
        model.addColumn("Auteur");
        model.addColumn("ISBN");
        model.addColumn("Dispo");
        add(new JScrollPane(table), BorderLayout.CENTER);


        JPanel panelSearch = new JPanel();
        panelSearch.add(new JLabel("Filtrer par titre (Dynamique) : "));
        panelSearch.add(txtRecherche);
        add(panelSearch, BorderLayout.SOUTH);
    }
}