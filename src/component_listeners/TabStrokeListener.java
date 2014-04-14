package component_listeners;

import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

import Components.CTabbedPane;

public class TabStrokeListener {
	
	public TabStrokeListener(){
		init();
		addActions();
	}
	
	public void init(){
		
		CTabbedPane.getInstance().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, InputEvent.ALT_DOWN_MASK), 2);
		CTabbedPane.getInstance().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, InputEvent.ALT_DOWN_MASK), 3);
		
	}
	
	public void addActions(){
		
		CTabbedPane.getInstance().getActionMap().put(2, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(CTabbedPane.getInstance().getSelectedIndex() == 0){
					return;
				}
				
				CTabbedPane.getInstance().setSelectedIndex(CTabbedPane.getInstance().getSelectedIndex() - 1);
			}
		});
		
		CTabbedPane.getInstance().getActionMap().put(3, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(CTabbedPane.getInstance().getTabCount() - 1 == CTabbedPane.getInstance().getSelectedIndex()){
					return;
				}
				
				CTabbedPane.getInstance().setSelectedIndex(CTabbedPane.getInstance().getSelectedIndex() + 1);
			}
		});
	}

}
