WHAT:

ChatSystem1.1 - INSA Toulouse projet étudiant

Cet humble logiciel a été réalisé dans le cadre d'un projet de développement orienté objet à l'INSA de Toulouse.
Il s'agit d'un logiciel de "chat" en peer-to-peer, permettant à ses utilisateurs de communiquer par envoi de messages
texte sur un réseau local.

----------------------------------------------------------------------------------------------------------------------

GETTING STARTED:

Pré-requis :

- Java 1.8.0_121 installé
- Eclipse IDE
- une connexion à un réseau local (filaire ou sans fil)

Installation & Execution:

- télécharger le dossier "ChatSystem" contenant les sources à l'adresse :
	https://github.com/one4bove4ll/ChatSystem/tree/master/ChatSystem
- compiler et executer sur Eclipse IDE

----------------------------------------------------------------------------------------------------------------------

FEATURES:

Implementés :

- Envoi d'un message texte à un utilisateur connecté
- Connexion à l'interface de "chat" via une interface de login en entrant un pseudonyme
- Un utilisateur connecté visualise la liste des utilisateurs connectés sur l'interface de "chat". Cet liste reste
  à jour lors de la connexion/déconnexion d'un autre utilisateur
- Un utilisateur est donc informé lorsqu'un autre utilisateur se connecte/déconnecte
- L'interface de "chat" offre la possibilité de sélectionner le destinataire de notre message en cliquant sur son
  pseudonyme dans la liste des utilisateurs connectés
- Une boîte de dialogue apparaît pour informer l'utilisateur de l'impossibilité d'envoyer un message à un
  utilisateur déconnecté, si une telle tentative est effectuée par l'utilisateur local
- Lorsque le logiciel reçoit un message à destination de l'utilisateur local, un nombre est incrémenté dans une
  zone dans le coin inférieur gauche de l'interface de "chat". L'utilisateur local peut cliquer sur cette zone
  afin de faire savoir explicitement au logiciel qu'il a compris que de nouveaux messages sont arrivés et remettre
  à zéro ce nombre
- les messages liés à une conversation entre l'utilisateur local et un destinataire donné sont mémorisés dans un
  fichier texte. Lorsque l'utilisateur local clique sur le destinataire correspondant dans le liste des
  utilisateurs connectés, la conversation est affichée
- l'envoi d'un message à un destinataire donné met à jour le fichier mémorisant la conversation correspondante

À faire :

- L'utilisateur ne peut, en l'état actuel, qu'envoyer des messages texte. Il serait possible d'implémenter l'envoi
  de fichiers de différents types (images, documents pdf, programmes, etc.). Cette fonctionnalité est toutefois
  optionnelle
- L'utilisateur ne peut, en l'état actuel, qu'envoyer des messages à un destinataire à la fois. Il serait possible
  d'implémenter l'envoi de messages à plusieurs destinataires en même temps. Cette fonctionnalité est toutefois
  optionnelle

----------------------------------------------------------------------------------------------------------------------

TEST RESULTS:

--> test Perroquet (auteur : B.Foucher & P. Charayron) :
Une instance du chatsystem specialisee dans la reponse automatique a un message recu est lancee sur un ordinateur.
Sur un autre ordinateur (connecte au meme reseau local que le precedent) nous lancons un instance classique du
chatsystem et nous envoyons un message au perroquet. Nous attendons la reponse du perroquet.
Notre logiciel a bien passe le test. La reponse attendu du perroquet est bien recue.

--> test "testAnnounce" (auteur : B.Foucher & P. Charayron) :
Un test JUnit vérifiant l'ajout d'un utilisateur dans la liste des utilisateurs connectes lorsque l'on recoit une
annonce de sa part et le retrait d'un utilisateur lorsque notre watchdog timer arrive a son terme et que
l'utilisateur en question n'est plus connecte
Notre logiciel a bien passe le test. L'etat de connexion d'un utilisateur est bien mis a jour en fonction des
annonces recues de sa part.

--> test "testNumberNewMessages" (auteur : B.Foucher & P. Charayron) :
Un test JUnit vérifiant le bon fonctionnement des methodes incrementNumber() et resetNumber() de la classe
NumberNewMessages.
Notre logiciel a bien passe le test.

--> test "testUserList" (auteur : B.Foucher & P. Charayron) :
Un test JUnit verifiant le bon fonctionnement des methodes addCouple() et removeCouple() de la classe UserList.
Notre logiciel a bien passe le test. Un appel a addCouple() ajoute bien l'utilisateur specifie à la liste et un appel
a removeCouple() retire bien l'utilisateur specifie de la liste.

----------------------------------------------------------------------------------------------------------------------

BUILT WITH:

Eclipse IDE

----------------------------------------------------------------------------------------------------------------------

AUTHORS:

Bérenger Foucher
Paul Charayron
