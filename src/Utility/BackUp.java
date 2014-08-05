package Utility;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Components.CTabbedPane;
import Gui.JEditor;
import IOFactory.Reader;

public class BackUp {
	
	private static BackUp instance;
	private File file;
	private ArrayList<String> files;
	private Timer timer;
	
	public static BackUp getInstance(){
		
		if(instance == null){
			instance = new BackUp();
		}
		
		return instance;
	}

	private BackUp(){
		
		if(! new File(System.getProperty("user.home")+"/.cache/JEditor").exists()){
			
			new File(System.getProperty("user.home")+"/.cache/JEditor").mkdirs();
			
		}
		
		file = new File(System.getProperty("user.home")+"/.cache/JEditor/backup.jeditor");
		
		try {
			
			file.createNewFile();
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(JEditor.frame, "Unable to create a backup file.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			return;
		}
		
		files = new ArrayList<String>();
		startTimer();
	}
	
	public void addFile(String path){
		files.add(path);
	}
	
	public void removeFile(String path){
		files.remove(path);
	}
	
	public void startTimer(){
		timer = new Timer(30000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				PrintWriter writer = null;
				try {
					writer = new PrintWriter(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
					return;
				}
				
				for(String s : files){
					writer.println(s);
				}
				writer.flush();
				writer.close();
			}
		});
		
		timer.start();
	}
	
	public void removeAllFiles(){
		files.clear();
	}
	
	public void releaseBackup(){
		timer.stop();
		file.delete();
	}
	
	public static void checkAbnormalClose(){
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(new File(System.getProperty("user.home")+"/.cache/JEditor/backup.jeditor").exists()){
					
					if(new File(System.getProperty("user.home")+"/.cache/JEditor/backup.jeditor").length() == 0){
						return;
					}
					
					int result = JOptionPane.showConfirmDialog(JEditor.frame, "JEditor was closed unexpectedly on the last start up. Do you want to restore the previous session?", "Restore session", JOptionPane.YES_NO_OPTION);
					
					if(result == JOptionPane.NO_OPTION){
						return;
					}
					
					EventQueue.invokeLater(new Runnable() {
						
						@Override
						public void run() {
							
							Scanner sc = null;
							
							try {
								sc = new Scanner(new File(System.getProperty("user.home")+"/.cache/JEditor/backup.jeditor"));
							} catch (FileNotFoundException e) {
								e.printStackTrace();
								return;
							}
							
							while(sc.hasNext()){
								
								if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() != null){
									CTabbedPane.getInstance().addTab("Untitled");
								}
								
								Reader.loadFile(sc.nextLine());
								
							}
							
							sc.close();
						}
					});
				}
			}
		});
		
		thread.start();
		
	}
}
