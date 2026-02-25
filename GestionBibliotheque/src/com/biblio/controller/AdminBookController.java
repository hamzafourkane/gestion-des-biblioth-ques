package com.biblio.controller;

import com.biblio.dao.LivreDAO;
import com.biblio.model.Livre;
import com.biblio.view.*;
import javax.swing.*;
import java.awt.Image;
import java.io.File;
import java.util.List;

public class AdminBookController {
    private AdminBookView view;
    private AdminDashboardView dash;
    private LivreDAO dao;
    private String currentImagePath = ""; 

    public AdminBookController(AdminBookView view, AdminDashboardView dash) {
        this.view = view;
        this.dash = dash;
        this.dao = new LivreDAO();
        
        refresh(); 

        
        view.table.getSelectionModel().addListSelectionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row != -1 && !e.getValueIsAdjusting()) {
                view.txtTitre.setText(view.model.getValueAt(row, 1).toString());
                view.txtAuteur.setText(view.model.getValueAt(row, 2).toString());
                view.txtIsbn.setText(view.model.getValueAt(row, 3).toString());
                
                
            }
        });

     
        view.btnChoisirImage.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {

                currentImagePath = fc.getSelectedFile().getAbsolutePath().replace("\\", "/");
                view.lblImageName.setText(fc.getSelectedFile().getName());
            }
        });


        view.btnAjouter.addActionListener(e -> {
            if(validate()) {
                Livre l = new Livre(0, view.txtTitre.getText(), view.txtAuteur.getText(), view.txtIsbn.getText(), true, currentImagePath);
                dao.ajouter(l);
                clear();
                refresh();
                JOptionPane.showMessageDialog(view, "Livre ajouté !");
            }
        });


        view.btnModifier.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row != -1) {
                if(validate()) {
                    int id = (int) view.model.getValueAt(row, 0);

                    
                    Livre l = new Livre(id, view.txtTitre.getText(), view.txtAuteur.getText(), view.txtIsbn.getText(), true, currentImagePath);
                    dao.modifier(l); 
                    clear();
                    refresh();
                    JOptionPane.showMessageDialog(view, "Livre modifié !");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Sélectionnez un livre.");
            }
        });

       
        view.btnSupprimer.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if(row != -1) {
                int confirm = JOptionPane.showConfirmDialog(view, "Supprimer ce livre ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if(confirm == JOptionPane.YES_OPTION) {
                    int id = (int) view.model.getValueAt(row, 0);
                    dao.supprimer(id);
                    clear();
                    refresh();
                }
            } else {
                JOptionPane.showMessageDialog(view, "Sélectionnez un livre.");
            }
        });
        
       
        view.chkTrier.addActionListener(e -> refresh());

       
        view.btnRetour.addActionListener(e -> {
            view.dispose();
            dash.setVisible(true);
        });
    }

    private void refresh() {
        view.model.setRowCount(0);
        List<Livre> list = dao.listerTout();
        
      
        if(view.chkTrier.isSelected()) {
            list.sort((l1, l2) -> l1.getTitre().compareToIgnoreCase(l2.getTitre()));
        }

        for (Livre l : list) {
            ImageIcon icon = null;
            if (l.getImagePath() != null && !l.getImagePath().isEmpty() && new File(l.getImagePath()).exists()) {
                icon = new ImageIcon(new ImageIcon(l.getImagePath()).getImage().getScaledInstance(60, 80, Image.SCALE_SMOOTH));
            }
            view.model.addRow(new Object[]{l.getId(), l.getTitre(), l.getAuteur(), l.getIsbn(), icon});
        }
    }

    private void clear() {
        view.txtTitre.setText("");
        view.txtAuteur.setText("");
        view.txtIsbn.setText("");
        view.lblImageName.setText("Aucune");
        currentImagePath = "";
        view.table.clearSelection();
    }

    private boolean validate() {
        if(view.txtTitre.getText().isEmpty() || view.txtAuteur.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Champs Titre et Auteur obligatoires.");
            return false;
        }
        return true;
    }
}