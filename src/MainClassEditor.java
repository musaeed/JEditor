import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Gui.JEditor;
import core.StartService;


public class MainClassEditor {
	
	public static void main(final String args[]){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new StartService(args);
		
		EventQueue.invokeLater(new Runnable()
		  {
		       public void run()
		       {
		   		new JEditor(args);
		       }
		  });

	}

}
