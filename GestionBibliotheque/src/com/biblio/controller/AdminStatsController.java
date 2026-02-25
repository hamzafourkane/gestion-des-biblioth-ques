package com.biblio.controller;

import com.biblio.dao.StatsDAO;
import com.biblio.view.AdminStatsView;
import com.biblio.view.AdminDashboardView;

public class AdminStatsController {
    public AdminStatsController(AdminStatsView view, AdminDashboardView dash) {
        StatsDAO dao = new StatsDAO();
        
        
        view.lblNbLivres.setText(String.valueOf(dao.getNombreLivres()));
        view.lblNbUsers.setText(String.valueOf(dao.getNombreUtilisateurs()));
        view.lblEmpruntsActifs.setText(String.valueOf(dao.getEmpruntsActifs()));
        view.lblTopLivre.setText(dao.getLivrePopulaire());

        view.btnRetour.addActionListener(e -> {
            view.dispose();
            dash.setVisible(true);
        });
    }
}