package com.biblio.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminBookView extends JFrame {

    public JTextField txtTitre = new JTextField();
    public JTextField txtAuteur = new JTextField();
    public JTextField txtIsbn = new JTextField();


    public JButton btnChoisirImage = new JButton("Choisir Image...");
    public JLabel lblImageName = new JLabel("Aucune");

    public JButton btnAjouter = new JButton("Ajouter");
    public JButton btnModifier = new JButton("Modifier");
    public JButton btnSupprimer = new JButton("Supprimer");
    public JButton btnRetour = new JButton("Retour Dashboard");


    public JCheckBox chkTrier = new JCheckBox("Trier A-Z");


    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int r, int c) { return false; }

        public Class<?> getColumnClass(int c) { return c == 4 ? Icon.class : Object.class; }
    };

    public AdminBookView() {
        setTitle("Gestion des Livres");
        setSize(1100, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(236, 240, 241));
        leftPanel.setPreferredSize(new Dimension(320, 0));
        leftPanel.setBorder(new EmptyBorder(10, 10, 10, 10));


        JPanel form = new JPanel(new GridLayout(9, 1, 5, 5)); 
        form.setOpaque(false);
        
        form.add(new JLabel("Titre :")); form.add(txtTitre);
        form.add(new JLabel("Auteur :")); form.add(txtAuteur);
        form.add(new JLabel("ISBN :")); form.add(txtIsbn);
        form.add(new JLabel("Couverture :"));
        
        JPanel imgP = new JPanel(new BorderLayout());
        imgP.setOpaque(false);
        imgP.add(btnChoisirImage, BorderLayout.WEST);
        imgP.add(lblImageName, BorderLayout.CENTER);
        form.add(imgP);
        
        form.add(chkTrier); 

        leftPanel.add(form, BorderLayout.NORTH);

        JPanel btnPanel = new JPanel(new GridLayout(4, 1, 5, 10));
        btnPanel.setOpaque(false);
        btnPanel.setBorder(new EmptyBorder(20, 0, 0, 0));
        
        styleButton(btnAjouter, new Color(46, 204, 113));
        styleButton(btnModifier, new Color(52, 152, 219));
        styleButton(btnSupprimer, new Color(231, 76, 60));
        styleButton(btnRetour, Color.GRAY);

        btnPanel.add(btnAjouter);
        btnPanel.add(btnModifier);
        btnPanel.add(btnSupprimer);
        btnPanel.add(btnRetour);

        leftPanel.add(btnPanel, BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);


        model.setColumnIdentifiers(new Object[]{"ID", "Titre", "Auteur", "ISBN", "Couverture"});
        table.setModel(model);
        table.setRowHeight(80); 
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btn.setFocusPainted(false);
    }
}