import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Gui.JEditor;
import Utility.BackUp;
import Utility.CheckUpdates;
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
		    	System.setProperty("awt.useSystemAAFontSettings","on");
		    	System.setProperty("swing.aatext", "true");
		   		new JEditor(args);
		   		BackUp.checkAbnormalClose();
		   		new CheckUpdates().start();
		       }
		  });

	}

}
