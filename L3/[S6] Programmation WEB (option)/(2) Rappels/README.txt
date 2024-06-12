-------------------------------------------------------------------------------
Le premier TP au moins (PHP/SQL) suppose l'installation d'un serveur privé sur
votre machine. Ce document vous indique pas à pas comment effectuer cette
installation sur une machine tournant sous Ubuntu 18.04 (ou une version plus
récente).

Si vous disposez que de Windows, de nombreux sites vous indiqueront comment
installer Ubuntu (ou tout autre Linux) sur VirtualBox, une machine virtuelle
tournant sous Windows : la solution est simple et pleinement fonctionnelle sur
une machine suffisamment récente. Nous vous déconseillons de tenter d'installer
en dual-boot en parallèle de Windows : la manière la plus simple d'effectuer
cette installation est risquée, car elle peut interdire la restauration du
système sur certaines machines. Elle est un peu plus compliquée si l'on
souhaite préserver le contenu du début du disque dur (le MBR).

Après l'installation, le répertoire du site se trouvera en /var/www/private.
Il sera librement utilisable pour résoudre les exercices du cours nécessitant
un serveur ou une base de données.
-------------------------------------------------------------------------------
(1) Installation des serveurs apache et mysql
-------------------------------------------------------------------------------
 sudo apt install apache2 php libapache2-mod-php mysql-server php-mysql
 sudo apt install php-curl php-gd php-intl php-json php-mbstring php-xml php-zip

Le second groupe de packages est optionnel, mais conseillé par Unbuntu. Pour
vérifier que tout fonctionne :

(a) Lancez un navigateur
(b) Dans la barre d'adresse, entrez : localhost

Vous devriez voir la page d'accueil par défaut d'apache ("It works!").

(c) Dans le shell, entrez : sudo mysql

Ceci devrait lancer mysql en mode interactif. La commande de sortie est

 EXIT;

Par défaut, les deux serveurs seront lancés au démarrage de la machine.
Il est possible de faire pour l'un ou pour l'autre le choix inverse
(disable) et de rétablir ce choix (enable) à l'aide des commandes
suivantes (remplacez les points par apache2 ou par mysql).

 sudo systemctl disable ...
 sudo systemctl enable  ...

Chaque serveur peut être démarré, relancé ou stoppé (remplacez les points
par apache2 ou par mysql) :

 sudo service ... start
 sudo service ... restart
 sudo service ... stop
-------------------------------------------------------------------------------
(2) Configuration d'un serveur privé
-------------------------------------------------------------------------------
(a) Dans le shell, entrez les commandes suivantes :

 sudo mkdir /var/www/private
 sudo chown $USER:www-data /var/www/private
 chmod 750 /var/www/private

Le répertoire /var/www/private sera la racine de votre site privé.
Il vous est accessible sans avoir à vous servir de sudo. Ajoutez-y
un fichier index.html de contenu quelconque - par exemple une copie
de /var/www/html/index.html (la page d'accueil par défaut d'apache).

(b) Si vous n'avez pas installé emacs, faites-le maintenant :

 sudo apt install emacs

Toujours dans le shell, entrez la commande suivante :

 sudo emacs /etc/apache2/sites-available/private.conf

Recopiez dans le fichier ouvert le texte suivant :

 <VirtualHost *:80>
         ServerName private.localhost
         DocumentRoot "/var/www/private"
         <Directory "/var/www/private">
                 Options +FollowSymLinks +Indexes
                 AllowOverride all
                 Require ip ::1 127.0.0.1 192.168
         </Directory>
         ErrorLog /var/log/apache2/error.private.log
         CustomLog /var/log/apache2/access.private.log combined
 </VirtualHost>

Sauvegardez le fichier, et quittez emacs.

(c) Entrez enfin les commandes suivantes

 sudo a2ensite private
 sudo systemctl restart apache2

(d) Testez votre site en entrant l'adresse private.localhost dans un
    navigateur.

Si tout s'est bien passé, vous devriez voir s'afficher la page
/var/www/private/index.html
-------------------------------------------------------------------------------
(3) Accès à mysql via PHP
-------------------------------------------------------------------------------
Pour qu'un document PHP puisse se connecter au serveur, il faut créer un
nouvel utilisateur mysql. Votre serveur étant totalement privé, il est
peu risqué qu'il ait un mot de passe vide.

(a) Lancez la commande suivante :

 sudo mysql

 Entrez ensuite les commandes suivantes, en remplaçant utilisateur par votre
 nom :

  CREATE USER 'utilisateur'@'localhost' IDENTIFIED BY '';
  GRANT ALL PRIVILEGES ON * . * TO 'utilisateur'@'localhost';
  FLUSH PRIVILEGES;
  EXIT;

La toute dernière étape est de vérifier que vous pouvez vous connecter au
serveur depuis un fichier PHP.

(b) Dans le répertoire /var/www/private, créez un fichier sql_test.php
    contenant les lignes suivantes :

 <!DOCTYPE html>
 <html>
     <head>
         <meta charset="UTF-8">
         <title>SQL Test</title>
     </head>
     <body>
     <?php
         try {
             $dbh = new PDO('mysql:host=localhost', 'utilisateur', '');
         } catch (PDOException $e) {
             print "Error!: " . $e->getMessage() . "<br/>";
             die();
         }
         echo "It Works!";
      ?> 
     </body>
 </html>

(c) Dans un navigateur, entrez dans la barre d'adresse

    private.localhost/sql_test.php

    Vous devriez voir s'afficher le message "It Works!".
-------------------------------------------------------------------------------
