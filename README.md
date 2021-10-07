**Auteurs**: Robin Gaudin, Noémie Plancherel, Lev Pozniakoff, Axel Vallon

# SYM: Labo 1 - Introduction à Android 

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

Pour vérifier le format de l'email. Nous avons d'abord créer une classe ``Util`` dans laquelle nous avons créé une fonction ``isValidAddress``. Celle ci va vérifier que l'email tapé a bien le bon format en utilisant un regex. En l'occurence, 

```
"^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$"
```

Dans le cadre de l'affichage du *Toast*. Nous avons ajouté dans la classe ``Util`` une fonction ``displayInvalidAddressToast``. Celle-ci va recevoir:

- **activity**: L'activité dans laquelle afficher le toast
- **toastText**: Le texte à afficher

Ensuite les lignes suivantes créeront le toast et l'afficheront pendant la durée ``Toast.LENGTH_SHORT`` (environ 2 sec)

````java
Toast toast = Toast.makeText(activity, toastText, Toast.LENGTH_SHORT);
toast.show();
````

Le texte dans le cadre du toast de cette partie est défini dans le string.xml.

### 3.2. Vérification du couple e-mail / mot de passe

Pour la vérification du couple email mot de passe, une simple boucle va parcourir la liste afin de chercher l'adresse mail dans les couples. Si nous la trouvons, nous vérifions si le mot de passe entré correspond. 

En cas de non correspondance. Un pop-up d'erreur s'affichera grâce à la fonction privé ``generateCredentialAlert`` de la classe ``MainActivity``. Ceci passe par la création d'un ``AlertDialog`` via un builder. Celui-ci contiendra un texte défini dans le fichier string.xml cité précédemment. Celui-ci devra contenir un bouton ok pour le fermer donc nous utilisons la fonction ``setPositiveButton`` qui fermera la fenêtre de dialogue. La fonction va enfin créer la fenêtre et la fonction appelante (``onCreate``) va l'afficher.

## 4. Passage à une autre activité

### 4.1. Création et lancement de la nouvelle activité

Android studio fourni des outils qui simplifie la création d'activité.

![image-20211005152129926](README.assets/image-20211005152129926.png)

Pour se faire, un simple clique droit sur le dossier source puis: New -> Activity -> Empty Activity (dans notre cas). 

![image-20211005152527081](README.assets/image-20211005152527081.png) 

Ceci ouvre la fenêtre ci-dessus qui créera une activité et une vue. La première est ajoutée au dossier java, la deuxième dans le dossier layout des ressources. 

Pour l'instant nous laissons cette activité vide. 

