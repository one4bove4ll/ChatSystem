package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.IsConnected;

import controller.*;

public class LoginInterface extends JFrame implements Observer, KeyListener{
	
	private JTextArea loginArea;
	private JButton bConnect;
	private String myTitle;
	
	private ConnectionRequest coRequest;
	
	public LoginInterface(String title, ConnectionRequest coRequest){
		this.myTitle = title;
		this.coRequest = coRequest;
		initGraphicComponents();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initGraphicComponents(){
		
		/******************** Create the components ********************/
		
		loginArea = new JTextArea(2,20); // voir si relevant de mettre les dims
		bConnect = new JButton("Connexion");
		
		// Ajout de l'actionlistener au bouton de connexion (appelle la methode doIt
		// d'un objet de la classe ConnectionRequest qui appartient au controleur)
		bConnect.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
            	//System.out.println("ActionListener du bouton de connexion!");
            	try{
            		coRequest.doIt(loginArea.getText());
            	}catch(Exception e){
            		JOptionPane.showMessageDialog(null,e.getMessage());
            	}
            }
		});
		
		/******************** Edit the components ********************/
		
		// set le layout du gui et les contraintes associées
		this.setLayout(new GridBagLayout());
		GridBagConstraints gBConstraints = new GridBagConstraints();
		
		// contraintes pour loginArea
		gBConstraints.fill = GridBagConstraints.BOTH; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 1; //request any extra vertical space
		gBConstraints.gridx = 0;
		gBConstraints.gridy = 0;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 1; // 1 line height
		gBConstraints.insets = new Insets(10,10,10,10); // pour l'espacement autour composant (<top>,<left>,<bottom>,<right>)
		loginArea.setEditable(true);
		loginArea.addKeyListener(this);
		this.add(loginArea, gBConstraints);
		
		// contraintes pour bConnect
		gBConstraints.fill = GridBagConstraints.BOTH; //////////// ??????????????
		gBConstraints.weightx = 1; //request any extra horizontal space
		gBConstraints.weighty = 1; //request any extra vertical space
		gBConstraints.gridx = 0;
		gBConstraints.gridy = 1;
		gBConstraints.gridwidth = 1; // 1 column width
		gBConstraints.gridheight = 1; // 1 line height
		gBConstraints.insets = new Insets(0,10,10,10); // pour l'espacement a gauche en bas et a droite du composant (<top>,<left>,<bottom>,<right>)
		this.add(bConnect, gBConstraints);
		this.bConnect.setBackground(new Color(229,255,204,255));
		
		
		this.pack();
		// set JFrame to visible
		this.setVisible(true);
		this.setTitle(myTitle);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof IsConnected){
			//System.out.println("methode update de l'objet de classe LoginInterface!");
			//System.out.println("		----> l'argument \"arg1\" de la fonction (cense representer la valeur de \"isConnected\") a pour valeur: "+(boolean)arg1);
			if((boolean)arg1 == true){
				this.setVisible(false);
			} else {
				this.setVisible(true);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			try {
				coRequest.doIt(loginArea.getText());
			} catch (Exception e1) {
        		JOptionPane.showMessageDialog(null,e1.getMessage());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// not used...
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not used...
	}
	
	
	/* Singleton?? */
}


