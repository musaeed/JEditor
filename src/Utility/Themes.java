package Utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;

public class Themes {

	public static final int SYSTEM = 0;
	public static final int NIMBUS = 1;
	public static final int WEBLAF = 2;
	public static int CURRENT = -1;

	public static void setTheme(){

		File file = new File(System.getProperty("user.home")+"/.cache/JEditor/themes.jeditor");
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			Themes.CURRENT = Themes.SYSTEM;
			return;
		}

		try{
			switch(sc.nextInt()){
			case SYSTEM:
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				Themes.CURRENT = Themes.SYSTEM;
				break;
			case NIMBUS:
				for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
					if ("Nimbus".equals(info.getName())) {
						UIManager.setLookAndFeel(info.getClassName());
						break;
					}
				}
				Themes.CURRENT = Themes.NIMBUS;
				break;
			case WEBLAF:
				WebLookAndFeel.install();
				Themes.CURRENT = Themes.WEBLAF;
				break;
			}
		} catch(Exception e){
			e.printStackTrace();
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException
					| IllegalAccessException | UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			Themes.CURRENT = Themes.SYSTEM;
			sc.close();
			return;
		}

		sc.close();

	}
}
