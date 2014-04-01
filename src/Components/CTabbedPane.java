package Components;

import javax.swing.JTabbedPane;

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
		addTab("Untitled", new TextPanel());
	}
	
	public TextPanel getPanel(){
		return (TextPanel)getSelectedComponent();
	}

}
