# 📚 BiblioTech - Système de Gestion de Bibliothèque

## Description Courte

*BiblioTech* est une application desktop développée en Java Swing permettant la gestion complète d'une bibliothèque. Le projet repose sur une architecture robuste pour la gestion des stocks de livres, le suivi des adhérents et l'automatisation des processus d'emprunt et de retour. Il a été conçu en collaboration pour mettre en pratique les concepts avancés de la POO et la persistance des données via JDBC.

## Prérequis

Java JDK : 8, 11 ou 17 (version recommandée)

IDE : Eclipse (ou IntelliJ IDEA)

SGBD : MYSQL

Connecteur : MySQL JDBC Driver


### Instructions d’Installation

###Base de données

Ouvrez votre outil d'administration (phpMyAdmin ou SQL Developer).

Créez une nouvelle base de données nommée bibliotheque_db.

Importez le fichier script situé à la racine du projet : /schema.sql.


###Configuration de l'Application

Clonez le dépôt ou téléchargez le ZIP.

Importez le projet dans Eclipse : File > Import > Existing Projects into Workspace.

Ajout du Driver JDBC :

Clic droit sur le projet > Build Path > Configure Build Path.

Onglet Libraries > Add External JARs.

Sélectionnez le fichier .jar du driver présent dans le dossier /lib.

Configurez la connexion dans la classe Connexion.java (ou équivalent) avec vos identifiants locaux (User/Password).


###Commandes de Lancement Rapides


**Via Eclipse :**

Localisez la classe contenant la méthode main (souvent Main.java ou LoginFrame.java).

Clic droit > Run As > Java Application.


**Via Exécutable (si généré) :**

java -jar BiblioTech.jar


###Données de test

**Comptes de test (Accès Interface)**

Utilisez ces identifiants pour accéder aux différentes sessions :


*Administrateur :*

Login : hamza

Mot de passe : 1212


*Bibliothécaire (Standard) :*

Login : user123

Mot de passe : user123


**Exemples d'objets à tester**

Pour tester la validation des formulaires, vous pouvez essayer :

*Ajout d'un livre :*

Titre : Le Petit Prince

ISBN : 978-3-16-148410-0

Quantité : 5

*Enregistrement Emprunt :*

ID Adhérent : 101

Date de retour prévue : [Date du jour + 14 jours]

