package com.biblio.controller;

import com.biblio.dao.EmpruntDAO;
import com.biblio.model.Emprunt;
import com.biblio.model.User;
import com.biblio.view.*;
import java.util.List;
import javax.swing.JOptionPane;

public class UserHistoryController {
    private UserHistoryView view;
    private UserCatalogView parentView;
    private EmpruntDAO dao;
    private User currentUser;

    public UserHistoryController(UserHistoryView view, UserCatalogView parentView, User user) {
        this.view = view;
        this.parentView = parentView;
        this.currentUser = user;
        this.dao = new EmpruntDAO();
        
        refresh();


        view.btnProlonger.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if (row != -1) {
                String etat = (String) view.model.getValueAt(row, 4);
                if("En cours".equals(etat)) {
                    int id = (int) view.model.getValueAt(row, 0);
                    dao.prolonger(id);
                    JOptionPane.showMessageDialog(view, "Prêt prolongé de 15 jours !");
                    refresh();
                } else {
                    JOptionPane.showMessageDialog(view, "Impossible de prolonger un livre déjà rendu.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Sélectionnez un emprunt en cours.");
            }
        });

        
        view.btnRetour.addActionListener(e -> {
            view.dispose();
            parentView.setVisible(true);
        });
    }

    private void refresh() {
        view.model.setRowCount(0);
        List<Emprunt> list = dao.getHistoriqueByUser(currentUser.getId());
        for (Emprunt e : list) {
            view.model.addRow(new Object[]{
                e.getId(), 
                e.getLivreTitre(), 
                e.getDateEmprunt(), 
                e.getDateRetour(),
                e.isRetourne() ? "Retourné" : "En cours"
            });
        }
    }
}