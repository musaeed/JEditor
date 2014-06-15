import java.awt.EventQueue;

import Gui.JEditor;
import Utility.BackUp;
import Utility.Themes;
import core.StartService;


public class MainClassEditor {
	
	public static void main(final String args[]){
		
		new StartService(args);
		
		EventQueue.invokeLater(new Runnable()
		  {
		       public void run()
		       {
		    	Themes.setTheme();
		    	System.setProperty("awt.useSystemAAFontSettings","on");
		    	System.setProperty("swing.aatext", "true");
		   		new JEditor(args);
		   		BackUp.checkAbnormalClose();
		   		//new CheckUpdates().start();
		       }
		  });

	}

}
