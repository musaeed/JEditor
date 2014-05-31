package Menus;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Components.CMenu;
import Components.CMenuItem;
import Gui.JEditor;

public class ProjectMenu extends CMenu{
	
	private static final long serialVersionUID = 1L;
	private CMenuItem newProject,load,close, save, saveAs;

	public ProjectMenu(String text, char Mnmonic) {
		super(text, Mnmonic);
		init();
		addActions();
		addToMenu();
	}

	public void init(){
		newProject = new CMenuItem("Create new project", "start a new project", 'n', null);
		load = new CMenuItem("Load", "load a project", 'L', null);
		close = new CMenuItem("Close", "close the current project", 'C', null);
		save = new CMenuItem("Save project", "save the project", 'S', null);
		saveAs = new CMenuItem("Save project as", "save the project with a new name", '0', null);
	}
	
	public void addToMenu(){
		add(newProject);
		add(load);
		add(close);
		add(save);
		add(saveAs);
	}
	
	public void addActions(){
		
		newProject.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		load.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				FileDialog dialog = new FileDialog(JEditor.frame, "Load project", FileDialog.LOAD);
				dialog.setVisible(true);
				
			}
		});
		
		close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		saveAs.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				FileDialog dialog = new FileDialog(JEditor.frame, "Save project as", FileDialog.SAVE);
				dialog.setVisible(true);
			}
		});
	}
}
