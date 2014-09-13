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
import Components.RibbonMenu;
import Gui.JEditor;
import Utility.BackUp;
import Utility.EditorUtilities;
import Utility.RecentFiles;
import Utility.SmartWordAdder;
import core.TextPanel;

public class Reader {

	public static void openDialog(){
		FileDialog dialog = new FileDialog(JEditor.frame , "Open a file" , FileDialog.LOAD);
		dialog.setMultipleMode(true);
		dialog.setVisible(true);

		try{

			dialog.getFiles()[0].getAbsolutePath();

		} catch(Exception e){
			CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
			return;
		}

		for(int i = 0 ; i < dialog.getFiles().length ; i++){
			loadFile(dialog.getFiles()[i].getAbsolutePath());

			if(i != dialog.getFiles().length-1){
				RibbonMenu.newtab.doClick();
			}

		}

	}

	public static void loadFile(final String path){

		if(checkFileExists(path)){
			return;
		}


		BottomPanel.progressLabel.setText("Loading...");

		BufferedReader reader = null;
		final StringBuffer buff = new StringBuffer("");
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

		final RSyntaxTextArea tArea = CTabbedPane.getInstance().getPanel().getTextArea();
		tArea.setText(buff.toString());
		SmartWordAdder.addWordsFromText(CTabbedPane.getInstance().getPanel().getWordSuggestions().getList(), tArea.getText());
		JEditor.frame.setTitle("JEditor - " + path);
		FileViewer.getInstance().addToTree(new File(path).getName(),CTabbedPane.getInstance().getPanel().unique);
		updateInfo();
		RecentFiles.getInstance().addToList(path);
		EditorUtilities.updateInfo(path,CTabbedPane.getInstance());
		FileViewer.getInstance().setSelectedFile(CTabbedPane.getInstance().getPanel().unique);
		BackUp.getInstance().addFile(path);

	}


	public static boolean checkFileExists(String path){
		for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++){

			if(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).getCurrentFilePath() == null){
				continue;
			}

			if(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).getCurrentFilePath().equals(path)){
				CTabbedPane.getInstance().remove(CTabbedPane.getInstance().getTabCount()-1);
				CTabbedPane.getInstance().setSelectedIndex(i);
				JOptionPane.showMessageDialog(null, "File is already opened.", "JEditor - Message", JOptionPane.INFORMATION_MESSAGE);
				return true;
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
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();

	}
}
