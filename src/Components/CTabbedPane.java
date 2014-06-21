package Components;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import Gui.JEditor;
import IOFactory.Reader;
import IOFactory.Writer;
import OptionDialogs.Dialogs;
import Utility.BackUp;

import component_listeners.CTabMouseListener;
import component_listeners.TabChangeListener;
import component_listeners.TabMouseWheelListener;

import core.TextPanel;

public class CTabbedPane extends JTabbedPane{

	private static final long serialVersionUID = 1L;

	private static CTabbedPane instance = null;
	private int unique = 0;

	public static CTabbedPane getInstance(){
		if(instance == null){

			instance = new CTabbedPane();
		}

		return instance;
	}

	private CTabbedPane(){
		init();
	}

	public void init(){

		addMouseWheelListener(new TabMouseWheelListener());		
		addMouseListener(new CTabMouseListener());
		addTab("Untitled", new TextPanel(++unique));
		setTabComponentAt(0, new TabClosePanel("Untitled" , unique));
		setIconAt(getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(Writer.class.getClassLoader().getResource("images/document_small.png"))));
		addChangeListener(new TabChangeListener());
		setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		setFont(new Font("Ubuntu", Font.PLAIN, 14));

	}

	public TextPanel getPanel(){
		return (TextPanel)getSelectedComponent();
	}

	public void addTab(String title){
		
		setSelectedIndex(getTabCount()-1);
		addTab(title, new TextPanel(++unique));

		try{
			setSelectedIndex(getSelectedIndex()+1);
		} catch(Exception e){
			setSelectedIndex(0);
		}
		setTabComponentAt(getSelectedIndex(), new TabClosePanel("Untitled" , unique));
		setIconAt(getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(Writer.class.getClassLoader().getResource("images/document_small.png"))));
		getPanel().getTextArea().requestFocusInWindow();
	}

	public void closeCurrentTab(){

		if(CTabbedPane.getInstance().getPanel().isNeedsToBeSaved()){

			int result = Dialogs.showConfirmationDialog(JEditor.frame, "This file needs to be saved. Do you want to save the changes?", "Confirm", Dialogs.YES_NO_CANCEL_OPTION, new Dimension(450,500));

			if(result == Dialogs.CANCEL_OPTION){
				return;
			}

			if(result == Dialogs.YES_OPTION){
				if(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null){
					Writer.showSaveDialog();
				}
				else{
					Writer.saveFile(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
				}

			}
		}

		if(getTabCount() == 1){
			FileViewer.getInstance().removeFromTree(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null ? null : new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getName());
			BackUp.getInstance().removeAllFiles();
			remove(getSelectedIndex());
			addTab("Untitled");
			getPanel().getTextArea().requestFocusInWindow();
			return;
		}

		FileViewer.getInstance().removeFromTree(CTabbedPane.getInstance().getPanel().getCurrentFilePath() == null ? null : new File(CTabbedPane.getInstance().getPanel().getCurrentFilePath()).getName());
		BackUp.getInstance().removeFile(CTabbedPane.getInstance().getPanel().getCurrentFilePath());
		remove(getSelectedIndex());
		getPanel().getTextArea().requestFocusInWindow();
	}

	public void closeAllTabs(){
		boolean isNeedToBeSaved = false;

		for(int i = 0 ; i < CTabbedPane.getInstance().getTabCount() ; i++){
			if(((TextPanel)CTabbedPane.getInstance().getComponentAt(i)).isNeedsToBeSaved()){
				isNeedToBeSaved = true;
			}
		}

		if(isNeedToBeSaved){
			int result = Dialogs.showConfirmationDialog(JEditor.frame, "Some of the files need to be saved. Are you sure you want to close them?", "Confirm", Dialogs.YES_NO_OPTION, new Dimension(530,530));

			if(result == Dialogs.NO_OPTION){
				return;
			}
		}

		FileViewer.getInstance().removeAllFiles();
		removeAll();
		BackUp.getInstance().removeAllFiles();
		addTab("Untitled");
	}



	@Override
	public void setIconAt(int index, Icon icon) {
		TabClosePanel panel = (TabClosePanel)getTabComponentAt(index);
		panel.setIcon(icon);
	}

	@Override
	public void setTitleAt(int index, String title) {
		TabClosePanel panel = (TabClosePanel)getTabComponentAt(index);
		panel.setTitle(title);
	}



	@Override
	public void setToolTipTextAt(int index, String toolTipText) {
		TabClosePanel panel = (TabClosePanel)getTabComponentAt(index);
		panel.setToolTip(toolTipText);
	}



	@Override
	public String getTitleAt(int index) {
		try{
			TabClosePanel panel = (TabClosePanel)getTabComponentAt(index);
			return panel.getTitle();
		}
		catch(Exception e){
			return "Untitled";
		}

	}

	public void openCommandLineFiles(String args[]){

		if(args.length == 1){
			Reader.loadFile(args[0]);
			return;
		}

		for(int i = 0 ; i < args.length ;i++){

			Reader.loadFile(args[i]);

			if(i != 0 && i != args.length - 1)
				addTab("");

		}

	}


}
