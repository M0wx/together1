# Together1 API - Application de Gestion de Tâches Collaborative

Together1 API est le backend d'une plateforme de gestion de tâches collaborative inspirée d'outils comme Mantis, Jira et Trello. L'application permet à des utilisateurs de créer des projets, d'inviter des collaborateurs et de suivre l'avancement des tâches en fonction de rôles précis.

---

## Fonctionnalités principales

- **Gestion des Utilisateurs** : Inscription, connexion et profil utilisateur.
- **Gestion des Projets** : Création de projets et gestion des membres associés.
- **Système de Rôles & Permissions** :
  - **OWNER** : Créateur du projet avec un contrôle total.
  - **COLLABORATOR** : Membre invité pouvant ajouter des tâches et modifier leur statut.
  - **VIEWER** : Membre en lecture seule (consultation uniquement).
- **Gestion des Tâches** : Création de tâches, assignation à un membre, description, et suivi de la progression (de 0 à 100%).

---

## Stack Technique

- **Langage** : Java 21
- **Framework Principal** : Spring Boot 4.x
- **Persistance & Données** : Spring Data JPA (Hibernate)
- **Base de Données** : PostgreSQL
- **Utilitaires** : Lombok (génération automatique des getters, setters, constructeurs)
- **Gestionnaire de dépendances** : Maven

---

## Modèle de Données

Voici l'architecture relationnelle des entités du projet :

```mermaid
classDiagram
    direction LR
    class User {
        +Long id
        +String name
        +String email
        +String password
        +LocalDateTime createdAt
    }

    class Project {
        +Long id
        +String name
        +String description
        +LocalDateTime createdAt
        +LocalDateTime dueDate
        +User creator
    }

    class ProjectMember {
        +Long id
        +Project project
        +User user
        +ProjectRole role
    }

    class ProjectRole {
        <<enumeration>>
        OWNER
        COLLABORATOR
        VIEWER
    }

    class Task {
        +Long id
        +String title
        +String description
        +Integer progressPercentage
        +LocalDateTime dueDate
        +TaskPriority priority
        +List~String~ tags
        +Project project
        +User assignee
    }

    class TaskPriority {
        <<enumeration>>
        LOW
        MEDIUM
        HIGH
    }

    User "1" --> "*" Project : "crée"
    Project "1" *-- "*" ProjectMember : "a pour membres"
    User "1" <-- "*" ProjectMember : "concerne"
    Project "1" *-- "*" Task : "contient"
    User "1" <-- "*" Task : "est assigné à"
```

---

## Configuration & Démarrage

### Prérequis
- **Java 21** ou version supérieure installé.
- **PostgreSQL** installé et en cours d'exécution.

### 1. Configuration de la base de données
Assurez-vous que la base de données `together1` existe dans votre PostgreSQL local. Vous pouvez la créer avec la commande SQL suivante :
```sql
CREATE DATABASE together1;
```

Les paramètres de connexion sont définis dans le fichier [application.properties](src/main/resources/application.properties) :
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/together1
spring.datasource.username=postgres
spring.datasource.password=manoa
spring.jpa.hibernate.ddl-auto=update
```

### 2. Lancement du serveur backend
Pour démarrer le projet en mode développement, ouvrez un terminal à la racine du projet et exécutez :

```powershell
./mvnw spring-boot:run
```

Le serveur sera accessible par défaut à l'adresse : `http://localhost:8080`
