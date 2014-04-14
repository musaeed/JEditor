package component_listeners;


import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.util.List;

import Components.CTabbedPane;
import IOFactory.Reader;

public class TabDropTargetListener implements DropTargetListener{
	
	private Color color = null;

	@Override
	public void dragEnter(DropTargetDragEvent dtde) {
		
		color = CTabbedPane.getInstance().getPanel().getTextArea().getBackground();
		CTabbedPane.getInstance().getPanel().getTextArea().setBackground(new Color(215, 72, 20));
		
	}

	@Override
	public void dragExit(DropTargetEvent dte) {
		CTabbedPane.getInstance().getPanel().getTextArea().setBackground(color);
	}

	@Override
	public void dragOver(DropTargetDragEvent dtde) {}

	@SuppressWarnings("unchecked")
	@Override
	public void drop(DropTargetDropEvent dtde) {
		
		CTabbedPane.getInstance().getPanel().getTextArea().setBackground(color);
		
		try {
			dtde.acceptDrop(DnDConstants.ACTION_COPY);
            List<File> droppedFiles = (List<File>)dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
            
            for (File file : droppedFiles) {
            	
            	if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() != null && (CTabbedPane.getInstance().getPanel().getTextArea().getText() != null || CTabbedPane.getInstance().getPanel().getTextArea().getText().equals(""))){
                	CTabbedPane.getInstance().addTab("Untitled");
                }
            	
            	Reader.loadFile(file.getAbsolutePath());
            }
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}

	@Override
	public void dropActionChanged(DropTargetDragEvent dtde) {}

}
