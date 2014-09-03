package OptionDialogs;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.KeyStroke;

import Utility.Notifications;

import Components.ColoredButton;
import Gui.JEditor;

public class DeveloperOptions {
	
	private JDialog dialog;
	private ColoredButton email, linkedin, stackoverflow, codeacademy, github;
	
	
	public DeveloperOptions(){
		init();
		addButtons();
		addActions();
	}
	
	public void init(){
		dialog = new JDialog();
		dialog.setTitle("Select an option");
		dialog.setModal(true);
		dialog.setSize(new Dimension(500,500));
		dialog.setLayout(new GridLayout(5, 1));
		dialog.setLocationRelativeTo(JEditor.frame);
		
	}
	
	public void addButtons(){
		
		email = ColoredButton.GetRandomButton("Email", "send an email to the developer");
		linkedin = ColoredButton.GetRandomButton("LinkedIn", "visit the LinkedIn profile of the developer");
		stackoverflow = ColoredButton.GetRandomButton("Stack exchange", "visit the stack exchange profile of the developer");
		codeacademy = ColoredButton.GetRandomButton("Code academy", "visit the code academy profile of the developer");
		github = ColoredButton.GetRandomButton("Github", "visit the github profile of the developer");
		
		dialog.add(email);
		dialog.add(github);
		dialog.add(linkedin);
		dialog.add(stackoverflow);
		dialog.add(codeacademy);
		
		email.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), 1);
		email.getActionMap().put(1, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
			}
		});
	}
	
	public void addActions(){
		
		email.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Notifications.showNotification("Opening the system mail client ...");
				
				String url = "mailto:muhammad.omar555@gmail.com?subject=JEditor suggestion&body=" +
						"Please write a small summary of your suggestion followed by the full descr" +
						"iption. Don't forget to add your name in the end ;)";
			
				try {
					Desktop.getDesktop().browse(new URI(url.replace(" ", "%20")));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
				
				dialog.dispose();
			}
		});
		
		linkedin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Notifications.showNotification("Opening the LinkedIn profile ...");
				String url = "http://de.linkedin.com/pub/muhammad-omer-saeed/84/871/71b/";
				
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dialog.dispose();
				
			}
		});
		
		github.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Notifications.showNotification("Opening the Github profile ...");
				String url = "http://github.com/musaeed";
				
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dialog.dispose();
			}
		});
		
		stackoverflow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Notifications.showNotification("Opening the stackexchange profile ...");
				String url ="http://stackexchange.com/users/2874011/muhammad-omer?tab=accounts";
				
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dialog.dispose();
			}
		});
		
		codeacademy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Notifications.showNotification("Opening the codeacademy profile ...");
				String url = "http://www.codecademy.com/musaeed";
				
				try {
					Desktop.getDesktop().browse(new URI(url));
				} catch (IOException | URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dialog.dispose();
			}
		});
		
	}
	
	public void show(){
		dialog.setVisible(true);
	}

}
