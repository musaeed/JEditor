package component_listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Components.RibbonMenu;

public class CTabMouseListener extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getButton() == MouseEvent.BUTTON2){
			RibbonMenu.close.doClick();
		}
	}

}
