package IOFactory;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Utility.EditorUtilities;

import Components.CProgressBar;
import Components.CTabbedPane;
import Gui.JEditor;

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
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
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
				EditorUtilities.updateInfo(path,CTabbedPane.getInstance());
				JEditor.frame.setTitle("JEditor - " + path);
				updateInfo();
			}
		});

		thread.start();
	}
	
	public static void updateInfo(){

		CProgressBar.getInstance().setValue(0);
		CTabbedPane.getInstance().getPanel().getTextArea().discardAllEdits();
		CTabbedPane.getInstance().getPanel().setNeedsToBeSaved(false);
		CTabbedPane.getInstance().setIconAt(CTabbedPane.getInstance().getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(Writer.class.getClassLoader().getResource("images/document_small.png"))));
		CTabbedPane.getInstance().getPanel().getTextArea().requestFocusInWindow();
		
	}
}
