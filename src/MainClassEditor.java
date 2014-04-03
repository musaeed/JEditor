import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Gui.JEditor;


public class MainClassEditor {
	
	public static void main(String args[]){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new JEditor(args);
	}

}
