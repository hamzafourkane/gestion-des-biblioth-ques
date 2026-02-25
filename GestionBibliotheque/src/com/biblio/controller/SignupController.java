package com.biblio.controller;

import com.biblio.dao.UserDAO;
import com.biblio.view.LoginView;
import com.biblio.view.SignupView;
import java.sql.SQLIntegrityConstraintViolationException; // Pour gérer le doublon
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SignupController {
    private SignupView view;
    private UserDAO dao;

    public SignupController(SignupView view) {
        this.view = view;
        this.dao = new UserDAO();

        view.btnValider.addActionListener(e -> inscription());

        view.btnRetourLogin.addActionListener(e -> {
            view.dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        });
    }

    private void inscription() {
        String username = view.txtUser.getText();
        String password = new String(view.txtPass.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Veuillez remplir tous les champs !", "Erreur", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {

            dao.ajouter(username, password, "MEMBRE");


            JOptionPane.showMessageDialog(view, "Compte créé avec succès ! Connectez-vous.", "Succès", JOptionPane.INFORMATION_MESSAGE);
            
            view.dispose();
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);

        } catch (SQLIntegrityConstraintViolationException e) {

            JOptionPane.showMessageDialog(view, "L'identifiant '" + username + "' est déjà pris !", "Erreur Inscription", JOptionPane.ERROR_MESSAGE);
            view.txtUser.setText("");
            view.txtUser.requestFocus();
            
        } catch (SQLException e) {

            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Erreur technique : " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }
}