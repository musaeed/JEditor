package component_listeners;

import java.io.File;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Components.CTabbedPane;
import Components.FileViewer;
import Gui.JEditor;

public class TabChangeListener implements ChangeListener{

	@Override
	public void stateChanged(ChangeEvent e) {
		try{
			JEditor.frame.setTitle("JEditor - " + CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null ? "Untitled" : CTabbedPane.getInstance().getPanel().getCurrentFilePath());

		} catch (Exception ex){

			JEditor.frame.setTitle("JEditor - Untitled");
		}
		
		FileViewer.getInstance().setSelectedFile(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null ? null : new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getName());

	}
}
