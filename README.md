**Auteurs**: Robin Gaudin, Noémie Plancherel, Lev Pozniakoff, Axel Vallon

# SYM: Labo 1 - Introduction à Android 

Vous trouverez ci-dessous les réponses au questions:

## Table des matières

[TOC]

## 2. Premières constations

### 2.1: Langue de l'interface

> Quel est l’intérêt de regrouper les chaînes de caractères dans un fichier XML indépendant à côté des layouts. Vous expliquerez également comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la langue par défaut ou dans une langue additionnelle ?

Nous pouvons d'abord voir que les chaînes de caractères sont stockées séparément dans un fichier ``strings.xml``. L'intérêt de cette construction est que la traduction en est simplifiée. En effet, il suffit de faire un clic droit sur le fichier et d'aller sur ``Open translation editor`` pour faire une traduction des chaînes de caractères:

![image-20210922112123135](README.assets/image-20210922112123135.png)

Ceci va ouvrir l'éditeur sous cette forme:

![image-20210922112324318](README.assets/image-20210922112324318.png)

Dans un premier temps il faut cliquer sur le bouton 1, choisir une langue, et ensuite définir une traduction pour chacun des champs. dans la colonne nouvellement créée (2). Ceci va générer un deuxième fichier avec les traductions que vous aurez fourni. L'application va ensuite être automatiquement dans la langue du système si celle-ci est disponible dans cette langue. 

Dans le cas où l'application ou un champ n'est pas traduit, l'application ira chercher le champ dans la langue par défaut. Par exemple, ci-dessous nous n'avons pas traduit le champ email et c'est le seul qui est en anglais.

![image-20210922113040796](README.assets/image-20210922113040796.png)

### 2.2 Champs textuels de saisie

Pour désactiver la correction automatique, il faut activer l'option:

````xml
android:inputType="textNoSuggestions"
````

Dans les champs concernés (email et mot de passe)

Pour cacher les caractères dans le champ de mot de passe il faut activer:

````xml
android:inputType="textPassword|textNoSuggestions" 
````

Afin d'ajouter 2 options input type, il faut les concaténer avec un ``|`` entre les 2 options.

### 2.3 Mode paysage

Le mode paysage nécessite de créer une seconde vue pour l'activité principale. Pour se faire, il faut d'abords se rendre sur la vue portrait existante:

![image-20211005143837786](README.assets/image-20211005143837786.png)

Cliquer sur l'icône 1 puis sur Landscape. Ceci va créer une seconde vue paysage pour l'activité où il faudra replacer les layouts pour qu'ils correspondent à nos attentes de la vue paysage. Ceci fait, android gèrera automatiquement le passage à la vue paysage ou portrait en choisissant le bon xml.

## 3. Gestion des événements et mise à jour de l’interface utilisateur

### 3.1. Vérification du format de l’e-mail

Pour vérifier le format de l'email. Nous avons 
