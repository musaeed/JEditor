package component_listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Components.CTabbedPane;
import Components.TextPanelPopupMenu;

public class TextPanelMouseListener extends MouseAdapter{

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON3){
			TextPanelPopupMenu.getInstance().getMenu().show(CTabbedPane.getInstance().getPanel(), e.getX(), e.getY());
		}
	}

}
