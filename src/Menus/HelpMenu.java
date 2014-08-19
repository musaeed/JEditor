package Menus;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import Components.RibbonMenu;
import Gui.JEditor;
import OptionDialogs.UpdateInstructionsDialog;
import Utility.ImageLoader;
import Utility.Notifications;

public class HelpMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	public static CMenuItem help,makeSuggestion,donate,updates;
	
	public HelpMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				addToMenu();
				addActions();
				addIcons();
			}
		}).start();

	}
	
	public void init(){
		help = new CMenuItem("Help", "show help dialog", 'H', KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0));
		makeSuggestion = new CMenuItem("Make a suggestion", "help the developer improve JEditor", 'M', null);
		donate = new CMenuItem("Donate", "donate to the developer, it keeps me motivated to work:)",  'D' ,null);
		updates = new CMenuItem("Update JEditor", "check if some updates are available", 'J', null);
	}

	public void addToMenu(){
		add(help);
		add(makeSuggestion);
		add(donate);
		add(updates);
	}
	
	public void addActions(){
		help.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RibbonMenu.help.doClick();
			}
		});
		
		makeSuggestion.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Notifications.showNotification("Opening the system mail client ...");
				
				String url = "mailto:muhammad.omar555@gmail.com?subject=JEditor suggestion&body=Please write a small summary of your suggestion followed by the full description. Don't forget to add your name in the end ;)";
			
				try {
					Desktop.getDesktop().browse(new URI(url.replace(" ", "%20")));
				} catch (IOException | URISyntaxException e) {
					e.printStackTrace();
				}
			}
		});
		
		donate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(JEditor.frame, "<html>You can donate me using the paypal service. My paypal account is: <font color=blue><b>muhammad.omar555@gmail.com</b></font>.<br>I am looking forward to your donations." +
						"<br><br>Yours sincerely,<br>Muhammad Omer Saeed</html>", "Donation", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		updates.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateInstructionsDialog.getInstance().show();
			}
		});
		
	}
	
	public void addIcons(){
		updates.setIcon(ImageLoader.loadImage("images_small/update.png"));
	}

}
