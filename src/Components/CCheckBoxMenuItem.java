package Components;

import javax.swing.JCheckBoxMenuItem;

public class CCheckBoxMenuItem extends JCheckBoxMenuItem{

	private static final long serialVersionUID = -1277062892208677270L;
	
	public CCheckBoxMenuItem(String text, String tooltip){
		setText(text);
		setToolTipText(tooltip);
	}

}
