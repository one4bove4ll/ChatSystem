WHAT:

ChatSystem1.1 - INSA Toulouse projet �tudiant

Cet humble logiciel a �t� r�alis� dans le cadre d'un projet de d�veloppement orient� objet � l'INSA de Toulouse.
Il s'agit d'un logiciel de "chat" en peer-to-peer, permettant � ses utilisateurs de communiquer par envoi de messages
texte sur un r�seau local.

----------------------------------------------------------------------------------------------------------------------

GETTING STARTED:

Pr�-requis :

- Java 1.8.0_121 install�
- Eclipse IDE
- une connexion � un r�seau local (filaire ou sans fil)

Installation & Execution:

- t�l�charger le dossier "ChatSystem" contenant les sources � l'adresse :
	https://github.com/one4bove4ll/ChatSystem/tree/master/ChatSystem
- compiler et executer sur Eclipse IDE

----------------------------------------------------------------------------------------------------------------------

FEATURES:

Implement�s :

- Envoi d'un message texte � un utilisateur connect�
- Connexion � l'interface de "chat" via une interface de login en entrant un pseudonyme
- Un utilisateur connect� visualise la liste des utilisateurs connect�s sur l'interface de "chat". Cet liste reste
  � jour lors de la connexion/d�connexion d'un autre utilisateur
- Un utilisateur est donc inform� lorsqu'un autre utilisateur se connecte/d�connecte
- L'interface de "chat" offre la possibilit� de s�lectionner le destinataire de notre message en cliquant sur son
  pseudonyme dans la liste des utilisateurs connect�s
- Une bo�te de dialogue appara�t pour informer l'utilisateur de l'impossibilit� d'envoyer un message � un
  utilisateur d�connect�, si une telle tentative est effectu�e par l'utilisateur local
- Lorsque le logiciel re�oit un message � destination de l'utilisateur local, un nombre est incr�ment� dans une
  zone dans le coin inf�rieur gauche de l'interface de "chat". L'utilisateur local peut cliquer sur cette zone
  afin de faire savoir explicitement au logiciel qu'il a compris que de nouveaux messages sont arriv�s et remettre
  � z�ro ce nombre
- les messages li�s � une conversation entre l'utilisateur local et un destinataire donn� sont m�moris�s dans un
  fichier texte. Lorsque l'utilisateur local clique sur le destinataire correspondant dans le liste des
  utilisateurs connect�s, la conversation est affich�e
- l'envoi d'un message � un destinataire donn� met � jour le fichier m�morisant la conversation correspondante

� faire :

- L'utilisateur ne peut, en l'�tat actuel, qu'envoyer des messages texte. Il serait possible d'impl�menter l'envoi
  de fichiers de diff�rents types (images, documents pdf, programmes, etc.). Cette fonctionnalit� est toutefois
  optionnelle
- L'utilisateur ne peut, en l'�tat actuel, qu'envoyer des messages � un destinataire � la fois. Il serait possible
  d'impl�menter l'envoi de messages � plusieurs destinataires en m�me temps. Cette fonctionnalit� est toutefois
  optionnelle

----------------------------------------------------------------------------------------------------------------------

TEST RESULTS:

--> test Perroquet (auteur : B.Foucher & P. Charayron) :
Une instance du chatsystem specialisee dans la reponse automatique a un message recu est lancee sur un ordinateur.
Sur un autre ordinateur (connecte au meme reseau local que le precedent) nous lancons un instance classique du
chatsystem et nous envoyons un message au perroquet. Nous attendons la reponse du perroquet.
Notre logiciel a bien passe le test. La reponse attendu du perroquet est bien recue.

--> test "testAnnounce" (auteur : B.Foucher & P. Charayron) :
Un test JUnit v�rifiant l'ajout d'un utilisateur dans la liste des utilisateurs connectes lorsque l'on recoit une
annonce de sa part et le retrait d'un utilisateur lorsque notre watchdog timer arrive a son terme et que
l'utilisateur en question n'est plus connecte
Notre logiciel a bien passe le test. L'etat de connexion d'un utilisateur est bien mis a jour en fonction des
annonces recues de sa part.

--> test "testNumberNewMessages" (auteur : B.Foucher & P. Charayron) :
Un test JUnit v�rifiant le bon fonctionnement des methodes incrementNumber() et resetNumber() de la classe
NumberNewMessages.
Notre logiciel a bien passe le test.

--> test "testUserList" (auteur : B.Foucher & P. Charayron) :
Un test JUnit verifiant le bon fonctionnement des methodes addCouple() et removeCouple() de la classe UserList.
Notre logiciel a bien passe le test. Un appel a addCouple() ajoute bien l'utilisateur specifie � la liste et un appel
a removeCouple() retire bien l'utilisateur specifie de la liste.

----------------------------------------------------------------------------------------------------------------------

BUILT WITH:

Eclipse IDE

----------------------------------------------------------------------------------------------------------------------

AUTHORS:

B�renger Foucher
Paul Charayron
