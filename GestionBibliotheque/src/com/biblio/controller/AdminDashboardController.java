package com.biblio.controller;

import com.biblio.view.*;

public class AdminDashboardController {
    
    public AdminDashboardController(AdminDashboardView view) {
        
        
        view.btnGestionLivres.addActionListener(e -> {
            view.setVisible(false);
            AdminBookView bookView = new AdminBookView();
            new AdminBookController(bookView, view); 
            bookView.setVisible(true);
        });

     
        view.btnGestionUsers.addActionListener(e -> {
            view.setVisible(false);
            AdminUserView userView = new AdminUserView();
            new AdminUserController(userView, view);
            userView.setVisible(true);
        });

        
        view.btnGestionEmprunts.addActionListener(e -> {
            view.setVisible(false);
            AdminEmpruntView empView = new AdminEmpruntView();
            new AdminEmpruntController(empView, view);
            empView.setVisible(true);
        });

      
        view.btnStats.addActionListener(e -> {
            view.setVisible(false);
            AdminStatsView statsView = new AdminStatsView();
            new AdminStatsController(statsView, view);
            statsView.setVisible(true);
        });

     
        view.btnLogout.addActionListener(e -> {
            view.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        });
    }
}