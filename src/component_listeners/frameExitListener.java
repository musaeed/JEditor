package component_listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class frameExitListener extends WindowAdapter{

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
	

}
