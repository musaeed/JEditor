package Components;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;

import Gui.JEditor;
import IOFactory.Reader;
import IOFactory.Writer;
import OptionDialogs.Dialogs;

import component_listeners.CTabMouseListener;
import component_listeners.TabChangeListener;
import component_listeners.TabMouseWheelListener;

import core.TextPanel;

public class CTabbedPane extends JTabbedPane{

	private static final long serialVersionUID = 1L;

	private static CTabbedPane instance = null;

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
		setComponentPopupMenu(new CTabPopupMenu());
		addTab("Untitled", new TextPanel());
		setIconAt(getSelectedIndex(), new ImageIcon(Toolkit.getDefaultToolkit().getImage(Writer.class.getClassLoader().getResource("images/document_small.png"))));
		addChangeListener(new TabChangeListener());

	}

	public TextPanel getPanel(){
		return (TextPanel)getSelectedComponent();
	}

	public void addTab(String title){
		addTab(title, new TextPanel());
		try{
			setSelectedIndex(getSelectedIndex()+1);
		} catch(Exception e){
			setSelectedIndex(0);
		}
		
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
			remove(getSelectedIndex());
			addTab("Untitled");
			getPanel().getTextArea().requestFocusInWindow();
			return;
		}
		
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
		
		removeAll();
		addTab("Untitled");
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
