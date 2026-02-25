package com.biblio.controller;

import com.biblio.dao.LivreDAO;
import com.biblio.model.Livre;
import com.biblio.view.UserView;
import java.util.List;

public class UserController {
    public UserController(UserView view) {
        LivreDAO dao = new LivreDAO();
        List<Livre> livres = dao.listerTout();
        
        for (Livre l : livres) {
            view.model.addRow(new Object[]{
                l.getTitre(), 
                l.getAuteur(), 
                l.isDisponible() ? "Disponible" : "Emprunté"
            });
        }
    }
}