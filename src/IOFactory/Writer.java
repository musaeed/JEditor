package IOFactory;

import java.awt.FileDialog;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.ImageIcon;

import Components.BottomPanel;
import Components.CProgressBar;
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
			CTabbedPane.getInstance().getPanel().getTextArea().requestFocus();
			return false;
		}

		CTabbedPane.getInstance().getPanel().setCurrentFilePath(filename);
		saveFile(filename);

		return true;
	}



	public static void saveFile(final String path){

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				CProgressBar.getInstance().setValue(5);
				BottomPanel.progressLabel.setText("Saving...");
				PrintWriter writer = null;
				CProgressBar.getInstance().setValue(20);
				try {

					writer = new PrintWriter(new File(path));
					CProgressBar.getInstance().setValue(35);
					writer.write(CTabbedPane.getInstance().getPanel().getTextArea().getText());
					writer.flush();
					CProgressBar.getInstance().setValue(60);

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} finally {
					writer.close();
					CProgressBar.getInstance().setValue(80);
				}

				updateInfo();
				CProgressBar.getInstance().setValue(100);
				BottomPanel.progressLabel.setText("");
				CProgressBar.getInstance().setValue(0);
				CTabbedPane.getInstance().getPanel().getTextArea().requestFocusInWindow();
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