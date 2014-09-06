package Utility;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Gui.JEditor;
import alarm.Alarm;
import alarm.AlarmTable;
import alarm.AlarmUtilities;
import alarm.Preferences;
import alarm.TableModel;


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
		
		o.println("Alarms");
		
		for(Alarm a : AlarmUtilities.getInstance().getList()){
			o.println(a.getTime());
			o.println(a.getAlertMessage());
			o.println(a.getPriority());
		}
		
		o.println("end");
		
		o.println("Alarm settings");
		o.println(Preferences.getInstance().getDef().isSelected());
		o.println(Preferences.getInstance().getLastPath());
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
					String s = sc.nextLine();
					
					if(s.equals("Recent files")){
						setRecentFiles(sc);
					}
					
					if(s.equals("Alarms")){
						readAlarms(sc);
					}
					
					if(s.equals("Alarm settings")){
						Preferences.getInstance().setPref(sc);
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
			
			if(!RecentFiles.getInstance().list.contains(s)){
				RecentFiles.getInstance().list.add(s);
			}

			s = sc.nextLine();
		}
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				RecentFiles.getInstance().buildRecentFilesMenu();
			}
		});

	}
	
	public static void readAlarms(Scanner sc){
		
		String s = sc.nextLine();
		ArrayList<Alarm> list = AlarmUtilities.getInstance().getList();
		
		while(!s.equals("end")){
			
			DateFormat df = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
		    Date date = null;
		    
			try {
				date = df.parse(s);
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
			
			if(date.before(Calendar.getInstance().getTime())){
				sc.nextLine();
				sc.nextLine();
				s = sc.nextLine();
				continue;
			}
			
			Alarm a = new Alarm(date, sc.nextLine(), sc.nextLine());
			list.add(a);
			
			s = sc.nextLine();
		}
		
		((TableModel)AlarmTable.getInstance().getRealTable().getModel()).updateAlarms();
		
	}
	
	public static void setDefautltSettings(){
		
	}

}
