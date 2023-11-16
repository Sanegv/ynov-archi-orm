
# Projet Architecture logiciel

L'objectif de ce projet est de créer une librairie de type ORM.

# I- Sujet

Vous devez créer une librairie dans le langague de votre choix (Java, C#, PHP, JS, python) permettant d'aider le développeur sur la couche d'accès aux données.

## Un ORM

Un mapping objet-relationnel (en anglais object-relational mapping ou ORM) est un type de programme informatique qui se place en interface entre un programme applicatif et une base de données relationnelle pour simuler une base de données orientée objet. Ce programme définit des correspondances entre les schémas de la base de données et les classes du programme applicatif. On pourrait le désigner par là « comme une couche d'abstraction entre le monde objet et monde relationnel ». Du fait de sa fonction, on retrouve ce type de programme dans un grand nombre de frameworks sous la forme de composant ORM qui a été soit développé, soit intégré depuis une solution externe.

## Multi base (3 points)

Votre ORM doit fonctionner avec différentes bases de données.

## Mapping simple (3 points)

Un mapping simple se caractérise par: 
- Un model représente une table
- Chaque propriété correspond à un champ
- Le typage doit être respecté
- La clé primaire est générée sur le champ ID, auto incrémenté, en type integer

## Mapping avec décorateur (3 points)

Les décorateurs vont permettent de:
- Redéfinir le nom d'un champ en base
- Redéfinir le nom d'une table en base
- Rendre un champ null ou not-null (obligatoire)
- Mettre une taille aux champs de type string
- Définir la clé primaire sur n'importe quel champ

## Requétage par défaut (5 points)

Votre ORM devra permette pour chaque model de:
- Lister toutes les données
- Trier toutes les données
- Récupérer un objet par rapport à une valeur de la clé primaire
- Insérer un objet
- Modifier un objet
- Supprimer un objet

## Critéria (3 points)

Pour les requêtes de sélection (SELECT), vous devez donné la possibilité au développeur (structure de votre choix et en fonction du langage) d'ajouter des critères de recherche.

## Gestion de transactions (3 points)

Votre ORM doit gérer les transactions sur toutes les requêtes. Si une transaction échoue, un rollback est fait automatiquement et une exception est levée.

# Fonctionnement

Le projet est a développé en individuel ou à 2 et sera noté en individuel.
La soutenance de projet aura lieu le Jeudi 21 décembre 2023.

# Rendu

Toute à la soutenance ou absence de rendu ou entrainera un 0 (ZERO) pour l'étudiant.

Le rendu s'effectu via un repos GIT ou SVN. L'adresse du rendu est envoyé par mail.
Le mail de rendu est vincent.leclerc@ynov.com
Les fichiers rendus doivent contenir
  - L'arborescence du projet, immédiatement exploitable après création des bases de données et exécution des migrations.
  - Un AUTHORS.TXT listant le prénom et nom de l'étudiant.
Le sujet du mail doit contenir votre section ainsi que votre nom.
Un code qui ne compile pas ou ne s'éxécute pas ne sera pas noté (conseil: faites des branches git)
