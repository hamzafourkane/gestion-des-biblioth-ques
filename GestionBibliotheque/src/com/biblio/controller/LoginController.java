package com.biblio.controller;

import com.biblio.dao.UserDAO;
import com.biblio.model.User;
import com.biblio.view.*;
import javax.swing.JOptionPane;

public class LoginController {
    private LoginView view;
    private UserDAO dao;

    public LoginController(LoginView view) {
        this.view = view;
        this.dao = new UserDAO();

       
        this.view.btnLogin.addActionListener(e -> login());

       
        this.view.btnSignup.addActionListener(e -> {
            view.dispose();
            SignupView signupView = new SignupView();
            new SignupController(signupView);
            signupView.setVisible(true);
        });
    }

    private void login() {
        String u = view.txtUser.getText();
        String p = new String(view.txtPass.getPassword());
        String role = (String) view.comboRole.getSelectedItem();

        User user = dao.authenticate(u, p);

        if (user != null) {
            if (user.getRole().equalsIgnoreCase(role)) {
                view.dispose();
                if (user.getRole().equals("ADMIN")) {
                    AdminDashboardView dash = new AdminDashboardView(user.getUsername());
                    new AdminDashboardController(dash);
                    dash.setVisible(true);
                } else {
                    UserCatalogView cat = new UserCatalogView(user.getUsername());
                    new UserCatalogController(cat, user); 
                    cat.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Rôle incorrect pour cet utilisateur !");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Identifiants incorrects !");
        }
    }
}