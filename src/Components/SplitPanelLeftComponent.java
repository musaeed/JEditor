package Components;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SplitPanelLeftComponent extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public SplitPanelLeftComponent(){
		init();
	}
	
	public void init(){
		setLayout(new BorderLayout());
		add(new JScrollPane(FileViewer.getInstance().getTree()), BorderLayout.CENTER);
		add(QuickPanel.getInstance().getPanel() , BorderLayout.SOUTH);
	}

}
