package IOFactory;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import Utility.EditorUtilities;

import Components.CTabbedPane;
import Gui.JEditor;

public class Reader {

	public static void openDialog(){
		FileDialog dialog = new FileDialog(JEditor.frame , "Open a file" , FileDialog.LOAD);
		dialog.setVisible(true);
		String filename = dialog.getFiles()[0].getAbsolutePath();

		if(filename == null){
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

				try {
					reader = new BufferedReader(new FileReader(new File(path)));
					String line;

					while((line = reader.readLine()) != null){
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
				tArea.requestFocus();
				EditorUtilities.updateInfo(path,CTabbedPane.getInstance());
			}
		});

		thread.start();
	}
}
