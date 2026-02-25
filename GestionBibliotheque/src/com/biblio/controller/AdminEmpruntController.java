package com.biblio.controller;

import com.biblio.dao.EmpruntDAO;
import com.biblio.model.Emprunt;
import com.biblio.view.*;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminEmpruntController {
    private AdminEmpruntView view;
    private AdminDashboardView dash;
    private EmpruntDAO dao;

    public AdminEmpruntController(AdminEmpruntView view, AdminDashboardView dash) {
        this.view = view;
        this.dash = dash;
        this.dao = new EmpruntDAO();
        refresh();

        view.btnRetourner.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if (row != -1) {
                int empruntId = (int) view.model.getValueAt(row, 0);
                dao.retourner(empruntId);
                JOptionPane.showMessageDialog(view, "Retour validé !");
                refresh();
            } else {
                JOptionPane.showMessageDialog(view, "Sélectionnez un emprunt.");
            }
        });

        view.btnRetour.addActionListener(e -> {
            view.dispose();
            dash.setVisible(true);
        });
    }

    private void refresh() {
        view.model.setRowCount(0);
        List<Emprunt> list = dao.listerEmpruntsEnCours();
        for (Emprunt e : list) {
            view.model.addRow(new Object[]{e.getId(), e.getUserName(), e.getLivreTitre(), e.getDateEmprunt(), e.getDateRetour()});
        }
    }
}