# 📚 BiblioTech - Système de Gestion de Bibliothèque

## Description
BiblioTech est une application desktop Java Swing pour la gestion complète d'une bibliothèque : gestion des livres, adhérents, emprunts et retours. Le projet met en œuvre la POO, JDBC et une architecture MVC.



## Fonctionnalités
- Gestion des livres (ajout, modification, suppression)
- Gestion des utilisateurs et adhérents
- Emprunt et retour automatisés
- Statistiques et historique
- Authentification (admin, utilisateur)



## Prérequis
- **Java JDK** : 8, 11 ou 17 (recommandé)
- **IDE** : Eclipse ou IntelliJ IDEA
- **SGBD** : MySQL



## 🚀 Installation

### 1️⃣ Base de données
1. Ouvrez votre outil d'administration (phpMyAdmin ou SQL Developer).
2. Créez une nouvelle base nommée **bibliotheque_db**.
3. Importez le script SQL : [`schema.sql`](schema.sql).

### 2️⃣ Application
1. Clonez le dépôt ou téléchargez le ZIP.
2. Importez dans Eclipse : `File > Import > Existing Projects into Workspace`.
3. Ajoutez le driver JDBC : `Build Path > Configure Build Path > Add External JARs` (fichier .jar dans `/lib`).
4. Configurez la connexion dans la classe `DbConnection.java` avec vos identifiants locaux.



## 🏁 Démarrage rapide

### 💻 Via Eclipse
1. Localisez la classe principale : [`Main.java`](src/com/biblio/main/Main.java)
2. Clic droit > **Run As > Java Application**

### 📦 Via Exécutable (si généré)
```sh
java -jar BiblioTech.jar



## 🧪 Données de test

### 👤 Comptes de test
| Rôle           | Identifiant | Mot de passe |
|----------------|-------------|--------------|
| Administrateur | hamza       | 1212         |
| Utilisateur    | user123     | user123      |

### 📚 Exemples d'objets
- **Ajout livre** :
	- Titre : *Le Petit Prince*
	- ISBN : `978-3-16-148410-0`
	- Quantité : `5`
- **Emprunt** :
	- ID Adhérent : `101`
	- Date retour prévue : `[Date du jour + 14 jours]`

