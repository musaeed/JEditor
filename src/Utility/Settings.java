package Utility;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Gui.JEditor;


public class Settings {
	
	public Settings(){
		
	}
	
	public static void saveSettings(){
		
		File file = new File(System.getProperty("user.home")+"/.cache/JEditor/settings.jeditor");
		PrintWriter o = null;
		
		try {
			
			o = new PrintWriter(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(JEditor.frame, "Unable to save the settings profile.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		o.println("Recent files");
		for(String s : RecentFiles.getInstance().list){
			o.println(s);
		}
		o.println("end");
		
		o.flush();
		o.close();
	}
	
	public static void readSettings(){
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				File file = new File(System.getProperty("user.home")+"/.cache/JEditor/settings.jeditor");
				Scanner sc = null;
				
				try {
					sc = new Scanner(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					setDefautltSettings();
					return;
				}
				
				while(sc.hasNext()){
					if(sc.nextLine().equals("Recent files")){
						setRecentFiles(sc);
					}
					
				}
				
				sc.close();
			}
		});
		
		thread.start();
	}
	
	public static void setRecentFiles(Scanner sc){
		String s = sc.nextLine();
		
		while(!s.equals("end")){
			RecentFiles.getInstance().list.add(s);
			s = sc.nextLine();
		}
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RecentFiles.getInstance().buildRecentFilesMenu();
			}
		});

	}
	
	public static void setDefautltSettings(){
		
	}

}
