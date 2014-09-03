package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Components.CMenu;
import Components.CMenuItem;
import Components.RibbonMenu;
import Gui.JEditor;
import OptionDialogs.DeveloperOptions;
import OptionDialogs.UpdateInstructionsDialog;
import Utility.ImageLoader;

public class HelpMenu extends CMenu{

	private static final long serialVersionUID = 1L;
	public static CMenuItem help,contact,donate,updates;
	
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
		contact = new CMenuItem("Contact developer", "help the developer improve JEditor", 'C', null);
		donate = new CMenuItem("Donate", "donate to the developer, it keeps me motivated to work:)",  'D' ,null);
		updates = new CMenuItem("Update JEditor", "check if some updates are available", 'J', null);
	}

	public void addToMenu(){
		add(help);
		add(contact);
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
		
		contact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new DeveloperOptions().show();
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