Pour la lancer depuis la MainActivity (une fois la validation d'email effectuée):

````java
Intent switchActivityIntent = new Intent(this, PostCoActivity.class); 
startActivity(switchActivityIntent);
````

Les lignes ci-dessus crée une Intent pour la nouvelle activité et la lance depuis la MainActivity.

### 4.2. Passage de paramètres à la nouvelle activité

Entre les 2 lignes qui permettent de lancer une activité. Il faut ajouter un extra à l'Intent pour passer des données d'une activité à l'autre:

````java
Intent switchActivityIntent = new Intent(this, PostCoActivity.class);
switchActivityIntent.putExtra("account", emailInput);
startActivity(switchActivityIntent);
````

Ceci va ajouter un couple <account, email> qui sera accessible à la 2e activité avec:

````java
String email = extras.getString("account");
````

La donnée passée est accessible via la clé "account", ici codée en dur. Il serait intéressant d'envisager d'ajouter un fichier contenant les clés importantes de l'application afin d'éviter le hardcode.

### 4.3. Permissions simples

Sans indiquer à l'application qu'elle a besoin de permissions pour se connecter à internet, la connexion ne sera pas acceptée. Ainsi, il faut ajouter la ligne ``uses-persmission`` au début du AndroidManifest.xml

````xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="ch.heigvd.iict.sym.labo1">

    <uses-permission android:name="android.permission.INTERNET" />
    .
    .
    .
````

Ceci fait nous pouvons utiliser l'``ImageDownloader`` pour insérer une image dans la nouvelle activité. 

Ainsi, il nous faut créer une nouvelle ``ImageView`` dans le layout de la nouvelle activité pour pourvoir la modifier et y insérer l'image qu'on télécharge. Ceci avec les lignes suivantes:

````java
ImageView connected_image = (ImageView) findViewById(R.id.connected_image);
new ImageDownloader(connected_image, "https://thispersondoesnotexist.com/image").show();
    
````

## 5. Navigation entre les activités

### 5.1. TODO Création et lancement de la nouvelle activité

Pour créer la nouvelle activité nous avons simplement cloné l'activité principale afin d'avoir les éléments dont nous avons besoin, à savoir:

- champ d'entrée d'email
- champ d'entrée de mot de passe
- bouton ok
- bouton annuler

> Vous êtes libre d’utiliser la méthode de votre choix dans ce laboratoire, toutefois vous discuterez dans votre rapport des avantages / inconvénients des deux méthodes.

Bien que la nouvelle méthode soit moins complexe quant il s'agit d'appeler une activité depuis une autre (ou un fragment), et bien qu'elle permette d'obtenir des permissions plus facilement aussi en ayant aussi un retour, nous avons choisi la méthode dépréciée. C'est à dire celle qui appelle ``startActivityForResult``

### 5.2. Affichage d’une image

> Dans quel(s) dossier(s) devons-nous ajouter cette image ? Veuillez décrire brièvement la logique derrière la gestion des ressources de type « image matricielle » sur Android. Quel intérêt voyez-vous donc à utiliser une image vectorielle ? Est-ce possible pour tout type d’images (logos, icônes, photos, etc.) ?![image-20211007132720283](../../../../../../../AppData/Roaming/Typora/draftsRecover/2021-10-6 README 141149.assets/image-20211007132720283.png)

Afin d'insérer une image, il faut se rendre dans la vue et drag drop une imageView dans la vue (1). Ceci va ouvrir une fenêtre qui va nous permettre de créer une image vectorielle en cliquant sur le + (2). Ceci va créer un fichier xml dans le dossier layout. Ceci fait, nous pouvons nous rendre sur le site https://fonts.google.com/icons afin de télécharger une icone qui nous convienne. Nous pouvons ensuite prendre le contenu du fichier xml disponible dans l'archive et le copier coller dans le fichier que nous venons de créer.

Nous aurions pu ajouter une image png mais ceci comporte le désavantage qu'elle ne va pas s'adapter à tous les écrans. Les images matricielle étant juste un ensemble de points défini dans un fichier, elles sont donc adaptable à la taille de l'écran des plus facilement. 

Ici l'image a été ajouté au dossier layout car c'est une image qui apparaît dans l'application. Si nous avions voulu ajouter une icône il aurait fallut importer dans l'application plusieurs format d'icone car dans ce cas ce ne sont sont pas des images vectorielles mais des png. N'étant donc pas adaptable à la taille de l'écran il faut importer plusieurs qualités différentes pour le logo. Ces images serait à importer dans le dossier mipmap

### 5.3. Factorisation du code

Les explications liées à cette partie comprennent les modifications liées à la partie [5.4 Cycle de vie](#5.4-cycle-de-vie).

Dans un premier temps nous avons factorisé le code de ``MainActivity`` et ``NewAccountActivity`` . Pour se faire nous avons ajouté plusieurs fonction à la classe ``Util`` afin de les appeler dans ces classe. 

Nous avons ajoutés les fonctions:

- ``displayInvalidAddressToast``: permet d'afficher le toast indiquant que le format de l'adresse mail est faux.
- ``checkCredentialValidity``: vérifie la validité du format des crédentials. C'est-à-dire, si les champs obligatoires ont été rempli, et que le format de l'adresse mail est correct. 
- ``initCancelButton``: les actions d'un bouton annulé, à savoir: vider les champs d'entrée et en retirer les erreur
- ``resetError``: enlève les erreurs des champs.

Les fonctions onCreate vont ensuite appeler ces différentes fonctions afin d'éviter la copie de code.

Pour le cycle de vie, nous avons créé une classe ``LifeCycleLogPrinter`` qui contient toutes les fonctions liées au cycle de vie avec ce format:

````java
@Override
protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Log.d(this.getClass().getSimpleName(), "onCreate");
}
````

Elle hérite de le AppCompatActivity.

Ensuite nous avons fait hériter toutes nos activités de celle-ci afin que les logs affiche le cycle de vie de toutes les activités sans pour autant copier coller le code.

### 5.4. Cycle de vie

> Décrivez brièvement à quelles occasions ces méthodes sont invoquées. Vous expliquerez aussi l’enchainement de ces appels lorsque l’on passe d’une activité à une autre.

``onCreate``: Nous nous rendons sur une activité qui n'est pas initialisé. Ceci arrive, soit parce que nous venons de lancer l'application et que l'activité n'a pas encore été appelée, soit parce que l'activité que nous appelons a été détruite et nécessite d'être à nouveau initialisée

``onStart``: L'application viens d'être créée et nécessite donc un démarrage, ou alors l'activité a été stoppée et est réappelé nécessitant donc un redémarrage.

``onResume``: reprend l'activité là où elle s'est pausée pour la dernière fois. Ceci peut arriver quand l'activité est démarrée pour la première fois ou parce que l'activité a été pausée et nécessite de reprendre.

``onPause``: L'activité est pausé et conserve son état actuelle afin de la relancer le plus rapidement possible.

``onStop``: L'activité est interrompu. Ceci peut arriver car elle était à l'état onPause mais que l'application a besoin des ressources qu'elle utilise pour sauvegarder son état. L'appareil va donc interrompre l'activité.

``onDestroy``: L'activité est détruite car achevée. 

Lorsque l'on passe d'une activité à une autre (par exemple entre ``MainActivity`` et ``NewAccountActivty``) l'application appelante va passer à l'état ``onPause``. La nouvelle activité passera par les états suivants:

``onCreate``->``onStart``->``onResume``

Une fois les 2 activités passeront par les états suivants:

Activité appelée: ``onPause``-> ----------------------------- ``onStop``->``onResume``

Activité appelante: --------------- ``onStart``->``onResume``

**Remarque:** Une rotation de l'écran nécessite de recharger l'activité. De ce fait, l'activité va passer par ces états dans cet ordre: 

onPause->onStop->onDestroy->onCreate->onStart->onResume
