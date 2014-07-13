package Components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class SplitPanelLeftComponent extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private static SplitPanelLeftComponent instance;
	
	private JSplitPane pane;
	
	public static SplitPanelLeftComponent getInstance(){
		
		if(instance == null){
			instance = new SplitPanelLeftComponent();
		}
		
		return instance;
	}

	private SplitPanelLeftComponent(){
		init();
	}
	
	public void init(){
		pane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		pane.setLeftComponent(new JScrollPane(FileViewer.getInstance().getTree()));
		pane.setRightComponent(QuickPanel.getInstance().getPanel());
		pane.setDividerSize(10);
		pane.setDividerLocation(510);
		setLayout(new BorderLayout());
		add(pane , BorderLayout.CENTER);
	}
	
	public JSplitPane getSplitPane(){
		return pane;
	}

}
