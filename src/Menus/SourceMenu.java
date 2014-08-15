package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import Utility.HtmlRenderer;
import Utility.ImageLoader;
import Utility.Notifications;

import Components.CMenu;
import Components.CMenuItem;
import Gui.JEditor;
import MenuEvents.SourceMenuEvent;

public class SourceMenu extends CMenu{
	
	private static final long serialVersionUID = 1L;
	public static CMenuItem command,terminal,browser,renderHtml;

	public SourceMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				addActions();
				addToMenu();
				addMenuListener(new SourceMenuEvent());
				addIcons();
			}
		}).start();

	}
	
	public void init(){
		command = new CMenuItem("Exec command", "execute a command", 'E', null);
		terminal = new CMenuItem("Open terminal", "open a new terminal", 'O', KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK + InputEvent.CTRL_DOWN_MASK));
		browser = new CMenuItem("Open browser", "open the system browser", 'B', KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
		renderHtml = new CMenuItem("Render Html", "render the text as html", 'R', KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK + InputEvent.SHIFT_DOWN_MASK));
	}
	
	public void addActions(){
		
		command.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String text = JOptionPane.showInputDialog(JEditor.frame, "Enter command:", "Exec command", JOptionPane.PLAIN_MESSAGE);
				
				if(text == null){
					return;
				}
				
				String[] command = text.split(" ");
				
				try {
					Runtime.getRuntime().exec(command);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(JEditor.frame, "An error occured while executing the command. Please check if the command is correct.", "Error executing", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		
		terminal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				Notifications.showNotification("Opening the system terminal ...");
				
				try {
					Runtime.getRuntime().exec("gnome-terminal");
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		
		browser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				Notifications.showNotification("Opening the system browser ...");
				
				try {
					Runtime.getRuntime().exec("sensible-browser");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		renderHtml.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				HtmlRenderer r = new HtmlRenderer();
				
				if(r.load())
					r.show();
			}
		});
	}
	
	public void addToMenu(){
		add(command);
		add(terminal);
		add(browser);
		add(renderHtml);
	}
	
	public void addIcons(){
		terminal.setIcon(ImageLoader.loadImage("images_small/terminal.png"));
		renderHtml.setIcon(ImageLoader.loadImage("images_small/html.png"));
		browser.setIcon(ImageLoader.loadImage("images_small/web.png"));
	}


}
