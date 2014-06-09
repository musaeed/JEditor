package Menus;

import javax.swing.JMenuBar;


public class FrameMenu extends JMenuBar{

	private static final long serialVersionUID = -7835759820105202925L;
	
	public FrameMenu(){
		init();
		addToMenu();
	}
	
	public void init(){
	}
	
	public void addToMenu(){
		add(new FileMenu("File", 'F'));
		add(new EditMenu("Edit", 'E'));
		add(new FormatMenu("Format", 'O'));
		add(new ToolMenu("Tools", 'T'));
		add(new HelpMenu("Help", 'H'));
	}

}
