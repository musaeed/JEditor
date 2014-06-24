package Components;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

public class CLabel extends JLabel{


	private static final long serialVersionUID = 1L;
	
	public CLabel(){
		
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setForeground(new Color(215, 72, 20));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setForeground(Color.BLACK);
			}
			
		});
		
	}

}
