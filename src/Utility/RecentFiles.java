package Utility;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import IOFactory.Reader;
import Menus.FileMenu;

public class RecentFiles {
	
	private static RecentFiles instance = null;
	public ArrayList<String> list;
	
	public static RecentFiles getInstance(){
		if(instance == null){
			instance = new RecentFiles();
		}
		return instance;
	}
	
	private RecentFiles(){
		init();
	}
	
	public void init(){
		list = new ArrayList<String>();
	}
	
	public void addToList(String path){

		if(list.contains(path)){
			list.remove(path);
			addToList(path);
			return;
		}
		
		if(list.size() > 10){
			list.remove(list.size()-1);
			addToList(path);
			return;
		}
		
		list.add(path);
		buildRecentFilesMenu();
	}
	
	public void buildRecentFilesMenu(){
		
		FileMenu.recentFiles.removeAll();
		
		JMenuItem arr[] = new JMenuItem[list.size()];
		
		ActionListener action = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Reader.loadFile(((JMenuItem)e.getSource()).getText());
			}
		};
		
		for(int i = 0 ; i < list.size() ; i++){
			arr[i] = new JMenuItem(list.get(list.size() - i - 1));
			arr[i].setToolTipText(arr[i].getText());
			arr[i].addActionListener(action);
			FileMenu.recentFiles.add(arr[i]);
		}
	}

}
