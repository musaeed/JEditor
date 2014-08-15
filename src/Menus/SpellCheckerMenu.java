package Menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import Components.CCheckBoxMenuItem;
import Components.CMenu;
import Components.CTabbedPane;
import Utility.ImageLoader;
import Utility.Notifications;
import core.TextPanel;

public class SpellCheckerMenu extends CMenu{

	private static final long serialVersionUID = 1L;

	private CCheckBoxMenuItem activate;
	private CMenu languages;
	private CCheckBoxMenuItem eng,fre,spanish,german,polish,italian,russian,dutch;

	public SpellCheckerMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				addActions();
				addToMenu();
				addIcons();
			}
		}).start();

	}

	public void init(){
		activate = new CCheckBoxMenuItem("Activate spell checker", "activate the spelling checker");

		languages = new CMenu("Languages", 'L');

		eng = new CCheckBoxMenuItem("English", "english");
		fre = new CCheckBoxMenuItem("French", "french");
		spanish = new CCheckBoxMenuItem("Spanish", "spanish");
		german = new CCheckBoxMenuItem("German", "german");
		polish = new CCheckBoxMenuItem("Polish", "polish");
		italian = new CCheckBoxMenuItem("Italian", "italian");
		russian = new CCheckBoxMenuItem("Russian", "russian");
		dutch = new CCheckBoxMenuItem("Dutch", "dutch");
	}

	public void addActions(){

		activate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(activate.isSelected()){
					Notifications.showNotification("Spelling checker activated");
					for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++) {
						((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).registerSpellChecker();
					}
					
					for(int i = 0 ; i < languages.getItemCount() ; i++){
						((CCheckBoxMenuItem)languages.getItem(i)).setEnabled(true);
					}
					eng.setSelected(true);
				}
				else{
					Notifications.showNotification("Spelling checker deactivated");
					for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++) {
						((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).unregisterSpellChecker();
					}
					
					for(int i = 0 ; i < languages.getItemCount() ; i++){
						((CCheckBoxMenuItem)languages.getItem(i)).setEnabled(false);
					}
				}

			}
		});


		eng.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				checkLanguageMenu(eng);
				CTabbedPane.getInstance().getPanel().registerEnglish();

			}
		});

		fre.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(fre);
				CTabbedPane.getInstance().getPanel().registerFrench();
			}
		});

		spanish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(spanish);
				CTabbedPane.getInstance().getPanel().registerSpanish();
			}
		});

		german.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(german);
				CTabbedPane.getInstance().getPanel().registerGerman();
			}
		});

		polish.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(polish);
				CTabbedPane.getInstance().getPanel().registerPolish();
			}
		});

		italian.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(italian);
				CTabbedPane.getInstance().getPanel().registerItalien();

			}
		});

		russian.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(russian);
				CTabbedPane.getInstance().getPanel().registerRussian();
			}
		});

		dutch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				checkLanguageMenu(dutch);
				CTabbedPane.getInstance().getPanel().registerDutch();
			}
		});

	}

	public void addToMenu(){
		add(activate);

		languages.add(eng);
		languages.add(fre);
		languages.add(spanish);
		languages.add(german);
		languages.add(polish);
		languages.add(italian);
		languages.add(russian);
		languages.add(dutch);

		add(languages);
		
		for(int i = 0 ; i < languages.getItemCount() ; i++){
			((CCheckBoxMenuItem)languages.getItem(i)).setEnabled(false);
		}
	}

	public void addIcons(){
		eng.setIcon(ImageLoader.loadImage("images_small/gb.png"));
		fre.setIcon(ImageLoader.loadImage("images_small/fr.png"));
		spanish.setIcon(ImageLoader.loadImage("images_small/es.png"));
		german.setIcon(ImageLoader.loadImage("images_small/de.png"));
		polish.setIcon(ImageLoader.loadImage("images_small/pl.png"));
		italian.setIcon(ImageLoader.loadImage("images_small/it.png"));
		russian.setIcon(ImageLoader.loadImage("images_small/ru.png"));
		dutch.setIcon(ImageLoader.loadImage("images_small/nl.png"));
	}

	public void checkLanguageMenu(JCheckBoxMenuItem item){
		
		if(eng.isSelected()){
			eng.setSelected(false); 
		}

		if(fre.isSelected()){
			fre.setSelected(false); 
		}
		if(german.isSelected()){
			german.setSelected(false); 
		}
		if(russian.isSelected()){
			russian.setSelected(false); 
		}
		if(italian.isSelected()){
			italian.setSelected(false); 
		}
		if(spanish.isSelected()){
			spanish.setSelected(false); 
		}
		if(polish.isSelected()){
			polish.setSelected(false); 
		}
		if(dutch.isSelected()){
			dutch.setSelected(false); 
		}

		item.setSelected(true);
	}
}
