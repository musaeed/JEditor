package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Components.BottomPanel;
import Components.CProgressBar;
import Gui.JEditor;
import Menus.HelpMenu;

public class CheckUpdates extends Thread{

	@Override
	public void run() {
		BottomPanel.progressLabel.setText("Checking for updates");
		CProgressBar.getInstance().setValue(10);
		String link = "https://raw.githubusercontent.com/musaeed/JEditor/master/current_version.txt";
		String fileName = System.getProperty("user.home")+"/.cache/JEditor/temp.txt";
		try{
			URL url = new URL(link);
			URLConnection c = url.openConnection();
			c.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; .NET CLR 1.0.3705; .NET CLR 1.1.4322; .NET CLR 1.2.30703)");
			CProgressBar.getInstance().setValue(30);
			InputStream input;
			input = c.getInputStream();
			byte[] buffer = new byte[4096];
			int n = -1;
			CProgressBar.getInstance().setValue(40);
			OutputStream output = new FileOutputStream(new File(fileName));
			while ((n = input.read(buffer)) != -1) {
				CProgressBar.getInstance().setValue(CProgressBar.getInstance().getValue() + 5);
				if (n > 0) {
					output.write(buffer, 0, n);
				}
			}
			output.close();
		} catch(Exception e){
			e.printStackTrace();
			CProgressBar.getInstance().setValue(0);
			BottomPanel.progressLabel.setText("");
			return;
		}
		
		try {
			Scanner sc = new Scanner(new File(fileName));
			CProgressBar.getInstance().setValue(80);
			if(sc.nextDouble() > JEditor.AppVersion){
				int result = JOptionPane.showConfirmDialog(JEditor.frame, "An update to JEditor is available. Do you want to update now?", "Update available", JOptionPane.YES_NO_OPTION);
				
				if(result == JOptionPane.NO_OPTION){
					sc.close();
					new File(fileName).delete();
					CProgressBar.getInstance().setValue(80);
					BottomPanel.progressLabel.setText("");
					return;
				}
				
				HelpMenu.updates.doClick();
			}
			CProgressBar.getInstance().setValue(90);
			sc.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		CProgressBar.getInstance().setValue(100);
		new File(fileName).delete();
		BottomPanel.progressLabel.setText("");
		CProgressBar.getInstance().setValue(0);
	}

}
