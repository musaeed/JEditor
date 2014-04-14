package IOFactory;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Components.BottomPanel;
import Components.CProgressBar;
import Components.CTabbedPane;
import Components.FileViewer;
import Gui.JEditor;
import Utility.EditorUtilities;
import Utility.RecentFiles;
import core.TextPanel;

public class Reader {
	
	public static void openDialog(){
		FileDialog dialog = new FileDialog(JEditor.frame , "Open a file" , FileDialog.LOAD);
		dialog.setVisible(true);
		String filename = "";
		
		try{
			filename = dialog.getFiles()[0].getAbsolutePath();
		
		} catch(Exception e){
		
			return;
		}
		
		loadFile(filename);

	}

	public static void loadFile(final String path){
				
				if(checkFileExists(path)){
					return;
				}
				
				
				BottomPanel.progressLabel.setText("Loading...");
				
				BufferedReader reader = null;
				StringBuffer buff = new StringBuffer("");
				int n = 0;
				
				try {
					reader = new BufferedReader(new FileReader(new File(path)));
					String line;

					while((line = reader.readLine()) != null){
						CProgressBar.getInstance().setValue(++n);
						buff.append(line + "\n");
					}

				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				RSyntaxTextArea tArea = CTabbedPane.getInstance().getPanel().getTextArea();
				tArea.setText(buff.toString());
				JEditor.frame.setTitle("JEditor - " + path);
				FileViewer.getInstance().addToTree(new File(path).getName());
				updateInfo();
				RecentFiles.getInstance().addToList(path);
				EditorUtilities.updateInfo(path,CTabbedPane.getInstance());
				FileViewer.getInstance().setSelectedFile(path);
		}
	
	public static boolean checkFileExists(String path){
		for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++){
			
			if(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).getCurrentFilePath() == null){
				continue;
			}
			
			if(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).getCurrentFilePath().equals(path)){
				CTabbedPane.getInstance().setSelectedIndex(i);
				JOptionPane.showMessageDialog(JEditor.frame, "File is already opened.", "Message", JOptionPane.INFORMATION_MESSAGE);
				return true;
			}
			
			if(new File(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).getCurrentFilePath()).getName().equals(new File(path).getName())){
				//TODO bund ho gai hai!!
			}
		}
		return false;
	}
	
	public static void updateInfo(){

		CProgressBar.getInstance().setValue(0);
		CTabbedPane.getInstance().getPanel().getTextArea().discardAllEdits();
		CTabbedPane.getInstance().getPanel().setNeedsToBeSaved(false);
		CTabbedPane.getInstance().setIconAt(CTabbedPane.getInstance().getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(Writer.class.getClassLoader().getResource("images/document_small.png"))));
		BottomPanel.progressLabel.setText("");
		CTabbedPane.getInstance().getPanel().getTextArea().setCaretPosition(0);
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocusInWindow();
		
	}
}
