package component_listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import Components.SplitPanelLeftComponent;
import Gui.JEditor;
import Utility.EditorUtilities;

public class frameListener extends WindowAdapter implements ComponentListener{

	@Override
	public void windowClosing(WindowEvent e) {
		EditorUtilities.exitApplication();
	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		JEditor.splitPane.setDividerLocation(0.11);
		SplitPanelLeftComponent.getInstance().getSplitPane().setDividerLocation(0.82);
	}

	@Override
	public void componentShown(ComponentEvent e) {}

}
