package IOFactory;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;

import Components.BottomPanel;
import Components.CTabbedPane;
import Gui.JEditor;

public class Writer {

	public static boolean showSaveDialog(){
		FileDialog dialog = new FileDialog(JEditor.frame , "Save file as" , FileDialog.SAVE);
		dialog.setVisible(true);

		String filename;

		try{

			filename = dialog.getFiles()[0].getAbsolutePath();

		} catch(Exception e){
			return false;
		}

		saveFile(filename);

		return true;
	}



	public static void saveFile(final String path){

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				BottomPanel.progressLabel.setText("Saving...");
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

				updateInfo();
				BottomPanel.progressLabel.setText("");
			}
		});

		thread.start();
	}

	public static void updateInfo(){
		CTabbedPane.getInstance().getPanel().setNeedsToBeSaved(false);
		CTabbedPane.getInstance().setIconAt(CTabbedPane.getInstance().getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(Writer.class.getClassLoader().getResource("images/document_small.png"))));

		if(CTabbedPane.getInstance().getTitleAt(CTabbedPane.getInstance().getSelectedIndex()).endsWith("*")){
			CTabbedPane.getInstance().setTitleAt(CTabbedPane.getInstance().getSelectedIndex(), CTabbedPane.getInstance().getTitleAt(CTabbedPane.getInstance().getSelectedIndex()).substring(0, CTabbedPane.getInstance().getTitleAt(CTabbedPane.getInstance().getSelectedIndex()).length()-1));
		}

	}

}