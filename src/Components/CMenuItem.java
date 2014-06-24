package Components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class CMenuItem extends JMenuItem{

	private static final long serialVersionUID = 8104985824315835432L;

	public CMenuItem(String text, String tooltip, char Mnmonic, KeyStroke accelerator){
		setText(text);
		setToolTipText(tooltip);
		setMnemonic(Mnmonic);
		setAccelerator(accelerator);
		addListener();
	}
	
	public void addListener(){
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				BottomPanel.fileType.setText(getToolTipText());
			}

			@Override
			public void mouseExited(MouseEvent e) {
				BottomPanel.fileType.setText("");
			}
			
		});
	}
	
}
