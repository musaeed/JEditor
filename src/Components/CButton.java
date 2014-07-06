package Components;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class CButton extends JButton{

	private static final long serialVersionUID = -3578309610785152111L;
	
	public CButton(String text, String ToolTip, char Mnmonic, KeyStroke stroke , String iconPath){
		setText(text);
		setToolTipText(ToolTip);
		setMnemonic(Mnmonic);
		//setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CButton.class.getClassLoader().getResource(iconPath))));
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, 1);
		getActionMap().put(1, new AbstractAction() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				doClick();
			}
		});
		
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

}
