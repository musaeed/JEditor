package Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class CButton extends JButton{

	private static final long serialVersionUID = -3578309610785152111L;
	
	public CButton(final String text, final String ToolTip, final char Mnmonic, final KeyStroke stroke , final String iconPath){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				setText(text);
				setToolTipText(ToolTip);
				setMnemonic(Mnmonic);
				setForeground(Color.BLACK);
				
				getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, 1);
				getActionMap().put(1, new AbstractAction() {
					
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent arg0) {
						doClick();
					}
				});
				
				addColorListener();
			}
		}).start();
		
	}
	
	public void addListener(){
		addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setContentAreaFilled(true);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setContentAreaFilled(false);
			}
			
		});
	}
	
	public void addColorListener(){
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
