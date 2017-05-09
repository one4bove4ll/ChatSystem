package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.ConversationFile;
import model.IsConnected;
import model.NotifyAddOrRemoveUser;
import model.NumberNewMessages;
import model.SelectedInterlocutor;
import model.UserList;
import controller.DisconnectionRequest;
import controller.ResetNbNotifRequest;
import controller.SelectInterlocutorRequest;
import controller.SendMessageRequest;

public class ChatInterface extends JFrame implements Observer{
	
	private JList<String> friendsList; // zone pour la liste d'amis
	private DefaultListModel<String> friendsListModel;
	private JTextArea chatArea; // affichage des messages echanges
	private JTextArea messageArea; // zone d'ecriture d'un message pour envoi
	private JTextArea notifMessageArea; // zone affichant l'arrivee de nouveaux messages
	private JScrollPane chatScroll;
	private JScrollPane messageScroll;
	private JButton bSendMessage;
	private JButton bDisconnect;
	private String myTitle; // titre de l'interface graphique
	
	private DisconnectionRequest discoRequest;
	private SendMessageRequest sendMsgRequest;
	private SelectInterlocutorRequest selectInterlocutorRequest;
	private ResetNbNotifRequest resetNotifsRequest;
	
	public ChatInterface(String title, DisconnectionRequest discoRequest, SendMessageRequest sendMsgRequest,
			SelectInterlocutorRequest selectInterlocutorRequest, ResetNbNotifRequest resetNotifsRequest){
		this.myTitle = title;
		this.discoRequest = discoRequest;
		this.sendMsgRequest = sendMsgRequest;
		this.selectInterlocutorRequest = selectInterlocutorRequest;
		this.resetNotifsRequest = resetNotifsRequest;
		initGraphicComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGraphicComponents(){
		
		/*************************************************************
		 * Create the components
		 *************************************************************/
		
		// Creation d'une JList de nom de User connectes
		friendsListModel = new DefaultListModel<String>();
		friendsList = new JList<String>(friendsListModel);
		// Cliquer sur un element de la JList modifie le modele en changeant la conversation selectionnee
		friendsList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO verifier que on recupere bien le nom de la conv selectionnee dans la ligne ci-dessous
				selectInterlocutorRequest.doIt(friendsList.getSelectedValue());
			}
		});
		
		// Zones de chat (affichage d'une conversation) et d'ecriture d'un message
		chatArea = new JTextArea(8,50); // voir si relevant de mettre les dims
		this.chatArea.setEditable(false);
		messageArea = new JTextArea(2,50); // voir si relevant de mettre les dims
		this.messageArea.setEditable(true);
		notifMessageArea = new JTextArea(2, 20); // voir si relevant de mettre les dims
		notifMessageArea.setText("0 new message(s)\n(click here to reset)");
		notifMessageArea.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				resetNotifsRequest.doIt();
			}
			@Override
			public void mouseEntered(MouseEvent e) {/* on ne fait rien */}
			@Override
			public void mouseExited(MouseEvent e) {/* on ne fait rien */}
			@Override
			public void mousePressed(MouseEvent e) {/* on ne fait rien */}
			@Override
			public void mouseReleased(MouseEvent e) {/* on ne fait rien */}
		});
		
		messageScroll = new JScrollPane(messageArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		chatScroll = new JScrollPane(chatArea,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		// Bouton d'envoi d'un message
		bSendMessage = new JButton("Envoyer message");
		bSendMessage.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	try{
                sendMsgRequest.doIt(messageArea.getText());
                messageArea.setText("");
            	}catch (Exception e){
            		JOptionPane.showMessageDialog(null,e.getMessage());
            	}
            }
		});
		
		// Bouton de deconnexion
		bDisconnect = new JButton("Deconnexion");
		bDisconnect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	System.out.println("ActionListener du bouton de deconnexion!");
                //try {
				//	discoRequest.doIt();
				//} catch (InterruptedException e) {
				//	e.printStackTrace();
				//}
            	System.exit(0);
            }
		});
		
		
		/*************************************************************
		 * Edit the components
		 *************************************************************/
		
		// Utilisation d'un GridBagLayout pour l'IHM
		this.setLayout(new GridBagLayout());
		// Creation d'un objet pour associer des contraintes aux objets que l'on va successivement ajouter � l'interface
		GridBagConstraints gBConstraints = new GridBagConstraints();
		
		// Contraintes pour la JList
		gBConstraints.fill = GridBagConstraints.BOTH; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 1; //request any extra vertical space
		gBConstraints.gridx = 0;
		gBConstraints.gridy = 0;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 2; // 2 lines height
		gBConstraints.insets = new Insets(5,5,5,5); // pour l'espacement a droite du composant (<top>,<left>,<bottom>,<right>)
		this.add(friendsList, gBConstraints);
		this.friendsList.setBackground(new Color(229,255,204,255));
		
		// Contraintes pour notifMessageArea
		gBConstraints.fill = GridBagConstraints.BOTH; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 1; //request any extra vertical space
		gBConstraints.gridx = 0;
		gBConstraints.gridy = 2;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 1; // 1 line height
		gBConstraints.insets = new Insets(0,5,5,5); // pour l'espacement a droite du composant (<top>,<left>,<bottom>,<right>)
		this.add(notifMessageArea, gBConstraints);
		this.notifMessageArea.setEditable(false);
		this.notifMessageArea.setBackground(Color.WHITE);
		
		// Contraintes pour chatArea
		gBConstraints.fill = GridBagConstraints.BOTH; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 1; //request any extra vertical space
		gBConstraints.gridx = 1;
		gBConstraints.gridy = 0;
		gBConstraints.gridwidth = 3; // 2 columns width
		gBConstraints.gridheight = 2; // 2 lines height
		gBConstraints.insets = new Insets(5,0,5,5); // pour l'espacement a haut,droite et bas du composant (<top>,<left>,<bottom>,<right>)
		this.add(chatScroll, gBConstraints);
		this.chatScroll.setBackground(Color.WHITE);
		
		// Contraintes pour messageArea
		gBConstraints.fill = GridBagConstraints.HORIZONTAL; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 0;
		gBConstraints.gridx = 1;
		gBConstraints.gridy = 2;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 1; // 1 line height
		gBConstraints.insets = new Insets(0,0,5,5); // pour l'espacement a droite et en bas du composant (<top>,<left>,<bottom>,<right>)
		this.add(messageScroll, gBConstraints);
		this.messageScroll.setBackground(Color.WHITE);
		
		// Contraintes pour bSendMessage
		gBConstraints.fill = GridBagConstraints.HORIZONTAL; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 0;
		gBConstraints.gridx = 2;
		gBConstraints.gridy = 2;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 1; // 1 line height
		gBConstraints.insets = new Insets(0,0,5,5); // pour l'espacement a droite et en bas du composant (<top>,<left>,<bottom>,<right>)
		this.add(bSendMessage, gBConstraints);
		
		// Contraintes pour bDisconnect
		gBConstraints.fill = GridBagConstraints.HORIZONTAL; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 0;
		gBConstraints.gridx = 3;
		gBConstraints.gridy = 2;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 1; // 1 line height
		gBConstraints.insets = new Insets(0,0,5,5); // pour l'espacement a droite et en bas du composant (<top>,<left>,<bottom>,<right>)
		this.add(bDisconnect, gBConstraints);
		
		this.pack();
		
		// A sa creation, l'IHM de chat n'est pas visible, c'est l'IHM de connexion qui apparait
		// On fait apparaitre l'IHM de chat et disparaitre celle de login quand on se connecte
		this.setVisible(false);
		this.setTitle(myTitle);
	}
	
	// L'IHM de chat observe un certain nombre d'elements du modele. La methode update specifie les actions a
	// mener lorsque un element observe notifie l'IHM de chat d'une modification a prendre en compte
	@Override
	public void update(Observable arg0, Object arg1) {
		// Si on clique sur le bouton de connexion de l'IHM de connexion, l'IHM de chat devient visible
		// Si on se deconnecte, l'IHM de chat disparait
		if (arg0 instanceof IsConnected){
			if((boolean)arg1 == true){
				this.setVisible(true);
			} else {
				this.setVisible(false);
			}
		// Si un utilisateur a ete ajoute a la liste des utilisateurs connectes, on l'ajoute dans la JList sur l'IHM de chat
		// Si un utilisateur se deconnecte (il sort alors de la liste des utilisateurs connectes), il disparait de la JList
		} else if (arg0 instanceof UserList) {
			if (((NotifyAddOrRemoveUser)arg1).getAddOrRemove() == 1){
				// 1 => il s'agit d'un ajout de user dans la UserList
				friendsListModel.addElement(((NotifyAddOrRemoveUser)arg1).getUser().getLogin().getName());
				//un utilisateur s'est connecte => on a cree un user et on l'a ajoute dans la UserList
				// on rend l'IHM de chat observatrice du fichier de conversation lie a cet utilisateur
				((NotifyAddOrRemoveUser)arg1).getUser().getConvFile().addObserver(this);
			} else if (((NotifyAddOrRemoveUser)arg1).getAddOrRemove() == 0){
				// 0 => il s'agit d'un retrait de user de la UserList
				friendsListModel.removeElement(((NotifyAddOrRemoveUser)arg1).getUser().getLogin().getName());
			}
		// Si l'on change de conversation (en cliquant sur un autre utilisateur de la JList), on met a jour la
		// conversation affichee dans la zone de chat
		} else if (arg0 instanceof SelectedInterlocutor){
			chatArea.setText((String)arg1);
		} else if (arg0 instanceof NumberNewMessages){
			notifMessageArea.setText((int)arg1+" new message(s)\n(click here to reset)");
		} else if (arg0 instanceof ConversationFile){
			chatArea.setText((String)arg1);
		}else {
			System.out.println("Error ! Notification with unknown observable source has been received by chatIHM !");
		}
	}
	
	/* Singleton?? */
	
}
