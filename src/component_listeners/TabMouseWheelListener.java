package component_listeners;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import Components.CTabbedPane;

public class TabMouseWheelListener implements MouseWheelListener{

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(e.getWheelRotation() > 0){
		
			int current = CTabbedPane.getInstance().getSelectedIndex();
			int count = CTabbedPane.getInstance().getTabCount();

			if(current < count - 1){
				CTabbedPane.getInstance().setSelectedIndex(current + 1);
			}
		}
		else{
			int current = CTabbedPane.getInstance().getSelectedIndex();

			if(current != 0){
				CTabbedPane.getInstance().setSelectedIndex(current - 1);
			}
		}
		
	}

}
