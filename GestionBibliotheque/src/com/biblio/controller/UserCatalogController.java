package com.biblio.controller;

import com.biblio.dao.EmpruntDAO;
import com.biblio.dao.LivreDAO;
import com.biblio.model.Livre;
import com.biblio.model.User;
import com.biblio.view.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class UserCatalogController {
    private UserCatalogView view;
    private LivreDAO livreDao;
    private EmpruntDAO empruntDao;
    private User currentUser;
    private List<Livre> cacheLivres; 

    public UserCatalogController(UserCatalogView view, User user) {
        this.view = view;
        this.currentUser = user;
        this.livreDao = new LivreDAO();
        this.empruntDao = new EmpruntDAO();


        this.cacheLivres = livreDao.listerTout();
        updateTable(cacheLivres);


        view.btnEmprunter.addActionListener(e -> {
            int row = view.table.getSelectedRow();
            if (row != -1) {

            	
                Livre selectedBook = findBookFromRow(row);

                if (selectedBook != null) {
                    if (selectedBook.isDisponible()) {

                        boolean ok = empruntDao.emprunter(currentUser.getId(), selectedBook.getId());
                        if (ok) {
                            JOptionPane.showMessageDialog(view, "Livre emprunté avec succès !");

                            this.cacheLivres = livreDao.listerTout();
                            updateTable(this.cacheLivres);
                        } else {
                            JOptionPane.showMessageDialog(view, "Erreur : Livre indisponible ou erreur technique.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Ce livre n'est pas disponible actuellement.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(view, "Veuillez sélectionner un livre dans la liste.");
            }
        });


        view.btnHistorique.addActionListener(e -> {
            view.setVisible(false);
            UserHistoryView histView = new UserHistoryView();

            new UserHistoryController(histView, view, currentUser); 
            histView.setVisible(true);
        });


        view.btnLogout.addActionListener(e -> {
            view.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        });


        view.txtSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String query = view.txtSearch.getText().toLowerCase();
                
                List<Livre> filteredList = cacheLivres.stream()
                    .filter(l -> l.getTitre().toLowerCase().contains(query) 
                              || l.getAuteur().toLowerCase().contains(query))
                    .collect(Collectors.toList());
                
                updateTable(filteredList);
            }
        });
    }

    
    private Livre findBookFromRow(int row) {
        String titre = (String) view.model.getValueAt(row, 0);
        String auteur = (String) view.model.getValueAt(row, 1);
        
        return cacheLivres.stream()
                .filter(l -> l.getTitre().equals(titre) && l.getAuteur().equals(auteur))
                .findFirst()
                .orElse(null);
    }

    private void updateTable(List<Livre> livres) {
        view.model.setRowCount(0);
        for (Livre l : livres) {
            ImageIcon icon = null;
            if (l.getImagePath() != null && !l.getImagePath().isEmpty() && new File(l.getImagePath()).exists()) {
                icon = new ImageIcon(new ImageIcon(l.getImagePath()).getImage().getScaledInstance(70, 90, Image.SCALE_SMOOTH));
            }
            view.model.addRow(new Object[]{
                l.getTitre(), 
                l.getAuteur(), 
                l.isDisponible() ? "Disponible" : "Indisponible", 
                icon
            });
        }
    }
}