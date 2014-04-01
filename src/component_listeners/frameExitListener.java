package component_listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Utility.EditorUtilities;

public class frameExitListener extends WindowAdapter{

	@Override
	public void windowClosing(WindowEvent e) {
		EditorUtilities.exitApplication();
	}
	

}
