package Components;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class RibbonButton extends JButton{
	
	private static final long serialVersionUID = 1L;

	public RibbonButton(final String s , final String t){
		
		super(s);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						setContentAreaFilled(true);
						setOpaque(true);
						setForeground(new Color(215, 72, 20));
						BottomPanel.fileType.setText(((RibbonButton)e.getSource()).getToolTipText());
					}

					@Override
					public void mouseExited(MouseEvent e) {
						setContentAreaFilled(false);
						setOpaque(false);
						setForeground(Color.BLACK);
						BottomPanel.fileType.setText("");
					}


					
				});

				setContentAreaFilled(false);
				setOpaque(false);
				
				setToolTipText(t);
			}
		}).start();
	}
	
	public void setAccelerator(KeyStroke stroke){
		
		getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(stroke, 0);
		getActionMap().put(0, new AbstractAction() {
			
			private static final long serialVersionUID = -8218382033869091796L;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				doClick();
			}
		});
	}
}


