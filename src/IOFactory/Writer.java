package IOFactory;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import Components.CTabbedPane;
import Gui.JEditor;

public class Writer {

	public static void showSaveDialog(){
		FileDialog dialog = new FileDialog(JEditor.frame , "Save file as" , FileDialog.SAVE);
		dialog.setVisible(true);

		String filename;

		try{
			filename = dialog.getFiles()[0].getAbsolutePath();

		} catch(Exception e){
			return;
		}
		saveFile(filename);
	}

	
	
	public static void saveFile(final String path){
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				PrintWriter writer = null;

				try {

					writer = new PrintWriter(new File(path));
					writer.write(CTabbedPane.getInstance().getPanel().getTextArea().getText());
					writer.flush();

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					writer.close();
				}

			}
		});
		
		thread.start();
	}

}