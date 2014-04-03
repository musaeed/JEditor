package Components;

import javax.swing.JProgressBar;

public class CProgressBar extends JProgressBar{

	private static final long serialVersionUID = 1L;
	private static CProgressBar instance = null;
	
	private CProgressBar() {
		
	}
	
	public static CProgressBar getInstance(){
		if(instance == null){
			
			instance = new CProgressBar();
			
		}
		
		return instance;
	}

}
